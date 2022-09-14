<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div>
			<jsp:include page="menu.jsp" />
		</div>
		<div><h1>로 그 인</h1></div>
		<div>
			<form action="FirstServlet" method="post">
				<div>
					<table border="1">
						<tr>
							<th>아이디</th>
							<td>
								<input type="text" id="id" name="id" placeholder="ID는 느낌있게">
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								<input type="text" id="name" name="name" placeholder="이름은 아름답게">
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input type="password" id="password" name="password" placeholder="비번은 은밀하게">
							</td>
						</tr>
					</table>
				</div><br>
				<div>
					<input type="submit" value="로그인">
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
	</div>
	<a href="home.jsp">홈페이지</a>
</body>
</html>