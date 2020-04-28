<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, com.ssafy.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	function pageMove(pg) { 
		document.getElementById("pg").value=pg;
		document.getElementById("pageform").submit();
		
	}
</script>
<title>주택 거래 정보</title>
</head>
<body>
<%@ include file="/menu.jsp"%>
	<c:set var="root" value="${pageContext.request.contextPath }" />
	<div align="center">
	<h1>주택 거래 목록 화면</h1>
	<form id="pageform" method="get" action="${root}/housedeal.do">	
	<input type="hidden" name="pg" id="pg" value="">
		<input type="checkbox" name="type" value="1">아파트 매매 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="type" value="2">아파트 전월세&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="type" value="3">다세대,주택 매매&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="type" value="4">다세대,주택 전월세&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>

	
		<input type="hidden" name="act" value="search">
		<select name="orderby">
			<option value="no">번호순
			<option value="name">이름순
			<option value="price">가격순
		</select>
		<select name="by">
			<option value="name">이름
			<option value="dong">동
			<option value="price">가격
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<form>
		<table border='1'>
			<tr>
				<th>번호</th>
				<th>동</th>
				<th>아파트 이름</th>
				<th>거래 금액</th>
				<th>거래 종류</th>
			</tr>
			<c:forEach items="${housedeals }" var="housedeal">
				<tr>
					<td>${housedeal.no }</td>
					<td>${housedeal.dong }</td>
					<td><a href="${root }/housedeal.do?act=detail&no=${housedeal.no }"> ${housedeal.aptName }</td>
					<td>${housedeal.dealAmount }</td>
					<td>${housedeal.type }</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<table>
	  	<tr>
	  	<td>
	  	${navigation.navigator }
	  	</td>
	  	</tr>
	  </table>
	  </div>
</body>
</html>