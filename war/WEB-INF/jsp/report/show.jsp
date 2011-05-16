<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hucha" uri="hucha.jar" %>

<hucha:mailer mailerService="${mailerService}" destinationAddress="${report.userEmail}" destinationName="${report.userName}">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>	La Hucha</title>

<link rel="stylesheet" href="http://la-hucha.appspot.com/css/mailing.css" type="text/css" media="screen" />

</head>
<body>
	<table style="font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;font-size: 12px;margin: 45px;width: 480px;text-align: left;border-collapse: collapse;">
		<colgroup>
    		<col style="background: #d0dafd;border-right: 10px solid transparent;border-left: 10px solid transparent;" />
    	</colgroup>
    	<thead>
		<tr>
		<th></th>
		<%-- iteration variables: initialization for each row --%>
		<jsp:useBean id="currentReportMonthYear" scope="page" class="org.matias.lahucha.dto.ReportMonthYear">
			<jsp:setProperty name="currentReportMonthYear" property="month" value="${report.from.month}"/>
			<jsp:setProperty name="currentReportMonthYear" property="year" value="${report.from.year}"/>
		</jsp:useBean>
		<c:forEach var="i" begin="0" end="${report.monthItemsCount}">
			<th style="font-size: 14px;font-weight: normal;padding: 12px 15px;color: #039;text-align: center;" colspan="2">${report.monthNames[currentReportMonthYear.month - 1]}</th>
			<c:choose>
			<c:when test="${currentReportMonthYear.month == 12}">
				<jsp:setProperty name="currentReportMonthYear" property="month" value="1" />
				<jsp:setProperty name="currentReportMonthYear" property="year" value="${currentReportMonthYear.year + 1}" />
			</c:when>
			<c:otherwise>
				<jsp:setProperty name="currentReportMonthYear" property="month" value="${currentReportMonthYear.month + 1}" />
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</tr>
	
		<tr>
		<th style="font-size: 14px;font-weight: normal;padding: 12px 15px;color: #039;">Account</th>
		<c:forEach var="i" begin="0" end="${report.monthItemsCount}">
			<th style="font-size: 14px;font-weight: normal;padding: 12px 15px;color: #039;">Income</th>
			<th style="font-size: 14px;font-weight: normal;padding: 12px 15px;color: #039;">Outcome</th>
		</c:forEach>
		</tr>
		</thead>
		
		
		<tbody>
		<c:forEach var="accountsMapIterator" items="${report.content}">
			<%-- iteration variables: initialization for each row --%>
			<jsp:setProperty name="currentReportMonthYear" property="month" value="${report.from.month}"/>
			<jsp:setProperty name="currentReportMonthYear" property="year" value="${report.from.year}"/>

			<tr>	
				<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;">${accountsMapIterator.key.name}</td>
				<c:forEach var="i" begin="0" end="${report.monthItemsCount}">
					<c:set scope="page" var="reportItem" value="${accountsMapIterator.value[currentReportMonthYear]}" />
					<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;"><c:if test="${reportItem.positive > 0}"><fmt:formatNumber type="currency" value="${reportItem.positive}" currencySymbol="&euro;"/></c:if></td>
					<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;"><c:if test="${reportItem.negative > 0}"><fmt:formatNumber type="currency" value="${reportItem.negative}" currencySymbol="&euro;"/></c:if></td>
						
					<%-- increment counter --%>
					<c:choose>
						<c:when test="${currentReportMonthYear.month == 12}">
							<jsp:setProperty name="currentReportMonthYear" property="month" value="1" />
							<jsp:setProperty name="currentReportMonthYear" property="year" value="${currentReportMonthYear.year + 1}" />
						</c:when>
						<c:otherwise>
							<jsp:setProperty name="currentReportMonthYear" property="month" value="${currentReportMonthYear.month + 1}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
  		</c:forEach>
  		
  		<tr>	
			<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;text-align: right;font-size: 14px;font-weight: bold;">Totals</td>
			<%-- iteration variables: initialization for each row --%>
			<jsp:setProperty name="currentReportMonthYear" property="month" value="${report.from.month}"/>
			<jsp:setProperty name="currentReportMonthYear" property="year" value="${report.from.year}"/>
			
			<c:forEach var="i" begin="0" end="${report.monthItemsCount}">
				<c:set scope="page" var="reportItem" value="${report.totals[currentReportMonthYear]}" />
				<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;"><c:if test="${reportItem.positive > 0}"><fmt:formatNumber type="currency" value="${reportItem.positive}" currencySymbol="&euro;"/></c:if></td>
				<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;"><c:if test="${reportItem.negative > 0}"><fmt:formatNumber type="currency" value="${reportItem.negative}" currencySymbol="&euro;"/></c:if></td>
					
				<%-- increment counter --%>
				<c:choose>
					<c:when test="${currentReportMonthYear.month == 12}">
						<jsp:setProperty name="currentReportMonthYear" property="month" value="1" />
						<jsp:setProperty name="currentReportMonthYear" property="year" value="${currentReportMonthYear.year + 1}" />
					</c:when>
					<c:otherwise>
						<jsp:setProperty name="currentReportMonthYear" property="month" value="${currentReportMonthYear.month + 1}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
		
		<tr>	
			<td style="padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;text-align: right;font-size: 14px;font-weight: bold;">Balance</td>
			<%-- iteration variables: initialization for each row --%>
			<jsp:setProperty name="currentReportMonthYear" property="month" value="${report.from.month}"/>
			<jsp:setProperty name="currentReportMonthYear" property="year" value="${report.from.year}"/>
			
			<c:forEach var="i" begin="0" end="${report.monthItemsCount}">
				<c:set scope="page" var="reportItem" value="${report.totals[currentReportMonthYear]}" />
				<td colspan="2" style="text-align: center;padding: 10px 15px;color: #669;border-top: 1px solid #e8edff;"><fmt:formatNumber type="currency" value="${reportItem.positive - reportItem.negative}" currencySymbol="&euro;"/></td>
					
				<%-- increment counter --%>
				<c:choose>
					<c:when test="${currentReportMonthYear.month == 12}">
						<jsp:setProperty name="currentReportMonthYear" property="month" value="1" />
						<jsp:setProperty name="currentReportMonthYear" property="year" value="${currentReportMonthYear.year + 1}" />
					</c:when>
					<c:otherwise>
						<jsp:setProperty name="currentReportMonthYear" property="month" value="${currentReportMonthYear.month + 1}" />
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
		</tbody>
	</table>
</body>
</html>
</hucha:mailer>