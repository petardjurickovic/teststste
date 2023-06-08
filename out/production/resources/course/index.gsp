<%@ page import="spec.test.Category" %>
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
    <div class="col-lg-4">
        <div class="card">
            <div class="card-body">
                <a href="${createLink(controller:'course',action:'index')}"> <h5 class="card-title">All</h5></a>
                <g:render template="cat" model="[categories: spec.test.Category.findAllByParent(null), depth:0]" />

            </div>
        </div>
    </div>
    <div class="col-lg-8">
        <g:if test="${courseList == []}">
            <h5 class="card-title">Currently there are no courses on the selected topic.</h5>
            <h5 class="card-title">Please try one of the other categories.</h5>
        </g:if>
    <g:each var="course" in="${courseList}" >
        <div class="col-md-4 col-xs-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${course?.title}</h5>
                    <p>${course.description}</p>
                </div>
                <div class="card-footer text-right">
                    <a class="btn btn-primary text-right" href="${createLink(controller:'course',action:'details',id:course.id)}"><g:message code='course.see_details.label' /> <i class="fas fa-arrow"></i></a>
                </div>
            </div>
        </div>
        <br>
    </g:each>
    </div>

</div>
<!-- -->
</body>
</html>
