<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="encodedImage" required="true" %>

<c:choose>
	<c:when test="${not empty encodedImage}">
		<img class="img-thumbnail sized"
			src="data:image/jpeg;base64,${encodedImage}" alt="product-image" />
  	</c:when>
	<c:otherwise>
		<img class="img-thumbnail sized"
			src="${pageContext.request.contextPath}/static/images/unknown-image.jpg" alt="product-image" />
	</c:otherwise>
</c:choose>
