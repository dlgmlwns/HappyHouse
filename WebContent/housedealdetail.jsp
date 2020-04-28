<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%@ include file="/menu.jsp"%>
<div align="center">
	<h2>조회한 주택 거래 정보</h2>
	<c:choose>
		<c:when test="${empty housedeal.img }">
			<img alt="" src="img/aptDefault.jpg" width="300" height="300"><br>
		</c:when>
		<c:otherwise>
			<img alt="" src="img/${housedeal.img }" width="300" height="300"><br>
		</c:otherwise>
	</c:choose>
	<br>	
	<table class="table table-striped table-bordered table-hover">
        <tbody>
            <tr>
                <td>거래번호</td>
                <td>${ housedeal.no }</td>
 
            </tr>
            <tr>
                <td>동</td>
                <td>${ housedeal.dong }</td>
            </tr>
            <tr>
                <td>아파트 이름</td>
                <td>${ housedeal.aptName }</td>
            </tr>
            <tr>
                <td>동 코드</td>
                <td>${ housedeal.code }</td>
            </tr>
            <tr>
                <td>거래 금액</td>
                <td>${ housedeal.dealAmount }</td>
            </tr>
             <tr>
                <td>건축년도</td>
                <td>${ housedeal.buildYear }</td>
            </tr>
             <tr>
                <td>거래일</td>
                <td>${ housedeal.dealYear }.${ housedeal.dealMonth }.${ housedeal.dealDay }</td>
            </tr>
             <tr>
                <td>크기</td>
                <td>${ housedeal.area }</td>
            </tr>
             <tr>
                <td>층</td>
                <td>${ housedeal.floor }</td>
            </tr>
             <tr>
                <td>지번</td>
                <td>${ housedeal.jibun }</td>
            </tr>
             <tr>
                <td>타입</td>
                <td>${ housedeal.type }</td>
            </tr>
        </tbody>
    </table>
	
	<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
	<a href="${root }/housedeal.do?act=list">주택 거래 목록</a>
	</div>
</body>
</html>