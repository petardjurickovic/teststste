package spec.test


import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured('permitAll')
class ModuleController {
    SpringSecurityService springSecurityService
    ModuleService moduleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond moduleService.list(params), model:[moduleCount: moduleService.count()]
    }

    def show(Long id) {
        respond moduleService.get(id)
    }

    def isEnroled(){

        def loggedUser = springSecurityService.currentUser
        def course = Course.findById(params.course)
        def enrollment = Enrollment.findAllByStudentAndCourse(loggedUser, course)
        if(enrollment.size()>0){
            return true
        }
        return false
    }

    def details(Long id) {
        if(isEnroled()){
            def isInstructor = false
            //println params
            try {
                Course.findById(params.course).instructor
            } catch (ValidationException e) {
                respond module.errors, view:'create'
                return
            }
            if(Course.findById(params.course)?.instructor==springSecurityService.currentUser){
                isInstructor=true
            }
            respond moduleService.get(id),  model:[isInstructor: isInstructor]
        }else{
            flash.message = message(code: 'not.enrolled', args: [message(code: 'module.label', default: 'Module'), params.course])
            redirect controller: 'course', action: "details", id: params.course
        }
    }

    def create() {
        respond new Module(params)
    }


    def save(Module module) {
        if (module == null) {
            notFound()
            return
        }

        try {
            moduleService.save(module)
        } catch (ValidationException e) {
            respond module.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'module.label', default: 'Module'), module.id])
                redirect module
            }
            '*' { respond module, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond moduleService.get(id)
    }

    def update(Module module) {
        if (module == null) {
            notFound()
            return
        }

        try {
            moduleService.save(module)
        } catch (ValidationException e) {
            respond module.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'module.label', default: 'Module'), module.id])
                redirect module
            }
            '*'{ respond module, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        moduleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'module.label', default: 'Module'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
