<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.Payments.model.entity.account.Status" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>

<html>

<head>
    <title>User account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/header.jsp"/>
<h4><f:message key="accounts_information"/></h4>
<table id="table-accounts" class="table" style="width: 50%">
    <thead>
    <tr>
        <th scope="col">
            <c:choose>
                <c:when test="${sortOrder.equalsIgnoreCase(\"DESC\")}">
                    <a href="${pageContext.request.contextPath}/user?sortBy=name&page=${page}&sortOrder=ASC">
                        <f:message key="name"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/user?sortBy=name&page=${page}&sortOrder=DESC">
                        <f:message key="name"/></a>
                </c:otherwise>
            </c:choose>
        </th>
        <th scope="col">
            <c:choose>
                <c:when test="${sortOrder.equalsIgnoreCase(\"DESC\")}">
                    <a href="${pageContext.request.contextPath}/user?sortBy=number&page=${page}&sortOrder=ASC">
                        <f:message key="number"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/user?sortBy=number&page=${page}&sortOrder=DESC">
                        <f:message key="number"/></a>
                </c:otherwise>
            </c:choose>
        </th>
        <th scope="col"><f:message key="amount"/></th>
        <th scope="col"><f:message key="status"/></th>
        <th scope="col"><f:message key="changeStatus"/></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="accounts" scope="request" type="java.util.List"/>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${account.getStatus() == Status.OPEN}">
                        <a href="${pageContext.request.contextPath}/account?id=${account.getId()}&page=1">${account.getName()}</a>
                    </c:when>
                    <c:otherwise>
                        ${account.getName()}
                    </c:otherwise>
                </c:choose>

            </td>
            <td>${account.getNumber()}</td>
            <td>$${account.getBalance()}</td>
            <td>${account.getStatus()}</td>
            <td>
                <c:choose>
                    <c:when test="${account.getStatus() == Status.OPEN }">
                        <a href="${pageContext.request.contextPath}/block?id=${account.getId()}"><f:message
                                key="block"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/sent-request?id=${account.getId()}"><f:message
                                key="unblock"/></a>
                    </c:otherwise>
                </c:choose>
            </td>

        </tr>
    </c:forEach>

    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}" step="1">
            <li class="page-item <c:if test="${i-1 == page}">active</c:if>">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/user?page=${i - 1}&sortBy=${sortBy}&sortOrder=${sortOrder}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>

</body>
</html>
