<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<title>Student Confirmation</title>
</head>

<body>

The student is confirmed: ${student.firstName} ${student.lastName}

<br><br>

Country: Code = ${student.countryCode}, Value = ${student.countryValue}

<br><br>

Favorite language: ${student.favoriteLanguage}

</body>
