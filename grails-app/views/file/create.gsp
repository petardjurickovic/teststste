<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'file.label', default: 'File')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-file" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-file" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.file}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.file}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
%{--            <g:form resource="${this.file}" method="POST">--}%
%{--                <fieldset class="form">--}%
%{--                    <f:field bean="file" property="title"/>--}%
%{--                    <f:field bean="file" property="lesson" />--}%
%{--                    <input type="file" name="featuredFile" />--}%
%{--                </fieldset>--}%
%{--                <fieldset class="buttons">--}%
%{--                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />--}%
%{--                </fieldset>--}%
%{--            </g:form>--}%

            <g:uploadForm name="save" action="save">
                <f:field bean="file" property="title"/>
                <f:field bean="file" property="lesson" />
                <input bean="file" property="url" type="file" name="featuredFile" />
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'file.upload.label', default: 'Upload')}" />
                </fieldset>
            </g:uploadForm>
%{--            <g:uploadForm name="uploadFile" action="uploadFile">--}%
%{--                <g:hiddenField name="id" value="${this.file?.id}" />--}%
%{--                <g:hiddenField name="version" value="${this.file?.version}" />--}%
%{--                <input type="file" name="featuredFile" />--}%
%{--                <fieldset class="buttons">--}%
%{--                    <input class="save" type="submit" value="${message(code: 'file.upload.label', default: 'Upload')}" />--}%
%{--                </fieldset>--}%
%{--            </g:uploadForm>--}%

        </div>
    </body>
</html>
