package spec.test

class Lesson {

    int lessonOrderNum
    String title
    String summary
   // String content
    String videoURL
    boolean active = true


    static belongsTo = [module: Module]
    static hasMany = [files:File]

    static constraints = {
        summary nullable:  true
        videoURL nullable:  true
        files nullable:  true

    }

    @Override
    String toString() {
        return  title
    }
}
