package quiz

import grails.gorm.services.Service

@Service(QuizAttempt)
interface QuizAttemptService {

    QuizAttempt get(Serializable id)

    List<QuizAttempt> list(Map args)

    Long count()

    void delete(Serializable id)

    QuizAttempt save(QuizAttempt quizAttempt)

}