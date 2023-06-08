package quiz


class Answer {

    String text
    boolean isCorrect
    static belongsTo = [question:  Question]
    static constraints = {
    }
}
