package spec.test

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

@Secured('permitAll')
class FileController {

    FileService fileService
    FileUploadService fileUploadService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fileService.list(params), model:[fileCount: fileService.count()]
    }

    def show(Long id) {
        File file = fileService.get(id)
        if (!file) {
            notFound()
            return
        }
        [file: file]
    }

    def create() {
        respond new File(params)
    }

    def downloadFile = {
        def sub =  params.url.toString()

        println sub
        def file = new java.io.File("C:\\Users\\HP\\IdeaProjects\\mama\\grails-app\\assets/images"+sub)
        if (file.exists())
        {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"${file.name}\"")
            response.outputStream << file.bytes
        }
        else render "Error!"
    }


    def uploadFile(FileCommand cmd) {

//        println cmd.featuredFile.getOriginalFilename()
//        println cmd.version
//        println cmd.id
//        println params

        if (cmd.hasErrors()) {
            respond(cmd, model: [file: cmd], view: 'create')
            return
        }

        File file = fileUploadService.uploadFile(cmd)

        if (file == null) {
            notFound()
            return
        }

        if (file.hasErrors()) {
            respond(file, model: [file: file], view: 'edit')
            return
        }

        Locale locale = request.locale
        flash.message = message(code: 'default.upload.message', args: [message(code: 'file.label', default: 'File'), file.id])
        redirect file
    }

    def save(File file) {
        //println params
        //println params.featuredFile.getOriginalFilename()
        if (file == null) {
            notFound()
            return
        }

        try {
            fileService.save(file)
            MultipartFile featuredFile =  params.featuredFile
            FileCommand cmd = new FileCommand(featuredFile,file.id, file.version.toInteger())
            file = fileUploadService.uploadFile(cmd)
        } catch (ValidationException e) {
            respond file.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'file.label', default: 'File'), file.id])
                redirect file
            }
            '*' { respond file, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fileService.get(id)
    }

    def update(File file) {
        if (file == null) {
            notFound()
            return
        }

        try {
            fileService.save(file)
        } catch (ValidationException e) {
            respond file.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'file.label', default: 'File'), file.id])
                redirect file
            }
            '*'{ respond file, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'file.label', default: 'File'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'file.label', default: 'File'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
