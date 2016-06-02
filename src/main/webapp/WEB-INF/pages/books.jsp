<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("a").click(function(){
			var serializeVar = $(":hidden").serialize();
			if(serializeVar != "") {
				var href = this.href + "&" + serializeVar;
				$(this).attr("href", href);
			}			
		});
		
		$("#goto").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			
			if(reg.test(val)){
				//2. 校验 val 在一个合法的范围内： 1-totalPageNumber
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${bookPage.totalPageNumber }")){
					flag = true;
				}
			}
			
			
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			
			//3. 页面跳转
			var href = "${pageContext.request.contextPath}/book/getBooks?pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
</script>
<title>图书浏览</title>
</head>
<body>
	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice}">
	
	<center>
		<br>
		<H3>图书信息浏览</H3>
		<br>
		
		<c:if test="${not empty param.title}">
		  你已将  <font color="red">${param.title}</font> 放入购物车中 
		 <br> <br>
		</c:if>
		<c:if test="${not empty sessionScope.ShoppingCart.items}">
		  你的购物车中已经有 ${sessionScope.ShoppingCart.bookNumber} 本图书 <a href="${pageContext.request.contextPath}/book/getCart?pageNo=${bookPage.pageNo}">查看购物车</a>
		   <br> <br>
		</c:if>
		
		<br>
		<form action="${pageContext.request.contextPath}/book/getBooks" method="POST">
			Price : <input type="text" size="1" name="minPrice"> - 
			        <input type="text" size="1" name="maxPrice">
			        <input type="submit" value="Submit">
			<br><br>
		</form>	
		<table cellpadding="10">
			<c:forEach items="${requestScope.bookPage.list}" var="book">
				<tr>
					<td><a href="${pageContext.request.contextPath}/book/getBook?bookId=${book.id}&pageNo=${bookPage.pageNo}">${book.title}</a></td>
					<td>${book.author}</td>
					<td>${book.price}</td>
					<td><a href="${pageContext.request.contextPath}/book/addToCart?pageNo=${bookPage.pageNo }&id=${book.id}&title=${book.title}">Add to Cart</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
			
		Total ${bookPage.totalPageNumber } Pages | Current ${bookPage.pageNo } Page 
		<c:if test="${bookPage.hasPrev }">
			| <a href="${pageContext.request.contextPath}/book/getBooks?pageNo=1">First </a> 
			| <a href="${pageContext.request.contextPath}/book/getBooks?pageNo=${bookPage.prevPage}">Previous </a>
		</c:if>
		<c:if test="${bookPage.hasNext }">
			| <a href="${pageContext.request.contextPath}/book/getBooks?pageNo=${bookPage.nextPage}">Next </a> 
			| <a href="${pageContext.request.contextPath}/book/getBooks?pageNo=${bookPage.totalPageNumber}">Last </a>
		</c:if>
		| Go <input type="text" name="pageNo" id="goto" size="1"> Page
			  
	</center>
</body>
</html>