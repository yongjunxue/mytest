<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script> 
</head>
<body>
	goods.........
	<table>
		<tr>
				<td>sku</td>
				<td>商品名称</td>
				<td>规格型号</td>
		<tr>
		<c:forEach items="${goodsList}" var="item">
			<tr>
				<td>${item.sku}</td>
				<td>${item.name}</td>
				<td>${item.brand}</td>
			</tr>
		</c:forEach>
	</table>
	<form action="" id="myform">  
	    商品编码<input id="sku" type="text" name="sku"/>  
	    商品名称<input id="name" type="text" name="name"/> 
	    <input id="page" type="hidden" name="page" value="1"/>  
	    <input id="pageSize" type="hidden" name="pageSize" value="10"/>  
<!-- 	    性别<input type="radio" name="sex" value="男人">man  
	    <input type="radio" name="sex" value="女人">woman   -->
	</form> 
	<a href="#">search</a>
	<div>  
    <p id="divp">before..</p>  
</div>    	
</body>
<script>
$(document).ready(function(){  
    $("a").click(function(){  
    	var sku=$("#sku").val();
    	var name=$("#name").val();
    	var page=$("#page").val();
    	var pageSize=$("#pageSize").val();
    	var param='{\"param\":{\"query\":{\"sku\":\"33e+\",\"name\":\"\",\"page\":\"1\",\"pageSize\":\"10\"}}}';
    	param={
    		query:{
    			sku:sku,
    			name:name,
    			page:page,
    			pageSize:pageSize
    		}	
    	} 
    	param="{\"param\":{\"query\":{\"sku\":\"33e+\",\"name\":\"\",\"page\":\"1\",\"pageSize\":\"10\"}}}";
        $.ajax({  
            type:'post',  
            url:'/action/goods/getGoodsByPage',  
            data:JSON.stringify({param:{query:{}}}),  
            cache:false,  
            dataType:'json',  
            traditional:true,
            contentType: "application/json; charset=utf-8",
            success:function(data){  
	            alert(data.status.msg);
            }  
        });  
    });  
});  
</script>
</html>