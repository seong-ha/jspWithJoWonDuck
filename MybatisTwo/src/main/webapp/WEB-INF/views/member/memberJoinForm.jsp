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

<body>
	<div align="center">
		<div>
			<h1>멤버 가입</h1>
		</div>
		<div>
			<form id="frm" action="memberInsert.do" onsubmit="return formCheck()"
				method="post">
				<div>
					<table border="1">
						<tr>
							<th>아이디</th>
							<td width="200"><input type="text" id="memberId"
								name="memberId" required="required">&nbsp;
								<button type="button" id="btn" value="No" onclick="idCheck()">중복체크</button>
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td colspan="3"><input type="password" id="memberPassword"
								name="memberPassword" required="required"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td colspan="3"><input type="password"
								id="memberPasswordConfirm" required="required"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" id="memberName" name="memberName"
								required="required"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td colspan="3"><input type="tel" id="memberTel"
								name="memberTel"></td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="hidden" name="memberAuthor" value="USER"> <input
						type="submit" value="등록">&nbsp;&nbsp; <input type="reset"
						value="취소">&nbsp;&nbsp; <input type="button" value="목록"
						onclick="location.href='memberSelectList.do'">
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function formCheck() {
			let pass1 = document.getElementById('memberPassword').value; // frm.memberpassword.value 랑 같음
			let pass2 = document.getElementById('memberPasswordConfirm').value;
			let idChecked = document.getElementById('btn').value;

			// 아이디 중복체크여부 부터 확인시킴
			if (idChecked == 'No') {
				alert("아이디 중복 체크를 해주세요.");
				return false;
			}

			if (pass1 != pass2) {
				alert("패스워드가 일치하지 않습니다.");
				document.getElementById('memberPassword').value = "";
				document.getElementById('memberPasswordConfirm').value = "";
				document.getElementById('memberPassword').focus();
				return false;
			}

			return true;
		}

		// Ajax를 통해서 id 중복체크를 한다.
		function idCheck() {
			let id = document.getElementById('memberId').value;

			/* 교수님 XMLHttpRequest
			const xhttp = new XMLHttpRequest();
		    xhttp.onload = function() {
		    	if (this.readyState == 4 && this.status == 200) {
		    		
		    	    if (this.responseText == '1') {
						alert("사용 가능한 아이디 입니다.");
						document.getElementById('btn').value = 'Yes';
						document.getElementById('memberPassword').focus();
		    	    } else {
		    	    	alert("사용할 수 없는 아이디 입니다.");
		    	    	document.getElementById('memberId').value = "";
		    	    	document.getElementById('memberId').focus();
		    	    }
		    	}  // 실패하면 else 이후callBack함수
			}
		    xhttp.open("GET", "ajaxMemberIdCheck.do?id=" + id);
		    xhttp.send();
		    */
		    
		 	// 교수님
		    fetch('ajaxMemberIdCheck.do?id=' + id)
		    .then(response => response.text())
			.then(data => responseResult(data));  // 이 곳에 Call Back함수를 작성하면 됨.

		    // fetch 처리 CallBack함수
		    function responseResult(data) {
		    	 if (data == '1') {
						alert("사용 가능한 아이디 입니다.");
						document.getElementById('btn').value = 'Yes';
						document.getElementById('memberPassword').focus();
		    	    } else {
		    	    	alert("사용할 수 없는 아이디 입니다.");
		    	    	document.getElementById('memberId').value = "";
		    	    	document.getElementById('memberId').focus();
		    	    } // 실패하면 callBack함수
		    }
			
			
			/*
		    let xhttp = new XMLHttpRequest();
		    xhttp.open('post', 'ajaxMemberIdCheck.do');
		    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		    xhttp.send('id=' + id);
		    xhttp.onload = function (e) {
		    	console.log('this: ' + this);
		    	console.log('e: ' + e);
		    	console.log(xhttp);

		    	let result = xhttp.text();
                console.log(result);
                if (`${check}` == '1') {
						alert("사용 가능한 아이디 입니다.");
						document.getElementById('btn').value = 'Yes';
						document.getElementById('memberPassword').focus();
		    	} else {
		    	    	alert("사용할 수 없는 아이디 입니다.");
		    	    	document.getElementById('memberId').value = "";
		    	    	document.getElementById('memberId').focus();
		    	}
            }
		    */

		    
			/* 
			fetch('ajaxMemberIdCheck.do', {
					method: 'post',
					headers: {
						'Content-type': 'application/x-www-form-urlencoded'
					},
					body: 'id=' + id
			})
			.then(result => result.text())
			.then(result => {
				console.log(result);
				if (result == '1') {
					alert("사용 가능한 아이디 입니다.");
					document.getElementById('btn').value = 'Yes';
					document.getElementById('memberPassword').focus();
				} else {
					alert("사용할 수 없는 아이디 입니다.");
					document.getElementById('memberId').value = "";
					document.getElementById('memberId').focus();
				}
			})
			.catch(error => console.error(error));
		    */
		    
		    
		}
	</script>
</body>

</html>