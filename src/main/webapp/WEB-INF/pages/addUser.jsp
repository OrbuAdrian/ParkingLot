<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="AddUser">
  <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">
    <div class="row">
      <div class="col-md-6 mb-3">
        <!-- Nume User -->
        <label for="username" >Username</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
        <div class="invalid-feedback">
          Username is required!
        </div>

        <!-- Email -->
        <label for="email">Email</label>
        <input type="text" class="form-control" id="email" name="email" placeholder="" value="" required>
        <div class="invalid-feedback">
          Email is required!
        </div>

        <!-- Parola -->
        <label for="password">Password</label>
        <input type="text" class="form-control" id="password" name="password" placeholder="" value="" required>
        <div class="invalid-feedback">
          Email is required!
        </div>

        <!-- UserGroup -->
        <label for="user_groups">Groups</label>
        <select class="custom-select d-block w-100" id="user_groups" name="user_groups" multiple>

          <c:forEach var="user_group" items="${userGroups}" varStatus="status">
            <option value="${user_group}">${user_group}</option>">
          </c:forEach>

        </select>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg" type="submit">Save</button>
      </div>


    </div>
  </form>
</t:pageTemplate>
