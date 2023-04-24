<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${param.success ne null}">
            <span><p class="message success">Employee saved</p></span>
        </c:if>
        <c:if test="${param.employeeNotFound ne null}">
            <span><p class="message error">Employee not found</p></span>
        </c:if>
        <div class="table-container">
            <div class="search-container">
                <button class="button plus" type="button" title="New employee" onClick="addEmployee()">Add employee</button>
                <form:form modelAttribute="search" class="search-form" method="get" action="/employees/list">
                    <input type="text" id="searchFirstName" name="firstName" value="${search.firstName}" placeholder="First name"/>
                    <input type="text" id="searchLastName" name="lastName" value="${search.lastName}" placeholder="Last name"/>
                    <input type="text" id="searchPosition" name="position"  value="${search.position}" placeholder="Position"/>
                    <div class="button-container">
                        <button class="button w-100" type="button" onClick="clearSearch()">Clear</button>
                        <button class="button submit w-100" type="submit">Search</button>
                    </div>
                </form:form>
            </div>
            <table class="employee-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Birthdate</th>
                        <th>Position</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employees}" var="employee">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
                            <td>${employee.birthDate}</td>
                            <td>${employee.position}</td>
                            <td><a href="/employees/${employee.id}">View</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${employees.size() == 0}">
                <span><p class="empty">No records found</p></span>
            </c:if>
        </div>
    </jsp:body>
</t:layout>