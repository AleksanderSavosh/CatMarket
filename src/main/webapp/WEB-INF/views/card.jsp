<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Card</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <script type="text/javascript">

        function deleteFromCard(){
            $.each($("input[type=checkbox]:checked"),function(index, value){
                console.info('index:' + index);
                console.info('value:' + $(value).attr('bread'));
                $('#' + $(value).attr('bread').replace(" ", "_")).remove();
                $.post("${pageContext.request.contextPath}/service/card/remove_product_from_card/" + $(value).attr('bread'), function(data){
                    $("#totalPrice").html(data);
                });
            });
        }

        function changeProductCount(bread){
            var count = $("#" + bread.replace(" ", "_") + " input[type=number]").val();
            $.post("${pageContext.request.contextPath}/service/card/change_product_count/" + bread + "/" + count, function(data){
                $("#totalPrice").html(data);
            });
        }

    </script>
    <style>
        body {
            margin: auto;
            max-width: 1280px;
        }
    </style>
</head>
<body>
    <div class="page-header">
        <div class="container">
            <h1>Card</h1>
        </div>
    </div>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th> </th>
                    <th>Bread</th>
                    <th>Count</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${hasProducts}">
                        <c:forEach items="${productIntegerMap.keySet()}" var="product">
                            <tr id="${product.bread.replace(" ","_")}">
                                <th scope="row"><input type="checkbox" bread="${product.bread}"/>
                                </th>
                                <td>${product.bread}</td>
                                <td><input class="itemCount" type="number" size="2" min="1" max="99"
                                           value="${productIntegerMap.get(product)}"
                                           onchange="changeProductCount('${product.bread}')"/>
                                </td>
                                <td>${product.price}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="4">No data</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <div class="container">
        <button class="btn btn-danger" onclick="deleteFromCard()">Delete</button>
        <span class="pull-right">Total: <span id="totalPrice">${totalPrice}</span>$</span>
        <br />
        <br />
        <div class="col-sm-6">
            <form role="form" action="thank_for_buy" method="post">
                <div class="form-group">
                    <label for="fio">FIO:</label>
                    <input id="fio" class="form-control" type="text" name="fio"
                           placeholder="Enter your name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input id="email" class="form-control" type="email" name="email"
                           placeholder="Enter your email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input id="phone" class="form-control" type="phone" name="phone"
                           placeholder="Enter your phone number" required>
                </div>
                <div class="form-group">
                    <label for="deliveryAddress">Delivery address:</label>
                    <input id="deliveryAddress" class="form-control" type="text"
                           name="deliveryAddress" placeholder="Enter delivery address" required>
                </div>
                <div class="form-group">
                    <label for="paymentDescription">Payment description:</label>
                    <input id="paymentDescription" class="form-control" type="text"
                           name="paymentDescription" placeholder="Enter payment description"
                           required>
                </div>
                <c:if test="${hasError}">
                    <div id="errorMess" class="alert alert-warning">${error}</div>
                </c:if>
                <button id="makeOrder" class="btn btn-success" type="submit">Buy</button>
            </form>
        </div>
    </div>
</body>
</html>
