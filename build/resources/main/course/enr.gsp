<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/18/2023
  Time: 3:25 PM
--%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<!-- -->
<div class="row">

    <g:if test="${courses == []}">
        <h5 class="card-title">You are not enrolled in any courses.</h5>
    </g:if>
    <g:each var="course" in="${courses}" >
        <div class="col-md-3 col-xs-12">
            <div class="card">

                <asset:image src="grails.svg" class="card-img-top"/>
                <div class="card-body">
                    <h5 class="card-title">${course?.title}</h5>
                    <p>${course.description}</p>
                </div>
                <div class="card-footer text-right">
                    <a class="btn btn-primary text-right" href="${createLink(controller:'course',action:'details',id:course.id)}"><g:message code='course.see_details.label' /> <i class="fas fa-arrow"></i></a>
                </div>
            </div>
        </div>
    </g:each>
</div>
<!-- -->
</body>
</html>
