<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/4/2022
  Time: 10:30 AM
--%>

<%@ page import="spec.test.Lesson" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="layout" content="main">
    <title>${lesson.title}</title>
</head>
<body>
<div class="row">
    <div class="col-lg-8">
        <div class="card">
            <div class="card-header">
                <h2>${lesson.title}</h2>
                <a href="#">${lesson?.module?.course?.title}</a>
            </div>
            <div class="card-body">
                <g:if test="${lesson.videoURL}">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${lesson?.videoURL}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>
                </g:if>
                <hr>
                <p>${lesson.summary}</p>
            </div>
            <div class="card-footer text-center">
                <%
                    def prev=Lesson.findByModuleAndLessonOrderNum(lesson.module,lesson.lessonOrderNum-1)
                    def next=Lesson.findByModuleAndLessonOrderNum(lesson.module,lesson.lessonOrderNum+1)
                %>
                <g:if test="${prev}">
                    <a href="${createLink(controller:'lesson',action:'details',id:prev.id, params: [course: lesson?.module?.course?.id])}" class="btn btn-primary"><i class="fas fa-angle-left"></i> ${prev?.title}</a>
                </g:if>
                <a href="${createLink(controller:'course',action:'details',id:lesson.module.course.id)}" class="btn btn-primary"><i class="fas fa-list"></i>Module</a>
                <g:if test="${next}">
                    <a href="${createLink(controller:'lesson',action:'details',id:next.id, params: [course: lesson?.module?.course?.id])}" class="btn btn-primary">${next?.title} <i class="fas fa-angle-right"></i></a>
                </g:if>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="card">
            <div class="card-header">
            </div>
            <div class="card-body">
                <h6>Summary</h6>
                ${lesson?.summary}
                <h6>Files</h6>
                <g:each var="file" in="${lesson?.files?}">
                    <li class="dropdown-item">
                        <g:link action="downloadFile" controller="file" id="1"  params="[url: file.url]">
                            ${file.title}
                        </g:link>
                    </li>
                </g:each>

%{--                <g:if test="${lesson?.files}">--}%
%{--                    <table>--}%
%{--                        <thead>--}%
%{--                        <th>Titulo</th>--}%
%{--                        <th></th>--}%
%{--                        </thead>--}%
%{--                        <tbody>--}%
%{--                        <tr>--}%
%{--                            <td>Nombre del archivo</td>--}%
%{--                            <td><a href="#" class="btn btn-primary btn-md">G</a></td>--}%
%{--                        </tr>--}%
%{--                        </tbody>--}%
%{--                    </table>--}%
%{--                </g:if>--}%
%{--                <g:else>--}%
%{--                    No hay archivos--}%
%{--                </g:else>--}%
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <h6>Teacher</h6>
                ${lesson?.module?.course?.instructor?.username}
            </div>

                <g:if test="${isInstructor == true}">
                    <div class="card-header">
                    <a href="${createLink(controller:'lesson',action:'show',id:lesson.id)}" class="btn btn-primary">Edit</a>
                    </div>
                </g:if>


        </div>
    </div>
</div>
</body>
</html>
