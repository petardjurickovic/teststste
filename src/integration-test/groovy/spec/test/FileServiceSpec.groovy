package spec.test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FileServiceSpec extends Specification {

    FileService fileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new File(...).save(flush: true, failOnError: true)
        //new File(...).save(flush: true, failOnError: true)
        //File file = new File(...).save(flush: true, failOnError: true)
        //new File(...).save(flush: true, failOnError: true)
        //new File(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //file.id
    }

    void "test get"() {
        setupData()

        expect:
        fileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<File> fileList = fileService.list(max: 2, offset: 2)

        then:
        fileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        fileService.count() == 5
    }

    void "test delete"() {
        Long fileId = setupData()

        expect:
        fileService.count() == 5

        when:
        fileService.delete(fileId)
        sessionFactory.currentSession.flush()

        then:
        fileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        File file = new File()
        fileService.save(file)

        then:
        file.id != null
    }
}
