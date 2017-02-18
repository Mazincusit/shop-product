<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags" %>

<c:forEach items="${products}" var="product">
	<tr>
		<td class="vertical-align"><input class="checkbox" type="checkbox" name="id" value="${product.id}"></td>
		<td class="vertical-align"><c:out value="${product.id}" /></td>
		<td class="vertical-align"><c:out value="${product.name}" /></td>
		<td class="vertical-align"><c:out value="${product.country}" /></td>
		<td class="vertical-align"><c:out value="${product.price}" /></td>
		<td class="vertical-align"><c:out value="${product.amount}" /></td>
		<td class="vertical-align"><c:out value="${product.deliveryDate}" /></td>
		<td class="vertical-align"><product:product-image encodedImage="${imageEncodeService.encodeImage(product.image)}" /></td>
	</tr>
</c:forEach>
