<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Company luv2code Home Page</title>
</head>

<body>
	<h2>Company luv2code Home Page</h2>
	<hr>

    <p>
	Welcome to the luv2code company home page!
	</p>

	<hr>
    	<!-- display user name and role -->

    	<p>
    		User: <security:authentication property="principal.username" />
    		<br><br>
    		Role(s): <security:authentication property="principal.authorities" />
    	</p>

    <hr>

	<form:form action="${pageContext.request.contextPath}/logout" method="POST">

        <input type="submit" value="Logout" />

    </form:form>
</body>

</html>