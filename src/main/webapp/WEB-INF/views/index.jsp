<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <meta charset="UTF-8">
    <title>Home</title>
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
        <c:if test="${param.saveEmployeeSuccess ne null}">
            <span class="success">Employee added</span>
        </c:if>
        <p>This page is under construction</p>
    </main>
    <footer class="footer">
        <p>&copy; All Rights Reserved, 2023</p>
    </footer>
</body>
</html>