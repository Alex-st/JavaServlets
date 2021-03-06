<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/17/15
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Registration page</title>
</head>
<body>
<div id="wrapper">

  <center>
    <c:if test="${not empty requestScope.result}">
  <fmt:message key="${requestScope.result}"/>
    </c:if>
  </center>
  <div id="register">
<form action='authorization' method="post">
  <p>
  <select name="select">
    <option value="student" name="opt">Зарегистрироваться как студент</option>
    <option value="tutor" name="opt">Зарегистрироваться как администратор</option>
  </select>
  </p>

  <p>
    <label for="bname" class="uname" data-icon="*"><fmt:message key="name"/></label>
    <input id="bname" type='text' name='name' value=''/>
  </p>
  <p>
    <fmt:message key="surname"/>
    <input type='text' name='surname' value=''/>
  </p>

  <p>
    <label for="em" class="uname" data-icon="*"><fmt:message key="email"/></label>
    <input id="em" type="text" name='email' value=''/>
  </p>
  <p>
    <label for="logintext" class="youpasswd" data-icon="u"><fmt:message key="loginButton"/></label>
    <input id="logintext" type='text' name='login' value=''/>
  </p>
  <p>
    <label for="password" class="youpasswd" data-icon="p"><fmt:message key="passButton"/></label>
    <input id="password" type="password" name='password' value=''/>
  </p>

    <button type = "reset" name="Reset" value="reset"><fmt:message key="reset"/></button>
    <button type = "submit" name="send" value="register"><fmt:message key="toregister"/></button>


</form>
    </div>

</div>
</body>
</html>
