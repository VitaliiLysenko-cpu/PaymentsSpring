
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.Payments.model.entity.account.Status " %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/header_admin.jsp"/>

<table id="table-accounts" class="table">
    <thead>
    <tr>
        <th scope="col"><f:message key="name"/></th>
        <th scope="col"><f:message key="status"/></th>
        <th scope="col"><f:message key="changeStatus"/></th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.getName()}</td>
            <td>${account.getStatus()}</td>
            <td>
                <c:choose>
                    <c:when test="${account.getStatus() == Status.BLOCKED }">
                        <a href="customer-account/unblock?accountId=${account.getId()}&customerId=${customerId}">
                            <f:message key="unblock"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="customer-account/block?accountId=${account.getId()}&customerId=${customerId}">
                            <f:message key="block"/>
                        </a>
                    </c:otherwise>
                </c:choose>
            </td>

        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
