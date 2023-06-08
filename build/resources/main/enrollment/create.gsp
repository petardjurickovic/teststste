<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'enrollment.label', default: "${params.title}")}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-enrollment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="create-enrollment" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.enrollment}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.enrollment}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>


            <g:form resource="${this.enrollment}" method="POST">
                <fieldset class="form">
%{--                    <f:all bean="enrollment"/>--}%
                    <span>Enrolment key: </span>
                        <g:field type="text" name="password"/>
                        <g:hiddenField name="student" value="${sec.loggedInUserInfo(field: 'id')}" />
                        <g:hiddenField name="course" value="${params.id}" />

                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>

        </div>
    </body>
</html>
