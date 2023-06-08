package quiz

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured('permitAll')
class QuizController {

    QuizService quizService
    SpringSecurityService springSecurityService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond quizService.list(params), model:[quizCount: quizService.count()]
    }

    def show(Long id) {
        def loggedUser = springSecurityService.currentUser
        def attempt = QuizAttempt.findByQuizAndStudent(Quiz.findById(id), loggedUser)
        def isQuizAttempted = true
        if(attempt == null){isQuizAttempted = false}
        if(!isQuizAttempted){
            respond quizService.get(id), model:[isQuizAttempted: isQuizAttempted]
        }else{
            respond quizService.get(id),  model:[attempt: attempt.id,isQuizAttempted: isQuizAttempted]

        }
    }

    def create() {
        respond new Quiz(params)
    }

    def save(Quiz quiz) {
        if (quiz == null) {
            notFound()
            return
        }

        try {
            quizService.save(quiz)
        } catch (ValidationException e) {
            respond quiz.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quiz.label', default: 'Quiz'), quiz.id])
                redirect quiz
            }
            '*' { respond quiz, [status: CREATED] }
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def edit(Long id) {
        respond quizService.get(id)
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def update(Quiz quiz) {
        if (quiz == null) {
            notFound()
            return
        }

        try {
            quizService.save(quiz)
        } catch (ValidationException e) {
            respond quiz.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quiz.label', default: 'Quiz'), quiz.id])
                redirect quiz
            }
            '*'{ respond quiz, [status: OK] }
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        quizService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quiz.label', default: 'Quiz'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quiz.label', default: 'Quiz'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
