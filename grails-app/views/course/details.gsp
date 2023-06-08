<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/21/2023
  Time: 3:50 PM
--%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>${course?.title}</title>
</head>
<body>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div class="row">
    <div class="col-lg-8">
        <div class="card">
            <div class="card-header">
                <h5 class="title">Modules</h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class=" text-primary">
                        <th>Title</th>
                        <th>Summary</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                        <g:each var="module" in="${course?.modules.sort{ it.moduleOrderNum }}">
                            <tr>
                                <td>
                                    ${module?.title}
                                </td>
                                <td>
                                    ${module?.summary}
                                </td>
                                <td class="text-right">

                                    <a href="${createLink(controller:'module',action:'details',id:module.id, params: [course: course?.id])}" class="btn btn-primary"><g:message code="go.label" /> <i class="fas fa-arrow"></i></a>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="card card-user">
            <div class="image">
%{--                <img src="${course?.banner}" alt="...">--}%
            </div>
            <div class="card-body">
                <div class="author">
                    <a href="#">
%{--                        <img class="avatar border-gray" src="${course?.banner}" alt="...">--}%
                        <h5 class="title">${course?.title}</h5>
                    </a>
                    <p class="description" style="color: black;">
                        ${course?.description}
                    </p>
                    <p class="description" style="color: black;">
                        ${course?.instructor?.username}
                    </p>
                    <g:if test="${enrolled == false}">
                    <a href="${createLink(controller:'enrollment',action:'create',id:course?.id, params: [title: course?.title])}" class="btn btn-primary"><g:message code="enroll.label" /></a>
                    </g:if>
                    <g:else>
                        You are enrolled in this course!
                        <br/>
                        <a href="${createLink(controller:'enrollment',action:'delete2',id:course?.id)}" class="btn btn-primary"><g:message code="unenrol.label" /></a>
                    </g:else>
                    <g:if test="${isInstructor == true}">
                        <a href="${createLink(controller:'course',action:'show',id:course?.id)}" class="btn btn-primary">Edit</a>
                    </g:if>


    </div>
            <g:if test="${enrolled == true}">

                <span>Quizes:</span>
                <tr>

                <g:each var="quiz" in="${quizes}">
                        <td>
                            <br>
                            <a href="${createLink(controller:'quiz',action:'show',id : quiz.id, params: [student: currentUser.id])}"> ${quiz?.title}</a>
                        </td>
                </g:each>
                </tr>

            </g:if>
</div>
</div>
</div>
</div>
</body>
</html>

