package spec.test

import grails.gorm.services.Service

@SuppressWarnings(['LineLength', 'UnusedVariable', 'SpaceAfterOpeningBrace', 'SpaceBeforeClosingBrace'])
@Service(File)
interface FileService {

    File get(Serializable id)

    List<File> list(Map args)

    Long count()

    void delete(Serializable id)

    File save(File file)

    File updateUrl(Serializable id, Long version, String url)

}