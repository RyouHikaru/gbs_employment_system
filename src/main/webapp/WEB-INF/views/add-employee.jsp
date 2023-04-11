<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <meta charset="UTF-8">
    <title>Add Employee</title>
</head>
<body>
    <header class="header">
        <a href="/"><h2>GBS Employment System</h2></a>
        <nav>
            <a href="/employee/list">View Employees</a>
            <a href="/employee/new">Add Employee</a>
        </nav>
    </header>
    <main class="main">
        <c:if test="${not empty errorMessage}">
            <span><p class="error">${errorMessage}</p></span>
        </c:if>
        <form:form modelAttribute="employee" class="employee-form" method="post" action="/employee/new">
            <h4>New Employee</h4>
            <input type="hidden" name="id" value="${employee.id}">
            <input type="text" id="firstName" name="firstName" value="${employee.firstName}" placeholder="First Name">
            <input type="text" id="middleName" name="middleName" value="${employee.middleName}" placeholder="Middle Name (Optional)">
            <input type="text" id="lastName" name="lastName" value="${employee.lastName}" placeholder="Last Name">
            <input type="date" id="birthDate" name="birthDate" max="2100-12-31" value="${employee.birthDate}">
            <input type="text" id="position" name="position" value="${employee.position}" placeholder="Position">
            <button class="submit-btn" type="submit">Save</button>
        </form:form>
    </main>
    <footer class="footer">
        <p>&copy; All Rights Reserved, 2023</p>
    </footer>
</body>
</html>