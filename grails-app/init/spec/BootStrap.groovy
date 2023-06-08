package spec

import quiz.Answer
import quiz.Question
import quiz.Quiz
import spec.auth.*
import spec.test.*

class BootStrap {

    def init = { servletContext ->
        Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        Role instructorRole = Role.findByAuthority('ROLE_INSTRUCTOR') ?: new Role(authority: 'ROLE_INSTRUCTOR').save()
        Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()

        def admin =  new User(password: "test", username: "admin").save(failOnError : true)
        def student = new User(username: "petar.petrovic", password: "test").save(failOnError : true)
        def student1 = new User(username: "marko.markovic", password: "test").save(failOnError : true)
        def teacher =  new User(password: "test", username: "dr.jovanovic").save(failOnError : true)

        UserRole.findByUserAndRole(student,userRole) ?: new UserRole(user: student,role: userRole).save()
        UserRole.findByUserAndRole(admin,adminRole) ?: new UserRole(user: admin,role: adminRole).save()
        UserRole.findByUserAndRole(student1,userRole) ?: new UserRole(user: student1,role: userRole).save()
        UserRole.findByUserAndRole(teacher,instructorRole) ?: new UserRole(user: teacher,role: instructorRole).save()

        def category1 = new Category(title: 'Softversko inženjerstvo').save(failOnError : true)
        def category2 = new Category(parent: category1, title: 'Machine Learning').save(failOnError : true)
        def category3 = new Category(parent: category1, title: 'Web Development').save(failOnError : true)
        def category4 = new Category(parent: category3, title: 'JavaScript').save(failOnError : true)
        def category5 = new Category(parent: category3, title: 'PHP').save(failOnError : true)

        def categor = new Category( title: 'Elektorotehnika').save(failOnError : true)
        def catego = new Category(parent: categor, title: 'Matematika').save(failOnError : true)
        def category = new Category(parent: categor, title: 'Teorija električnih kola').save(failOnError : true)
        def category4u = new Category(parent: categor, title: 'Arhitektura računara').save(failOnError : true)



        def course = new Course(title: "Uvod u mašinsko učenje", description: "Kurs na kome ćete se upoznati sa osnavama mašinskog učenja.", instructor: teacher).save(failOnError : true)

        def course2 = new Course(title: "Uvod u JavaScript", description: "Osnovni koncepti JavaScript programskog jezika.", instructor: teacher).save(failOnError : true)

        def course3 = new Course(title: "Matematika 1", description: "Matamatika za inženjere.", instructor: teacher).save(failOnError : true)
        def course5 = new Course(title: "Statistika", description: "", instructor: teacher).save(failOnError : true)

        def course4 = new Course(title: "Uvod u električna kola", description: "", instructor: teacher).save(failOnError : true)

        def ct = new CourseCategory(category: category2, course:course).save(failOnError : true)
        def ct2 = new CourseCategory(category: category4, course:course2).save(failOnError : true)
        def ct3 = new CourseCategory(category: catego, course:course3).save(failOnError : true)
        //def ct4 = new CourseCategory(category: category4, course:course2).save(failOnError : true)
        def ct5 = new CourseCategory(category: category, course:course4).save(failOnError : true)

        def ct6 = new CourseCategory(category: category2, course:course5).save(failOnError : true)
        def ct7 = new CourseCategory(category: catego, course:course5).save(failOnError : true)


        def mod = new Module(title: "Prvi modul", course: course, moduleOrderNum: 1).save(failOnError : true)
        def mod2 = new Module(title: "Drugi modul", course: course, moduleOrderNum: 2).save(failOnError : true)

        new Enrollment(student: student, course: course).save(failOnError : true)
        new Enrollment(student: student1, course: course).save(failOnError : true)

        def les1 = new Lesson(title: "Linearna regresija", lessonOrderNum: 1, module: mod, videoURL: 'E5RjzSK0fvY', summary: 'Predavanje sa prvog časa.').save(failOnError : true)
        def les2 = new Lesson(title: "Vrste mašinskog učenja", lessonOrderNum: 2, module: mod).save(failOnError : true)

        def les3 = new Lesson(title: "Lekcija 1", lessonOrderNum: 1, module: mod2).save(failOnError : true)
        def les4 = new Lesson(title: "Lekcija 2", lessonOrderNum: 2, module: mod2).save(failOnError : true)


        def quiz = new Quiz(title: "Prvi probni kviz", description: "Opis", course: course).save(failOnError : true)
        def ques1 = new Question(text: "Prvo. 2 + 2 = ?", quiz: quiz).save(failOnError : true)
        def ques2 = new Question(text: "Drugo. 3 + 3 = ?", quiz: quiz).save(failOnError : true)
        quiz.questions = [ques1, ques2]

        def ans1 = new Answer(question: ques1, text: "4", isCorrect: true).save(failOnError : true)
        def ans2 = new Answer(question: ques1, text: "5", isCorrect: false).save(failOnError : true)
        def ans3 = new Answer(question: ques2, text: "6", isCorrect: true).save(failOnError : true)
        def ans4 = new Answer(question: ques2, text: "5", isCorrect: false).save(failOnError : true)

        ques1.answers = [ans1, ans2]
        ques2.answers = [ans3, ans4]




    }
    def destroy = {
    }
}
