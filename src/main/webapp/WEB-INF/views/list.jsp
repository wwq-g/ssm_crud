<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
</head>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${APP_PATH}/static/js/jquery.min.js"></script>
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table table-striped table-hover">
					<tr>
						<th>ID</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list}" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender=="M"?"男":"女"}</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>
								<button type="button" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button type="button" class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">当前第${pageInfo.pageNum}页,总${pageInfo.pages}页,总共有${pageInfo.total}条记录</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">

					</ul>
				</nav>
				<!-- 分页条信息 -->
				<div class="col-md-6">
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
							<c:if test="${pageInfo.hasPreviousPage }">
								<li><a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1 }"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:if>
							<c:forEach items="${ pageInfo.navigatepageNums }" var="page_Num">
								<c:if test="${page_Num==pageInfo.pageNum }">
									<li class="active"><a href="#">${page_Num }</a></li>
								</c:if>
								<c:if test="${page_Num!=pageInfo.pageNum }">
									<li><a href="${APP_PATH}/emps?pn=${page_Num }">${page_Num }</a></li>
								</c:if>

							</c:forEach>
							<c:if test="${pageInfo.hasNextPage }">
								<li><a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1 }"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>

							</c:if>
							<li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</li>
						</ul>
					</nav>
				</div>
			</div>

		</div>

	</div>

</body>
</html>