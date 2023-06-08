package quiz

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnswerServiceSpec extends Specification {

    AnswerService answerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Answer(...).save(flush: true, failOnError: true)
        //new Answer(...).save(flush: true, failOnError: true)
        //Answer answer = new Answer(...).save(flush: true, failOnError: true)
        //new Answer(...).save(flush: true, failOnError: true)
        //new Answer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //answer.id
    }

    void "test get"() {
        setupData()

        expect:
        answerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Answer> answerList = answerService.list(max: 2, offset: 2)

        then:
        answerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        answerService.count() == 5
    }

    void "test delete"() {
        Long answerId = setupData()

        expect:
        answerService.count() == 5

        when:
        answerService.delete(answerId)
        sessionFactory.currentSession.flush()

        then:
        answerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Answer answer = new Answer()
        answerService.save(answer)

        then:
        answer.id != null
    }
}
