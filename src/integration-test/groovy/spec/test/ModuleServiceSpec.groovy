package spec.test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ModuleServiceSpec extends Specification {

    ModuleService moduleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Module(...).save(flush: true, failOnError: true)
        //new Module(...).save(flush: true, failOnError: true)
        //Module module = new Module(...).save(flush: true, failOnError: true)
        //new Module(...).save(flush: true, failOnError: true)
        //new Module(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //module.id
    }

    void "test get"() {
        setupData()

        expect:
        moduleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Module> moduleList = moduleService.list(max: 2, offset: 2)

        then:
        moduleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        moduleService.count() == 5
    }

    void "test delete"() {
        Long moduleId = setupData()

        expect:
        moduleService.count() == 5

        when:
        moduleService.delete(moduleId)
        sessionFactory.currentSession.flush()

        then:
        moduleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Module module = new Module()
        moduleService.save(module)

        then:
        module.id != null
    }
}
