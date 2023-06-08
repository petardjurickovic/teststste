package spec.test

class Category {

    Category parent
    String title
    static hasMany = [courses: CourseCategory]
    static constraints = {
        parent nullable: true
    }
//    static mapping = {
//        courses joinTable:[name:"course_category", key:'course_id' ]
//    }
    @Override
      String toString() {
        return title
    }
}
