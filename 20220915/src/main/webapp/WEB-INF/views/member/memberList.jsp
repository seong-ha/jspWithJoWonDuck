<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<style>
/* Google Web Font */
@import url('https://fonts.googleapis.com/css?family=Montserrat:300,400,500&display=swap');
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
    width: 900px;
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
table th,
table td {
    padding: 12px;
    text-align: center; 
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

.secondTr:hover {
	background-color: yellow;
}
	</style>
</head>

<body>
	<div align="center">
		<div>
			<h1>멤버 전체 목록</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>권한</th>
				</tr>
				<c:forEach items="${members}" var="member">
					<tr class="secondTr" onclick="selectMember('${member.memberId}')">
						<td>${member.memberId}</td>
						<td>${member.memberName}</td>
						<td>${member.memberTel}</td>
						<td>${member.memberAuthor}</td>
					</tr>
				</c:forEach>
			</table>
		</div><br>
		<div>
			<button type="button" onclick="location.href='memberJoinForm.do'">멤버추가</button>&nbsp;&nbsp;
			<button type="button" onclick="location.href='main.do'">홈으로</button>
		</div>
		<div>
			<form id="frm" action="MemberSelect" method="post">
				<input type="hidden" id="id" name="id">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function selectMember(id) {
			frm.id.value = id;
			frm.action = "memberSelect.do";
			frm.submit();
		}	
	</script>
</body>

</html>