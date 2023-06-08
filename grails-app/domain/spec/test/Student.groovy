package spec.test

import spec.auth.User

class Student extends User{


    static hasMany = [enrollments: Enrollment]
    static constraints = {
    }
}
