<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 4/22/2022
  Time: 3:37 PM
--%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>${module?.title}</title>
</head>
<body>
<div class="row">
    <div class="col-lg-8">
        <div class="card">
            <div class="card-header">
                <h5 class="title">${module?.title}</h5>
            </div>
            <div class="card-body">
                <p>${module?.summary}</p>
                <div class="table-responsive">
                    <table class="table">
                        <thead class=" text-primary">
                        <th>Title</th>
                        <th>Summary</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                        <g:each var="lesson" in="${module?.lessons.sort{ it.lessonOrderNum }}">
                            <tr>
                                <td>
                                    ${lesson?.title}
                                </td>
                                <td>
                                    ${lesson?.summary}
                                </td>
                                <td class="text-right">
                                    <a href="${createLink(controller:'lesson',action:'details',id:lesson.id, params: [course: lesson?.module?.course?.id])}" class="btn btn-primary"><g:message code="go.label" /> <i class="fas fa-angle-right"></i></a>
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
                        <h5 class="title">${module?.course?.title}</h5>
                    </a>
                    <p class="description" style="color: black;">
                        ${module?.course?.description}
                    </p>
                    <p class="description" style="color: black;">
                        ${module?.course?.instructor?.username}
                    </p>
                    <g:if test="${isInstructor == true}">
                        <a href="${createLink(controller:'module',action:'show',id:module?.id)}" class="btn btn-primary">Edit</a>
                    </g:if>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
