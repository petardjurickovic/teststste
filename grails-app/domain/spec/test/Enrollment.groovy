package spec.test

import spec.auth.User

class Enrollment {

    boolean active = true
    static belongsTo = [student:User,course:Course]

    static constraints = {
    }
}
