<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${param.success ne null}">
            <span><p class="message success">Compensation saved</p></span>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <span><p class="message error">${errorMessage}</p></span>
        </c:if>
        <form:form modelAttribute="compensation" class="form" method="post" action="/compensation/save">
            <h4>New Compensation</h4>
            <input type="hidden" name="id" value="${compensation.id}">
            <input type="text" name="employee" value="${compensation.getEmployee() != null ? compensation.getEmployee().getId() : ''}" placeholder="Employee ID">
            <select id="type" name="type">
                <option ${compensation.type == null ? 'selected' : ''} disabled value="">Type</option>
                <option value="SALARY" ${compensation.type == 'SALARY' ? 'selected' : ''}>SALARY</option>
                <option value="BONUS" ${compensation.type == 'BONUS' ? 'selected' : ''}>BONUS</option>
                <option value="COMMISSION" ${compensation.type == 'COMMISSION' ? 'selected' : ''}>COMMISSION</option>
                <option value="ALLOWANCE" ${compensation.type == 'ALLOWANCE' ? 'selected' : ''}>ALLOWANCE</option>
                <option value="ADJUSTMENT" ${compensation.type == 'ADJUSTMENT' ? 'selected' : ''}>ADJUSTMENT</option>
            </select>
            <input type="number" step="any" id="amount" name="amount" value="${compensation.amount}" placeholder="Amount">
            <input type="month" id="date" name="date" value="${compensation.date.format(DateTimeFormatter.ofPattern('yyyy-MM'))}">
            <input type="text" id="description" name="description" value="${compensation.description}" placeholder="Description">
            <div class="button-container full-width">
                <button class="button" type="button" onClick="returnToList()">Cancel</button>
                <button class="button submit" type="submit">Save</button>
            </div>
        </form:form>
    </jsp:body>
</t:layout>