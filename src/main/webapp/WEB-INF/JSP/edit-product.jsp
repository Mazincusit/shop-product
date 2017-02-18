<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags" %>

<html>
<head>
<jsp:include page="../section/css.jsp" />
</head>
<body>
	<div class="container">
		<c:set var="ctx" value="${pageContext.request.contextPath}" />

		<form id="editProductForm" action="${ctx}/edit-product" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${product.id}">

			<div class="form-group">
				<label for="name">Name:</label>
				<input type="text" id="name" class="form-control" name="name" value="${product.name}" placeholder="Name">
			</div>
			<div class="form-group">
				<label for="country">Country:</label>
				<input type="text" id="country" class="form-control" name="country" value="${product.country}" placeholder="Country">
			</div>
			<div class="form-group">
				<label for="price">Price:</label>
				<input type="number" step="0.01" id="price" class="form-control" name="price" value="${product.price}" placeholder="Price">
			</div>
			<div class="form-group">
				<label for="amount">Amount:</label>
				<input type="number" id="amount" class="form-control" name="amount" value="${product.amount}" placeholder="Amount">
			</div>
			<div class="form-group">
				<label for="deliveryDate">Delivery Date:</label>
				<input type="date" id="deliveryDate" class="form-control" name="deliveryDate" value="${product.deliveryDate}" placeholder="Delivery Date">
			</div>
			<div class="form-group">
				<label for="image">Image:</label>
				<input type="file" id="image" name="image">
				<product:product-image encodedImage="${imageEncodeService.encodeImage(product.image)}" />
			</div>
		</form>

		<c:if test="${showSaveButton}">
			<button class="btn btn-primary" type="submit" form="editProductForm" name="action" value="Save">Save</button>
		</c:if>

		<c:if test="${showAddButton}">
			<button class="btn btn-primary" type="submit" form="editProductForm" name="action" value="Add">Add</button>
		</c:if>

		<button class="btn btn-primary" value="Cancel" onclick="window.location.href='${ctx}/dashboard'">Cancel</button>
		<jsp:include page="../section/javascript.jsp" />
	</div>
</body>
</html>
