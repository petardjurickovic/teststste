package spec.test

import spec.auth.User

class Instructor extends User{
    String resume

    static hasMany = [courses:Course]
    static constraints = {
    }
}
