package quiz

import spec.auth.User

class QuizAttempt {

    User student
    Quiz quiz
    Date date
    int score
    static belongsTo = [User, Quiz]
    static constraints = {
    }
}
