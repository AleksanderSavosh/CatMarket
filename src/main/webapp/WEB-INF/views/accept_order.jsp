<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accept orders page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            margin: auto;
            max-width: 1280px;
        }
    </style>
</head>
<body>
    <div class="page-header">
        <div class="container">
            <h1>Accept orders</h1>
        </div>
    </div>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Fio</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Delivery address</th>
                <th>Payment description</th>
                <th>Total price</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.fio}</td>
                        <td>${order.phone}</td>
                        <td>${order.email}</td>
                        <td>${order.deliveryAddress}</td>
                        <td>${order.paymentDescription}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>


</body>
</html>
