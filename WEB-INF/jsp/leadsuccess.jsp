<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty leadid}">
	<p>Your New Lead Id = <c:out value="${leadid}" /></p>
</c:if>
