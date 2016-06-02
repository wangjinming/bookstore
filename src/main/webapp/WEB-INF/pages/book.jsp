<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书详细信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").click(function() {
			var href = this.href + "?" + $(":hidden").serialize();
			$(this).attr("href", href);
		});
	})
</script>
</head>
<body>
<center>
	<h3>图书详细信息</h3>
	<input type="hidden" name="pageNo" value="${param.pageNo}">
	<input type="hidden" name="minPrice" value="${param.minPrice}">
	<input type="hidden" name="maxPrice" value="${param.maxPrice}">
	<table cellpadding="10">
		<tr>
			<td>书名</td>
			<td>${book.title}</td>
		</tr>
		<tr>
			<td>作者</td>
			<td>${book.author}</td>
		</tr>
		<tr>
			<td>单价</td>
			<td>${book.price}</td>
		</tr>
		<tr>
			<td>出版时间</td>
			<td>${book.publishingDate}</td>
		</tr>
		<tr>
			<td>评价</td>
			<td>${book.remark}</td>
		</tr>
		<tr>
			<td align="center" colspan=2><a href="${pageContext.request.contextPath}/book/getBooks">继续购物</a></td>
		</tr>
	</table>
</center>
</body>
</html>