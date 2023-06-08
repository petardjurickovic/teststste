package spec.test

import quiz.Quiz
import spec.auth.User


class Course {

    String title
    String description
    User instructor
    String password = "test"
    static  hasMany = [modules:Module, enrollments: Enrollment, categories: CourseCategory, quizes: Quiz]
    static belongsTo = [instructor: Instructor]


    static constraints = {
        enrollments cascade: 'all'
        description nullable: true
    }

    @Override
    String toString() {
        return  title
    }
//    static mapping = {
//        categories joinTable:[name:"course_category", key:'category_id' ]
//    }

}
