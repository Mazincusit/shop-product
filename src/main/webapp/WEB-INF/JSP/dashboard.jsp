<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags" %>

<html>
<head>
<jsp:include page="../section/css.jsp" />
</head>
<body>
	<div class="container">
		<form style="margin-bottom: 2px">
			<div class="input-group">
				<input id="searchInput" class="form-control" name="query" value="${query}" placeholder="Search for product...">
				<span class="input-group-btn">
					<button id="clearSearchInput" class="btn btn-default" type="button" title="Clear search input">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
					<button class="btn btn-default" type="submit" name="action" value="Search" title="Search for product">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
		</form>
		<c:choose>
			<c:when test="${products.size() == 0}">
				<h1><c:out value="No products found"></c:out></h1>
			</c:when>
			<c:otherwise>
				<input id="page" type="hidden" value="2">
				<input id="maxPageNumber" type="hidden" value="${maxPageNumber}">

				<form id="productsForm">
					<table class="table table-striped">
						<thead>
							<tr>
								<th class="vertical-align"><input id="checkAll" class="checkbox" type="checkbox"></th>
								<th class="vertical-align">ID</th>
								<th class="vertical-align">Name</th>
								<th class="vertical-align">Country</th>
								<th class="vertical-align">Price</th>
								<th class="vertical-align">Amount</th>
								<th class="vertical-align">Delivery date</th>
								<th class="vertical-align">Image</th>
							</tr>
						</thead>
						<tbody>
							<jsp:include page="./fragment/product-items.jsp"></jsp:include>
						</tbody>
					</table>
				</form>
				<c:if test="${maxPageNumber ge 2}">
					<div id="loadMoreProducts" class="text-center">
						<button class="btn btn-primary">Load more products</button>
					</div>
					<div id="spinner" class="text-center hidden">
						<img class="sized" src="${pageContext.request.contextPath}/static/images/loader.gif" alt="spinner" />
					</div>
				</c:if>
				<button class="btn btn-primary" type="submit" form="productsForm" name="action" value="Refresh" title="Refresh">Refresh</button>
				<button class="btn btn-primary" type="submit" form="productsForm" name="action" value="Edit"
					onclick="verifyIfMoreThan1Checked(event)" title="Edit">Edit</button>
				<button id="deleteProducts" class="btn btn-primary" title="Delete">Delete</button>
			</c:otherwise>
		</c:choose>
		<jsp:include page="../section/javascript.jsp" />
	</div>
</body>
</html>
