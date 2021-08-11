<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>메인페이지</h1>
${name}(${id})님 안녕하세요. <br/>
포인트 : ${point} 점 <a href="logout"><button>로그아웃</button></a><br/>
<h1>구입할 콘텐츠를 선택하세요</h1>
<table>
	<tr>
		<td><a href="purchase?price=100000&subject=intro"><img src="resources/img/Intro_350_408.png"></a></td>
		<td><a href="purchase?price=500000&subject=java"><img src="resources/img/Java_350_408.png"></a></td>
		<td><a href="purchase?price=300000&subject=cpp"><img src="resources/img/Cpp_350_408.png"></a></td>
	</tr>
	<tr>
		<td>100,000 포인트</td>
		<td>500,000 포인트</td>
		<td>300,000 포인트</td>
	</tr>
</table>
<br/>
&lt;광고&gt;<br/>
<a href="pointup"><img src="resources/img/korea_it.png"></a>
</body>
</html>
