<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <div class="quick-start">
            <h3>Welcome!</h3>
            <p>1. If you want to view the Employees, navigate to <a class="quick-start-link" href="/employees/list">Employees</a></p>
            <p>2. If you want to add a new Compensation, navigate to <a class="quick-start-link" href="/compensation/new">Compensation</a></p>
            <p>3. To view a specific Employee, click on "View" from Details column.</p>
            <p>From there, you can also access their information, as well as their Compensation History.</p>
            </br>
            <p>For inquiries, please contact us at <a class="quick-start-link" href="mailto: gbsteam@detroit.ne">gbsteam@detroit.ne</a></p>
        </div>
    </jsp:body>
</t:layout>
