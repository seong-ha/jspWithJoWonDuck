<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th {
		width: 100;
	}
</style>
</head>
<body>
	<div align="center">
		<div>
			<table border="1">
				<tr>
					<th>작성자</th>
					<td>
						${vo.noticeWriter}
					</td>
					<th>작성일자</th>
					<td>
						${vo.noticeDate}
					</td>
					<th>조회수</th>
					<td>
						${vo.noticeHit}
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5">
						${vo.noticeTitle}
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5">
						<textArea rows="10" cols="88" readonly="readonly">${vo.noticeSubject}</textArea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5"></td>
				</tr>
			</table>
		</div><br>
		<div>
			<c:if test="${vo.noticeWriter eq name}">
				<button type="button" onclick="subCall('E')">수정</button>&nbsp;&nbsp;
				<button type="button" onclick="subCall('D')">삭제</button>&nbsp;&nbsp;
			</c:if>
			<button type="button" onclick="location.href='noticeSelectList.do'">목록</button>
		</div>
		<div>
			<form id="frm" action="" method="post">
				<input type="hidden" id="id" name="id" value="${vo.noticeId}">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function subCall(str) {
			if (str == 'E') {
				frm.action = "noticeEditForm.do";  // 수정
			} else if (str == 'D') {
				frm.action = "noticeDelete.do"; // 삭제
			}
			frm.submit();
		}
	</script>
</body>
</html>