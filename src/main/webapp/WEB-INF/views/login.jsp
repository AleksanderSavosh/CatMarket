<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <style>
        body {
            margin: auto;
            max-width: 1280px;
        }

        .col-centered {
            float: none;
            margin: 0 auto;
        }
    </style>
</head>


    <div class="container">
        <div class="row">
            <div class="col-md-6 col-centered">
                <h2>Enter your admin login and password</h2>

                <form role="form" action="/xxx/j_spring_security_check" method="post">
                    <div class="form-group">
                        <label for="login">Login:</label>
                        <input id="login" class="form-control" type="text" name="j_username"
                               placeholder="Enter login" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input id="pwd" class="form-control" type="password" name="j_password"
                               placeholder="Enter password" required>
                    </div>
                    <c:if test="${error}">
                        <div class="alert alert-warning">Wrong login or password.</div>
                    </c:if>
                    <button id="submit" class="btn btn-default" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>