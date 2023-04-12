<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${param.saveEmployeeSuccess ne null}">
            <span class="success">Employee added</span>
        </c:if>
        <p>This page is under construction</p>
    </jsp:body>
</t:layout>
