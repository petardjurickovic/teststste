package spec.test

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured("permitAll")
class CourseCategoryController {

    CourseCategoryService courseCategoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseCategoryService.list(params), model:[courseCategoryCount: courseCategoryService.count()]
    }

    def show(Long id) {
        respond courseCategoryService.get(id)
    }

    def create() {
        respond new CourseCategory(params)
    }

    def save(CourseCategory courseCategory) {
        if (courseCategory == null) {
            notFound()
            return
        }

        try {
            courseCategoryService.save(courseCategory)
        } catch (ValidationException e) {
            respond courseCategory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), courseCategory.id])
                redirect courseCategory
            }
            '*' { respond courseCategory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseCategoryService.get(id)
    }

    def update(CourseCategory courseCategory) {
        if (courseCategory == null) {
            notFound()
            return
        }

        try {
            courseCategoryService.save(courseCategory)
        } catch (ValidationException e) {
            respond courseCategory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), courseCategory.id])
                redirect courseCategory
            }
            '*'{ respond courseCategory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseCategoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
