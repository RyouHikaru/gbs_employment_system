<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${param.success ne null}">
            <span class="success">Employee added</span>
        </c:if>
        <div class="table-container">
            <table class="employee-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Birthdate</th>
                        <th>Position</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
                        <td>${employee.birthDate}</td>
                        <td>${employee.position}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:layout>