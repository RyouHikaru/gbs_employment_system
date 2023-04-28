<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${not empty errorMessage}">
            <span><p class="message error">${errorMessage}</p></span>
        </c:if>
        <div class="table-container">
            <div class="button-container">
                <button class="button submit w-125" type="button" onClick="returnToPreviousPage()">Back to History</button>
                <button class="button w-125" type="button" onClick="returnToIndex()">Home</button>
            </div>
            <h3>Compensation details of Employee ${param.employeeId} for ${param.month} ${param.year}</h3>
            <table class="monthly-comp-table">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${compensations}" var="compensation">
                        <tr>
                            <td>${compensation.date.format(DateTimeFormatter.ofPattern('yyyy-MM'))}</td>
                            <td>${compensation.type}</td>
                            <td>${compensation.amount}</td>
                            <td>${compensation.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:layout>