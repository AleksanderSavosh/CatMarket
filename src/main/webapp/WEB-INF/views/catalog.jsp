<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $.get("${pageContext.request.contextPath}/service/catalog/count_products_in_card", function( data ) {
                $( "#count" ).html( data );
            });
        });

        function addProductInCard(bread){
            $.post("${pageContext.request.contextPath}/service/catalog/add_in_card/" + bread, function( data ) {
                $( "#count" ).html( data );
            });
        }

        <c:if test="${isAdmin}">
        function deleteProductFromCatalog(bread){
            $.post("${pageContext.request.contextPath}/service/xxx/catalog/delete/" + bread, function(){
                $("#" + bread.replace(" ","_")).remove();
            });
        }

        function createCat(){
            var data = new FormData();
            data.append('bread',     $("input[name=bread]").val());
            data.append('hairType',  $("input[name=hairType]").val());
            data.append('lifeTime',  $("input[name=lifeTime]").val());
            data.append('maxWeight', $("input[name=maxWeight]").val());
            data.append('price',     $("input[name=price]").val());
            data.append('file',      $('input[name=file]')[0].files[0]);

            $.ajax({
                type: 'POST',
                url:"${pageContext.request.contextPath}/service/xxx/catalog/create",
                data:data,
                contentType: false,
                processData: false,
                success: function () {
                    location.reload();
                }
            });
        }
        </c:if>

    </script>
    <style type="text/css">
        body {
            margin: auto;
            max-width: 1280px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <h3 class="navbar-text">Catalog</h3>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${isAdmin}">
                        <li><a href="/xxx/accept_order">Accept orders</a></li>
                        <li><a href="/xxx/j_spring_security_logout">Log out</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><p class="navbar-text">In card <span id="count">0</span> cats</p></li>
                        <li><a id="goToCard" href="${pageContext.request.contextPath}/card">In card</a></li>
                    </c:otherwise>
                </c:choose>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <c:forEach items="${products}" var="product">
            <div id="${product.bread.replace(" ","_")}" class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="${pageContext.request.contextPath}/resources/${product.imgName}" alt="Cat" class="img-rounded"/>
                    <div class="caption">
                        <h3>${product.bread}</h3>
                        <p>Hair: ${product.hairType}</p>
                        <p>Life time: ${product.lifeTime} years</p>
                        <p>Max weight: ${product.maxWeight} kg</p>
                        <p>Price: ${product.price} $</p>
                        <c:choose>
                            <c:when test="${isAdmin}">
                                <button type="button" class="btn btn-danger"
                                        onclick="deleteProductFromCatalog('${product.bread}')">Delete</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-success"
                                        onclick="addProductInCard('${product.bread}')">Add in card</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${isAdmin}">
            <div class="col-sm-6 col-md-4">
                <div class="form-group">
                    <label for="bread">Bread:</label>
                    <input id="bread" class="form-control" type="text" name="bread"
                           placeholder="Enter bread" required>
                </div>
                <div class="form-group">
                    <label for="hairType">Hair type:</label>
                    <input id="hairType" class="form-control" type="text" name="hairType"
                           placeholder="Enter hair type" required>
                </div>
                <div class="form-group">
                    <label for="lifeTime">Life time:</label>
                    <input id="lifeTime" class="form-control" type="number" min="0"
                           name="lifeTime" placeholder="Enter life time" required>
                </div>
                <div class="form-group">
                    <label for="maxWeight">Max weight:</label>
                    <input id="maxWeight" class="form-control" type="number" min="0"
                           name="maxWeight" placeholder="Enter max weight" required>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input id="price" class="form-control" type="text"
                           name="price" placeholder="Enter price" required>
                </div>
                <div class="form-group">
                    <label for="file">Image:</label>
                    <input id="file" class="form-control" type="file"
                           name="file" placeholder="Choice image" required>
                </div>
                <button class="btn btn-success" type="button" onclick="createCat()">Create cat</button>
            </div>
        </c:if>
    </div>
</body>
</html>
