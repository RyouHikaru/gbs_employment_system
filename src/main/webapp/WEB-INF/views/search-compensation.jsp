<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${not empty errorMessage}">
            <span><p class="message error">${errorMessage}</p></span>
        </c:if>
        <form:form modelAttribute="compensationSearch" class="form" method="post" action="/compensation/search/${employeeId}">
            <h4>Compensation History Search</h4>
            <input type="text" name="employeeId" value="${employeeId}" readonly>
            <input type="month" id="startDate" name="startDate" value="${compensationSearch.startDate.format(DateTimeFormatter.ofPattern('yyyy-MM'))}">
            <input type="month" id="endDate" name="endDate" value="${compensationSearch.endDate.format(DateTimeFormatter.ofPattern('yyyy-MM'))}">
            <div class="button-container full-width">
                <button class="button" type="button" onClick="returnToEmployeeDetails(${employeeId})">Cancel</button>
                <button class="button submit" type="submit">Search</button>
            </div>
        </form:form>
    </jsp:body>
</t:layout>