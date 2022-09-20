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
			<form id="frm" action="noticeEdit.do" method="post" enctype="multipart/form-data">
				<div>
					<table border="1">
						<tr>
							<th>작성자</th>
							<td>
								${vo.noticeWriter}
							</td>
							<th>작성일자</th>
							<td>
								<input type="date" id="noticeDate" name="noticeDate" value="${vo.noticeDate}" required="required">
							</td>
							<th>조회수</th>
							<td>
								${vo.noticeHit}
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="5">
								<input type="text" size="87" id="noticeTitle" name="noticeTitle" value="${vo.noticeTitle}" required="required">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="5">
								<textArea rows="10" cols="88" id="noticeSubject" name="noticeSubject">${vo.noticeSubject}</textArea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="5">
								<input type="file" id="file" name="file" value="${vo.noticeAttech}">
							</td>
						</tr>
					</table>
				</div><br>
				<div>
					<input type="hidden" id="noticeId" name="noticeId" value="${vo.noticeId}">
					<input type="submit" value="수정">&nbsp;&nbsp;
					<input type="button" onclick="location.href='noticeSelectList.do'"value="목록">
				</div>
			</form>
		</div>
	</div>
</body>
</html>