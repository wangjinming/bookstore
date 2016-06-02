<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车详情</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#getBooks, #deleteFromCart, #clearCart").click(function() {
			var href = this.href + "?" + $(":hidden").serialize();
			$(this).attr("href", href);
		});
		
		$(":text").change(function(){
			var id = $(this).attr("name");
			var preCount = $(this).attr("class");
			var curCount = $(this).val();
			if(preCount != curCount) {
				var url = "${pageContext.request.contextPath }/book/updateQuantity";
				var argus = {"id":id, "quantity": curCount};
				
				$.post(url, argus, function(data){
					$("#bookNumber").text("你的购物车里一共有 " + data.totalQuantity + " 本图书");
					$("#totalMoney").text("总价格 " + data.totalMoney);
				}, "JSON");
			}
			
		});
	})
	
</script>
</head>
<body>
	<input type="hidden" name="pageNo" value="${param.pageNo}">
	<input type="hidden" name="minPrice" value="${param.minPrice}">
	<input type="hidden" name="maxPrice" value="${param.maxPrice}">
	
	<center>
		<h3>购物车详情</h3>
		<br>
		<div id="bookNumber">你的购物车里一共有 ${sessionScope.ShoppingCart.bookNumber } 本图书</div>
		<br><br>
		<table cellpadding="5">
			<thead>
				<tr align="left">
					<th>书名</th>
					<th>数量</th>
					<th>价格</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach var="item" items="${sessionScope.ShoppingCart.items }">
				<tr align="left">
					<td>${item.book.title }</td>
					<td><input class="${item.quantity }" type="text" size="1" name="${item.book.id }" value="${item.quantity }"/></td>
					<td>${item.book.price }</td>
					<td><a id="deleteFromCart" href="${pageContext.request.contextPath }/book/deleteFromCart?id=${item.book.id }">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><div id="totalMoney">总价格：${sessionScope.ShoppingCart.totalMoney } </div></td>
			</tr>
			<tr>
				<td colspan="4">
					<a id="getBooks" href="${pageContext.request.contextPath }/book/getBooks">继续购物 </a>&nbsp;
					<a id= "clearCart"href="${pageContext.request.contextPath }/book/clearCart">清空购物车</a>&nbsp;
					<a href="${pageContext.request.contextPath }/book/goCash">结账 </a>	
				</td>
			</tr>
		</table>
	</center>
</body>
</html>