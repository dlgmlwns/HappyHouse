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

<title>Insert title here</title>
</head>
<body>
<%@ include file="/menu.jsp"%>
	<div align="center">	
	<table class="table table-striped table-bordered table-hover">
        <tbody>
            <tr>
                <td>아이디</td>
                <td>${userdetail.id }</td>
 
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>${userdetail.password }</td>
            </tr>
            <tr>
                <td>이름</td>
                <td>${userdetail.name }</td>
            </tr>
            <tr>
                <td>주소</td>
                <td>${userdetail.address }</td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td>${userdetail.phone }</td>
            </tr>
        </tbody>
    </table>
	
	<c:set var = "root" value ="${pageContext.request.contextPath }"></c:set>
   <a href="${root }/user.do?act=mvupdate&id=${userdetail.id}">회원정보수정</a>
   <a href="${root }/user.do?act=index">확인</a>
   </div>
	
</body>
</html>