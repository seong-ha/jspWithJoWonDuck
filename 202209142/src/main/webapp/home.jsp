<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		String[] names = new String[5];
	%>
	
	<% 
		request.setCharacterEncoding("utf-8");
		names[0] = "홍길동";
		names[1] = "박길동";
		names[2] = "장길동";
		names[3] = "대길동";
		names[4] = "소길동";
	%>
	<h1>넘어온 id 데이터는 = ${param.id}</h1>
	<c:if test="${not empty param.name}">
		<h1>넘어온 name 데이터는 = ${param.name}</h1>
	</c:if>
	<h1>넘어온 password 데이터는 = ${param.password}</h1>
	
	<c:forEach  var="i" begin="0" end="10">
		2 * ${i} = ${2 * i} <br>
	</c:forEach>

	<c:choose>
		<c:when test="${empty param.id}">
			<h3>아이디가 비어있다.</h3>
		</c:when>
		<c:when test="${empty param.name}">
			<h3>이름이 비어있다.</h3>
		</c:when>
		<c:when test="${empty param.password}">
			<h3>비밀번호가 비어있다.</h3>
		</c:when>
		<c:otherwise>
			<h3>정상 동작</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>