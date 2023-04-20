<%@tag description="Page Layout Template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <meta charset="UTF-8">
    <title>GBS Employment System</title>
</head>
<body>
    <header class="header">
        <a href="/"><h2>GBS Employment System</h2></a>
        <nav>
            <a href="/employees/list">View Employees</a>
            <a href="/employees/new">Add Employee</a>
        </nav>
    </header>
    <main class="main">
        <jsp:doBody/>
    </main>
    <footer class="footer">
        <p>&copy; All Rights Reserved, 2023</p>
    </footer>
    <script src="/js/index.js"></script>
</body>
</html>