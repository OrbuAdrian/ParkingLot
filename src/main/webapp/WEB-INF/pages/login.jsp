<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Login">
    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
            ${message}
        </div>
    </c:if>

    <form action="j_security_check" class="form-signin" method="POST">
        <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus />
        <label for="password" class="sr-only">Password</label>
        <input type="text" id="password" name="j_password" class="form-control" placeholder="Password" required />
        <button class="btn btn-lg btn-primary btn-black" type="submit">Sign in</button>

    </form>
</t:pageTemplate>
