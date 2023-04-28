<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${not empty errorMessage}">
            <span><p class="message error">${errorMessage}</p></span>
        </c:if>
        <div class="table-container">
            <div class="button-container">
                <button class="button submit w-125" type="button" onClick="returnToCompensationSearch(${employeeId})">Back to Search</button>
                <button class="button w-125" type="button" onClick="returnToIndex()">Home</button>
            </div>
            <h3>Compensation History of Employee ${employeeId}</h3>
            <table class="monthly-comp-table">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Month</th>
                        <th>Total Amount</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${monthlyCompensations}" var="monthlyCompensation">
                        <tr>
                            <td>${monthlyCompensation.year}</td>
                            <td>${monthlyCompensation.month}</td>
                            <td>${monthlyCompensation.totalAmount}</td>
                            <td>
                                <a href="breakdown?employeeId=${employeeId}&year=${monthlyCompensation.year}&month=${monthlyCompensation.month}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${monthlyCompensations.size() == 0}">
                <span><p class="empty">No records found</p></span>
            </c:if>
        </div>
    </jsp:body>
</t:layout>