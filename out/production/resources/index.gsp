<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails E-Learning platform</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>

    <sec:ifLoggedIn>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Welcome <sec:username/><span class="caret"></span></a>
            <ul class="dropdown-menu">

                <li class="dropdown-item"><a href="#">Profile</a></li>
                <li class="dropdown-item"><g:link controller="logout">Logout</g:link> </li>
            </ul>
        </li>


    </sec:ifLoggedIn>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome to Grails E-Learning platform</h1>
        <sec:ifNotLoggedIn><h3>Please Log in to gain access to the course materials.</h3></sec:ifNotLoggedIn>

        <span>
            Explore our online teaching and workplace training solutions.
            Here teachers can set up virtual courses to upload course material, communicate with students, give tests or provide recordings.


        </span>



%{--        <sec:ifLoggedIn>--}%
%{--            <p>--}%
%{--                Below is a list of controllers that are currently deployed in--}%
%{--                this application, click on each to execute its default action:--}%
%{--            </p>--}%
%{--        </sec:ifLoggedIn>--}%
        <sec:ifLoggedIn>

            <div id="controllers" role="navigation">
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_INSTRUCTOR">
                <h2>Available Controllers:</h2>
                </sec:ifAnyGranted>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <g:if test="${c.name!= 'Login' && c.name!= 'Logout'}">
                                <li class="controller">
                                    <g:link controller="${c.logicalPropertyName}">${c.name}</g:link>
                                </li>
                            </g:if>
                    </sec:ifAllGranted>

                        <sec:ifAllGranted roles="ROLE_INSTRUCTOR">
                            <g:if test="${c.name== 'Enrollment'}">
                                <li class="controller">
                                    <g:link controller="${c.logicalPropertyName}">${c.name}</g:link>
                                </li>
                            </g:if>
                        </sec:ifAllGranted>
                    </g:each>
                </ul>
            </div>
        </sec:ifLoggedIn>
    </section>
</div>

</body>
</html>
