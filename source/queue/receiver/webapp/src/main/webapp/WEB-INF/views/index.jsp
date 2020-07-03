<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="refresh" content="2; URL=">
        <title>Order System</title>
        <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="generic-container">
            <div class="well lead">Welcome to Order Inventory System.</div>
        <div>
            <c:if test="${orderCountMap.size() > 0}">
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Count of Orders</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orderCountMap}" var="map" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${map.key}</td>
                                <td>${map.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
    </body>
</html>