<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

	<form:form action="${pageContext.request.contextPath}/logout" method="POST">

        <input type="submit" value="Logout" />

    </form:form>
</body>

</html>