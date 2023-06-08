<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'quiz.label', default: 'Quiz')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-quiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-quiz" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="quiz" except="questions, createdDate"/>

    <g:if test="${sec.loggedInUserInfo(field: 'id')}">
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <g:form resource="${this.quiz}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.quiz}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </sec:ifAnyGranted>
    </g:if>

    <div class="card-body">

        <g:if test="${isQuizAttempted == true}">
            <a href="${createLink(controller:'quizAttempt',action:'show',id : attempt)}">click here to view your score.</a>
        </g:if>

        <g:if test="${isQuizAttempted == false}">
            <g:form controller="quizAttempt" action="create2">
                <input type="hidden" name="quizId" value="${quiz.id}" />

                <g:each in="${quiz.questions}" var="question" status="idx">
                    <h2>Question ${idx + 1}: ${question.text}</h2>
                    <ul>
                        <g:each in="${question.answers}" var="answer">
                            <li>
                                <label>
                                    <g:checkBox name="answers[${idx}]" value="${answer.id}" checked="false"/>
                                    ${answer.text}
                                </label>
                            </li>
                        </g:each>

                    </ul>
                </g:each>

                <input type="submit" value="Submit Quiz" />
            </g:form>
        </g:if>
    </div>

</div>
</body>
</html>
