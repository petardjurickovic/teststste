package spec.test

class CourseCategory {

    static belongsTo = [course: Course, category:Category]
    static constraints = {
    }

    @Override
    String toString() {
        return  course.toString() + "-" + category.toString()
    }
}
