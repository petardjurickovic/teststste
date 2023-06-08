package spec.test

class Module {

    int moduleOrderNum
    String title
    String summary
    static belongsTo = [course: Course]
    static hasMany = [lessons: Lesson]

    static constraints = {
        summary nullable: true
    }

    @Override
     String toString() {
        return  title
    }
}
