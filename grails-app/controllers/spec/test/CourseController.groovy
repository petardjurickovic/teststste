package spec.test

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import quiz.Quiz

import static org.springframework.http.HttpStatus.*


@Secured('permitAll')
class CourseController {

    CourseService courseService
    CategoryService categoryService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //println params
        def courses
        if(params.category==null){
            courses = list(-1)
        }else{
            courses = list(params.category.toInteger())
        }
       // println categories(null)

        //println findChildren(Category.findById(1))

        respond courses, model:[courseCount: courseService.count(), params:  courseService.list(params), courseList: courses]
    }

    def list(Long id){
     def courses = Course.list()
         if(id!=-1){
             def parent = Category.findById(id)
             def categories =  findChildren(parent)
             categories += parent
             def ct =[]
             for(category in categories){
                 ct+=CourseCategory.findAllByCategory(category).id
             }

             courses = Course.executeQuery("select c from Course c, CourseCategory ct where c.id = ct.course and ct.id in :test", [test: ct])
             //println ct
             //println categories
             //println courses.unique()
         }
        return courses
    }

    def findChildren(Category c){
        def cat = Category.findAllByParent(c)

        def grandchildren = cat

        for(category in cat){
           // println category
            if(findChildren(category)!=null){
                grandchildren+=findChildren(category)
            }

        }

        return grandchildren
    }

//    def test() {
//        def courses = Course.findAllByInstructor(springSecurityService.currentUser)
//        [courseList: courses, courseCount: courseService.count()]
//    }

    def enr(){
        def enrollments = Enrollment.findAllByStudent(springSecurityService.currentUser)
        def courses = new ArrayList()
        int i=0
        for (i;i<enrollments.size();i++){
            courses.add(enrollments.get(i).course)
        }
        [courses: courses]
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def myCourses(){
        def courses = Course.findAllByInstructor(springSecurityService.currentUser)
        [courses: courses]
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def show(Long id) {
        if(isInstructor(id)){
            respond courseService.get(id)
        }else{
            notFound()
            return
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def create() {
        def currentUser = springSecurityService.currentUser
        //println currentUser
        respond new Course(params), model:[currentUser: currentUser]

    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def save(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'course.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def edit(Long id) {
        if(isInstructor(id)){
            respond courseService.get(id)
        }else{
            notFound()
            return
        }
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN"])
    def update(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    @Secured('ROLE_INSTRUCTOR')
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def isInstructor(Long id){
        def isInstructor = false
        //println id
        //println "Ss"+params
        if(Course.findById(id)?.instructor==springSecurityService.currentUser){
            isInstructor=true
        }
        return isInstructor
    }

    @Secured(["ROLE_INSTRUCTOR", "ROLE_ADMIN", "ROLE_USER"])
    def details(Long id){

        def enrolled = false
        def quizes = Quiz.findAllByCourse(Course.findById(id))
        println(quizes)


        def currentUser = springSecurityService.currentUser
        def enrollments = Enrollment.findAllByStudent(currentUser)
        int i=0
        for (i;i<enrollments.size();i++){
            if(enrollments.get(i).course.id==id){
                enrolled = true
            }
        }

        def isInstructor = isInstructor(id)
        respond courseService.get(id),  model:[enrolled: enrolled, isInstructor: isInstructor, quizes: quizes, currentUser: currentUser]
    }
}
