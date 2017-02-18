<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var query = "${query}";
	var nextPage = document.getElementById("page").value;
    var ctx = "${ctx}";
	var maxPageNumber = parseInt("${maxPageNumber}");
</script>

<script src="${ctx}/static/javascript/jquery.js"></script>
<script src="${ctx}/static/javascript/app.js"></script>
