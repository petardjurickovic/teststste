package spec.test

class File {

    String title
    String url
   static belongsTo = [lesson: Lesson]

    static constraints = {
        title nullable: false
        url nullable: true
    }
}
