<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Registration Form</title>
</head>
<body>
    <form:form action="process-form" modelAttribute="student">

        First name: <form:input path="firstName"/>

        <br><br>

        Last name: <form:input path="lastName"/>

        <br><br>

        Country:

     		<form:select path="countryCode">

     			<form:options items="${countryOptions}" />

     		</form:select>

        <br><br>

        Favorite language:

        <form:radiobuttons path="favoriteLanguage" items="${favoriteLanguages}"  />

        <br><br>

        <input type="submit" value="Submit"/>

    </form:form>
</body>
</html>