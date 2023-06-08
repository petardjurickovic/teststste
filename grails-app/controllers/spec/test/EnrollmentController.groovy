package spec.test

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('permitAll')
class EnrollmentController {

    EnrollmentService enrollmentService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond enrollmentService.list(params), model:[enrollmentCount: enrollmentService.count()]
    }

    def myEnrollments(){
       def enrollments = Enrollment.findAllByStudent(springSecurityService.currentUser)
      // def courses = Course.findAll()
    }

    def show(Long id) {
        respond enrollmentService.get(id)
    }

    def create() {
        def loggedUser = springSecurityService.currentUser
        def course = Course.findById(params.id)
        def enrollment = Enrollment.findAllByStudentAndCourse(loggedUser, course)

        //println params
        println "llk"
        def coursePassword = course.password


        if(enrollment!=[]){
            flash.message = message(code: 'already.enrolled', args: [message(code: 'enrollment.label', default: 'Course'), params.id])
            redirect controller: 'course', action: "details", id: params.id
            return
        }

        respond new Enrollment(params), model:[loggedUser :loggedUser, coursePassword: coursePassword]
    }

    def save(Enrollment enrollment) {
        if (enrollment == null) {
            notFound()
            return
        }

        if(enrollment.course.password!=params.password){
            wrongPassword()
            return
        }

        try {
            enrollmentService.save(enrollment)
        } catch (ValidationException e) {
            respond enrollment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'enrollment.created.label', args: [message(code: 'enrollment.label'), params.course])
                redirect(url: "/course/details/${params.course}")
            }
            '*' { respond enrollment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond enrollmentService.get(id)
    }

    def update(Enrollment enrollment) {
        if (enrollment == null) {
            notFound()
            return
        }

        try {
            enrollmentService.save(enrollment)
        } catch (ValidationException e) {
            respond enrollment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), enrollment.id])
                redirect enrollment
            }
            '*'{ respond enrollment, [status: OK] }
        }
    }

    def delete2() {
        def loggedUser = springSecurityService.currentUser
        def course = Course.findById(params.id)
        def enrollment = Enrollment.findAllByStudentAndCourse(loggedUser, course)

        delete(enrollment[0].id)
        flash.message = message(code: 'enrollment.deleted.label', args: [message(code: 'enrollment.label', default: 'Enrollment'), params.id])
        redirect controller: 'course', action: "details", id: params.id
    }


    def delete(Long id) {

        if (id == null) {
            notFound()
            return
        }

        enrollmentService.delete(id)
        return
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), id])
//                redirect action:"index", method:"GET"
//
//            }
//            '*'{ render status: NO_CONTENT }
//        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrollment.label', default: 'Enrollment'), params.course])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    protected void wrongPassword() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'wrong.password', args: [message(code: 'enrollment.label', default: 'Course'), params.course])
                redirect controller: 'course', action: "details", id: params.course
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
