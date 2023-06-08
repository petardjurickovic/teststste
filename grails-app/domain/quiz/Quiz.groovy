package quiz

import spec.test.Course

class Quiz {
    String title
    String description
    Date createdDate
    static hasMany = [questions: Question]
    static belongsTo = [course: Course]

    static constraints = {
        createdDate nullable: true

    }

    @Override
    String toString() {
        return  title
    }
}
