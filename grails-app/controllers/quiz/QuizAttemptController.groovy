package quiz

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('permitAll')

class QuizAttemptController {

    SpringSecurityService springSecurityService

    QuizAttemptService quizAttemptService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond quizAttemptService.list(params), model:[quizAttemptCount: quizAttemptService.count()]
    }

    def show(Long id) {
        respond quizAttemptService.get(id)
    }

    def create() {
        respond new QuizAttempt(params)
    }

    def create2() {
        def currentUser = springSecurityService.currentUser
        def quiz = Quiz.get(params.quizId.toLong())
        def questions = quiz.questions.toList()
        println(params)
        println("sss")
        println("Params: ${params}")


        // Retrieve the selected answer IDs from the submitted form
        def selectedAnswerIds = []
        params.keySet().findAll { it.startsWith('_answers') }.each { key ->
            def index = key.substring('_answers['.length(), key.length() - 1).toInteger()
            selectedAnswerIds << params["answers[${index}]"].toLong()
        }

        // Calculate the score based on the selected answers
        def score = calculateScore(questions, selectedAnswerIds)

        def quizAttempt = new QuizAttempt(
                student: currentUser,
                quiz: quiz,
                date: new Date(),
                score: score
        )

        if (!quizAttempt.validate()) {
            // Handle validation errors, if any
            flash.error = "Failed to create quiz attempt: ${quizAttempt.errors}"
            redirect(action: 'index') // Adjust the redirect target as needed
            return
        }

        quizAttempt.save(failOnError: true)

        redirect(action: 'show', id: quizAttempt.id)
    }

    private int calculateScore(List<Question> questions, List<Long> selectedAnswerIds) {
        int score = 0

        questions.each { question ->
            def correctAnswerIds = question.answers.findAll { answer -> answer.isCorrect }
                    .collect { answer -> answer.id }

            if (selectedAnswerIds.containsAll(correctAnswerIds)) {
                score++
            }
        }

        return score
    }



    def save(QuizAttempt quizAttempt) {
        if (quizAttempt == null) {
            notFound()
            return
        }

        try {
            quizAttemptService.save(quizAttempt)
        } catch (ValidationException e) {
            respond quizAttempt.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quizAttempt.label', default: 'QuizAttempt'), quizAttempt.id])
                redirect quizAttempt
            }
            '*' { respond quizAttempt, [status: CREATED] }
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def edit(Long id) {
        respond quizAttemptService.get(id)
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def update(QuizAttempt quizAttempt) {
        if (quizAttempt == null) {
            notFound()
            return
        }

        try {
            quizAttemptService.save(quizAttempt)
        } catch (ValidationException e) {
            respond quizAttempt.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quizAttempt.label', default: 'QuizAttempt'), quizAttempt.id])
                redirect quizAttempt
            }
            '*'{ respond quizAttempt, [status: OK] }
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        quizAttemptService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quizAttempt.label', default: 'QuizAttempt'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizAttempt.label', default: 'QuizAttempt'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
