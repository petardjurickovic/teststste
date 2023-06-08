
<div>

    <ol class="property-list">
        <li class="fieldcontain"><span class="property-label">Students:</span></li>
    <g:each in="${course.enrollments.student}" var="student">
        <li class="fieldcontain"><div class="property-value">${student}</div></li>
    </g:each>
    </ol>
</div>
