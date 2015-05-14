<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <style>
        body {
            background: url("${pageContext.request.contextPath}/resources/background.jpg") no-repeat center center;
            background-size: cover;
            margin: auto;
            max-width: 1280px;
        }
        p, h1 {
            color: #ffffff;
        }
    </style>
</head>
<body >
    <div class="page-header">
        <div class="container">
            <h1>Best cat market</h1>
            <p>The first internet store specialize in cats.</p>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <p>Our shop opened recently. But we have found a range of buyers. All people not indifferent to cats, use our services to get a pet. In turn, we ensure the health and purebred kittens, because working with the best nurseries of the country. Each kitten has purchased the passport and the necessary vaccinations. Also we offer a huge selection of breads so that everyone can find a pet to your liking. Apply to Best Cat Market.</p>
        </div>
        <div class="row">
            <a id="forAdmin" href="/xxx/login" class="btn btn-danger" role="button">Only for admin</a>
            <a id="toCatalog" href="/catalog" class="btn btn-info pull-right" role="button">Start</a>
       </div>
    </div>
</body>
</html>
