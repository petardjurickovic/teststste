package spec.test


import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class FileCommand implements Validateable {
	MultipartFile featuredFile
	Long id
	Integer version

	FileCommand(MultipartFile featuredFile, Long id, Integer version) {
		this.featuredFile = featuredFile
		this.id = id
		this.version = version
	}

	static constraints = {
		id nullable: false
		version nullable: false
		featuredFile  validator: { val, obj ->
			if ( val == null ) {
				return false
			}
			if ( val.empty ) {
				return false
			}

			['jpeg', 'jpg', 'png', 'pdf'].any { extension -> // <1>
				val.originalFilename?.toLowerCase()?.endsWith(extension)
			}
		}
	}
}
