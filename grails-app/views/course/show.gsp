<%@ page import="spec.test.Enrollment" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-course" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
%{--            <span>Instructor: </span>--}%
%{--            <f:display bean="course"  property="instructor.username"/>--}%

            <f:display bean="course" except="enrollments, categories"/>
            <span style="margin-left: 22%;">Categories:</span>
            <%
                def test =  course.categories.category         %>
            ${test}
%{--            <g:each in="${course.categories.category}" var="category">--}%
%{--                <span style="margin-left: 0.7%;">${category}, </span>--}%
%{--      --}%
%{--            </g:each>--}%
            <f:display bean="course" property="enrollments"/>



%{--            <g:select name="enrollment" from="${spec.test.Enrollment.all}" optionValue="student" optionKey="id" value="${this.course.enrollments}"/>--}%


            <g:form resource="${this.course}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.course}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>

        </div>
    </body>
</html>
