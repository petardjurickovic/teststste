package spec.test

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
class FileUploadService implements GrailsConfigurationAware{

    FileService fileService

    String cdnFolder
    String cdnRootUrl

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('grails.guides.cdnFolder')
        cdnRootUrl = co.getRequiredProperty('grails.guides.cdnRootUrl')
    }

    @SuppressWarnings('JavaIoPackageAccess')
    File uploadFile(FileCommand cmd) {

        String filename = cmd.featuredFile.originalFilename
        String folderPath = "${cdnFolder}/images/lessonFiles/${cmd.id}"

        java.io.File folder = new java.io.File(folderPath)
        if ( !folder.exists() ) {
            folder.mkdirs()
        }
        String path = "${folderPath}/${filename}"
        println path
        cmd.featuredFile.transferTo(new java.io.File(path))


        String featuredImageUrl = "/lessonFiles/${cmd.id}/${filename}"
        File f = fileService.updateUrl(cmd.id, cmd.version, featuredImageUrl)

        if ( !f || f.hasErrors() ) {
            java.io.File file = new java.io.File(path)
            file.delete()
        }
        f
    }
}
