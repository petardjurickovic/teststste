package quiz

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuizAttemptServiceSpec extends Specification {

    QuizAttemptService quizAttemptService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new QuizAttempt(...).save(flush: true, failOnError: true)
        //new QuizAttempt(...).save(flush: true, failOnError: true)
        //QuizAttempt quizAttempt = new QuizAttempt(...).save(flush: true, failOnError: true)
        //new QuizAttempt(...).save(flush: true, failOnError: true)
        //new QuizAttempt(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //quizAttempt.id
    }

    void "test get"() {
        setupData()

        expect:
        quizAttemptService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<QuizAttempt> quizAttemptList = quizAttemptService.list(max: 2, offset: 2)

        then:
        quizAttemptList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        quizAttemptService.count() == 5
    }

    void "test delete"() {
        Long quizAttemptId = setupData()

        expect:
        quizAttemptService.count() == 5

        when:
        quizAttemptService.delete(quizAttemptId)
        sessionFactory.currentSession.flush()

        then:
        quizAttemptService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        QuizAttempt quizAttempt = new QuizAttempt()
        quizAttemptService.save(quizAttempt)

        then:
        quizAttempt.id != null
    }
}
