<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${not empty errorMessage}">
            <span><p class="error">${errorMessage}</p></span>
        </c:if>
        <form:form modelAttribute="employee" class="employee-form" method="post" action="/employees/save">
            <h4>View/Edit Employee</h4>
            <input type="text" name="id" value="${employee.id}" readonly>
            <input type="text" id="firstName" name="firstName" value="${employee.firstName}" placeholder="First Name">
            <input type="text" id="middleName" name="middleName" value="${employee.middleName}" placeholder="Middle Name (Optional)">
            <input type="text" id="lastName" name="lastName" value="${employee.lastName}" placeholder="Last Name">
            <input type="date" id="birthDate" name="birthDate" max="2100-12-31" value="${employee.birthDate}">
            <input type="text" id="position" name="position" value="${employee.position}" placeholder="Position">
            <div class="button-container full-width">
                <button class="button" type="button" onClick="cancelAction()">Cancel</button>
                <button class="button submit" type="submit">Save</button>
            </div>
        </form:form>
    </jsp:body>
</t:layout>