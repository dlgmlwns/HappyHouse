<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("#updateBtn").click(function() {
		
		if($("#username").val() == "") {
			alert("이름 입력!!!");
			return;
		} else if($("#userid").val() == "") {
			alert("아이디 입력!!!");
			return;
		} else if($("#userpwd").val() == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			$("#memberform").attr("action", "${root}/user.do?act=update").submit();
		}
	});
	
	$("#deleteBtn").click(function() {		
				$("#memberform").attr("action", "${root}/user.do?act=delete").submit();

		});
		 
	 
	
});
</script>
</head>
<body>

<%@ include file="/menu.jsp"%>


<div class="container" align="center">
<c:if test="${msg != null}">
<h3>${msg}</h3>
</c:if>
	<div class="col-lg-6" align="center">
		<form id="memberform" method="post" action="">
		<input type="hidden" name="act" id="act" value="">
			<div class="form-group" align="left">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="username" name="name" value="${userinfo.name }">
			</div>
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="userid" name="id" value="${userinfo.id }"readonly="readonly">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="text" class="form-control" id="userpwd" name="password" value="${userinfo.password }"readonly="readonly">
			</div>
			
			<div class="form-group" align="left">
				<label for="">주소</label>
				<input type="text" class="form-control" id="useradd" name="address" value="${userinfo.address }">
			</div>
			
			<div class="form-group" align="left">
				<label for="">전화번호</label>
				<input type="text" class="form-control" id="userphone" name="phone" value="${userinfo.phone }">
			</div>
			
			<div class="form-group" align="center">
				<button type="button" class="btn btn-primary" id="updateBtn">회원정보 수정</button>
				<button type="button" class="btn btn-warning" id="deleteBtn">탈퇴</button>
			</div>
		</form>
	</div>
</div>


</body>
</html>