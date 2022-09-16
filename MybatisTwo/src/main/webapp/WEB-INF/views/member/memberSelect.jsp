<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* Google Web Font */
@import
	url('https://fonts.googleapis.com/css?family=Montserrat:300,400,500&display=swap')
	;

* {
	box-sizing: border-box;
}

body {
	font-family: 'Montserrat', sans-serif;
	margin: 0;
	color: #333;
	font-size: 15px;
	line-height: 1.6em;
	background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* Table */
table {
	width: 500px;
	background-color: #fff;
	border-collapse: collapse;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
	border-radius: 5px;
	overflow: hidden;
}

table caption {
	font-size: 20px;
	margin-bottom: 30px;
}

table tr {
	border-bottom: 1px solid #eee;
}

table tr:last-child {
	border: none;
}

table tr:nth-child(odd) {
	background-color: #ddd;
}

table th {
	padding: 12px;
	text-align: center;
}

table td {
	padding: 12px;
	text-align: left;
}

table tr th {
	background-color: royalblue;
	color: #fff;
}

table tr th:first-child {
	border-radius: 5px 0 0 0;
}

table tr th:last-child {
	border-radius: 0 5px 0 0;
}

table tr td:last-child {
	color: crimson;
	font-weight: 500;
}

th {
	width: 150px;
}

td {
	width: 200px;
}
</style>
</head>
</head>
<body>
<div align="center">
	<div><h1>멤버 상세 정보</h1></div>
	
	<div>
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="memberId" name="memberId" value="${member.memberId}" readonly="readonly"/>
				</td>
			</tr>
			
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" id="memberPassword" name="memberPassword" value="${member.memberPassword}"/>
				</td>
			</tr>
			
			<tr>
				<th>이름</th>
				<td>
					<input type="text" id="memberName" name="memberName" value="${member.memberName}"/>
				</td>
			</tr>
			
			<tr>
				<th>전화번호</th>
				<td>
					<input type="tel" id="memberTel" name="memberTel" value="${member.memberTel}"/>
				</td>
			</tr>
			
			<tr>
				<th>권한</th>
				<td>
					<input type="text" id="memberAuthor" name="memberAuthor" value="${member.memberAuthor}"/>
				</td>
			</tr>
		</table>
	</div><br>
	<div>
		<form id="frm" method="post">
			<input type="hidden" id="memberId" name="memberId"/>
			<button type="button" onclick="actionForm('memberUpdate')">수정</button>&nbsp;&nbsp;
			<button type="button" onclick="actionForm('memberDelete')">삭제</button>&nbsp;&nbsp;
			<button type="button" onclick="actionForm('memberSelectList')">목록</button>
		</form>
	</div>
</div>
<script type="text/javascript">
	function actionForm(str) {
		switch (str) {
			case 'memberUpdate':
				frm.action = 'memberUpdate.do';
				break;
			case 'memberDelete':
				frm.action = 'memberDelete.do';
				break;
			default:
				frm.action = 'memberSelectList.do';
		}

		frm.submit();
	}
</script>


</body>
</html>