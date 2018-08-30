<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainmenu">
	<ul>

		<c:forEach var="menu" items="${menuList}">
			<c:if test="${menu=='DASHBOARD'}">
				<li><a href="dashboard.do"><span class="dashboard-icon"></span>Dashboard</a></li>
			</c:if>
			<c:if test="${menu=='NEW LEAD'}">
				<li><a href="newlead.do"><span class="leads-icon"></span>New Lead</a></li>
			</c:if>
			<c:if test="${menu=='WORKLIST'}">
				<li><a href="workList.do"><span class="worklist-icon"></span>Worklist</a></li>
			</c:if>
			<c:if test="${menu=='SEARCH AND ALLOCATE'}">
				<li><a href="serchAllocate.do"><span class="settings-icon"></span>Search & Allocate</a></li>
			</c:if>
		</c:forEach>
		 <li><a href="travelSummary.do"><span class="dashboard-icon"></span>Travel Summary</a></li>
						<li><a href="dailyActivity.do"><span class="dashboard-icon"></span>Daily Activity</a></li> 
	</ul>
</div>
