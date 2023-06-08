package spec.test

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
@Secured('permitAll')
class LessonController {

    LessonService lessonService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond lessonService.list(params), model:[lessonCount: lessonService.count()]
    }

    def show(Long id) {
        respond lessonService.get(id)
    }

    def create() {
        respond new Lesson(params)
    }

    def details(Long id){
        if(isEnroled()){
            def isInstructor = false
            //println params.id +"ee"
            //println Course.findById(params.course).instructor
            if(Course.findById(params.course).instructor==springSecurityService.currentUser){
                isInstructor=true
            }
            respond lessonService.get(id),  model:[isInstructor: isInstructor]
        }else{
            //println params

            Lesson l = Lesson.findById(params.id)

            def moduleId = Module.executeQuery("select m.id from Module m INNER JOIN Lesson l ON m.id = l.module where l.id = :test",[test: l.id])
            def courseId = Course.executeQuery("select  c.id from Course c INNER JOIN Module m ON c.id = m.course where m.id = :test", [test: moduleId])


            println 'ee'
            flash.message = message(code: 'not.enrolled', args: [message(code: 'module.label', default: 'Module')])
            redirect controller: 'course', action: "details", id: courseId[0]
        }
    }

    def isEnroled(){
        //println params

        def loggedUser = springSecurityService.currentUser
        def course = Course.findById(params.course)
        def enrollment = Enrollment.findAllByStudentAndCourse(loggedUser, course)
        if(enrollment.size()>0){
            return true
        }
        return false
    }

    def save(Lesson lesson) {
        if (lesson == null) {
            notFound()
            return
        }

        try {
            lessonService.save(lesson)
        } catch (ValidationException e) {
            respond lesson.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lesson.label', default: 'Lesson'), lesson.id])
                redirect lesson
            }
            '*' { respond lesson, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond lessonService.get(id)
    }

    def update(Lesson lesson) {
        if (lesson == null) {
            notFound()
            return
        }

        try {
            lessonService.save(lesson)
        } catch (ValidationException e) {
            respond lesson.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'lesson.label', default: 'Lesson'), lesson.id])
                redirect lesson
            }
            '*'{ respond lesson, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        lessonService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'lesson.label', default: 'Lesson'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lesson.label', default: 'Lesson'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
