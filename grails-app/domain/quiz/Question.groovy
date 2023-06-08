package quiz

class Question {

    String text
    static hasMany = [answers: Answer]
    static belongsTo = [quiz: Quiz]
    static constraints = {
    }
}
