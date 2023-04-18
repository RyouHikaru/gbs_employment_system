<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
    <jsp:body>
        <c:if test="${param.success ne null}">
            <span class="message success">Employee saved</span>
        </c:if>
        <c:if test="${param.employeeNotFound ne null}">
            <span class="message error">Employee not found</span>
        </c:if>
        <div class="table-container">
            <form:form modelAttribute="search" class="search-form" method="get" action="/employees/list">
                <input type="text" id="searchFirstName" name="firstName" value="${search.firstName}" placeholder="First name"/>
                <input type="text" id="searchLastName" name="lastName" value="${search.lastName}" placeholder="Last name"/>
                <input type="text" id="searchPosition" name="position"  value="${search.position}" placeholder="Position"/>
                <button class="search-btn" type="submit">Search</button>
                <button class="search-btn" type="button" onClick="clearSearch()">Clear</button>
            </form:form>
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
        </div>
        <script>
            function clearSearch() {
                document.getElementById("searchFirstName").value = "";
                document.getElementById("searchLastName").value = "";
                document.getElementById("searchPosition").value = "";
                var search = {
                    firstName: "",
                    lastName: "",
                    position: ""
                };
                document.querySelector("form").setAttribute("modelAttribute", "search");
                document.querySelector("form").setAttribute("modelAttribute", JSON.stringify(search));
            }
        </script>
    </jsp:body>
</t:layout>