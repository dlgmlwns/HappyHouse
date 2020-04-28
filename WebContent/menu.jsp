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
<div align="right">


<c:if test="${userinfo == null}">
<h6>${msg}</h6>
<a href="${root}/user.do?act=mvjoin">회원가입</a>&nbsp&nbsp
<a href="${root}/user.do?act=mvlogin">로그인</a>&nbsp&nbsp<br>
</c:if>
<c:if test="${userinfo != null}">
<div>
<strong>${userinfo.name}</strong>님 환영합니다.
<a href="${root}/user.do?act=logout">로그아웃</a>&nbsp&nbsp
<a href="${root}/user.do?act=userinfo&id=${userinfo.id}">회원정보</a>&nbsp&nbsp<br>
</div>
</c:if>
</div>

<!-- 상단 메뉴바{s} -->

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

  <a class="navbar-brand" href="${root}/user.do?act=index">Happy house</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>

  <div class="collapse navbar-collapse" id="navbarsExample03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="#">공지사항 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">오늘의 뉴스</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">주변 탐방</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">관심지역 설정</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-md-0">
      <input class="form-control" type="text" placeholder="Search">
    </form>
  </div>
</nav>
<br><br>
</body>
</html>