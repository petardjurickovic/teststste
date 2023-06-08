<%@ page import="spec.test.Category" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/17/2022
  Time: 2:19 PM
--%>

<g:each in="${categories}" var="category">
    <pre>${"    "*depth}<a href="${createLink(controller:'course',action:'index', params: [category: category.id])}">&#8594;${category.title}</a></pre>
    <g:render template="cat" model="[categories:spec.test.Category.findAllByParent(category), depth: depth+1]" />
</g:each>