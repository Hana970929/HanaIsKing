<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<form action="updateAction">
		<input type="text" name="id" value="${id}" readonly/>
		<input type="text" name="pw" value="${pw}"/>
		<input type="text" name="point" value="${point}"/>
		<input type="text" name="name" value="${name}"/>
		<input type="submit" value="작성 완료">
	</form>
</body>
</html>
