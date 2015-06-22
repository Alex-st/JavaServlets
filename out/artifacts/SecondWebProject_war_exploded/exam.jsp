<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 6/19/15
  Time: 2:01 PM
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
    <title>exam</title>
</head>
<body>
<center>
<br>
  <form name="questionForm" action="tutorpage">
    <p><b>${curQText}</b></p>

    <c:forEach var="ans" items="${answers}">
      <p><input type="radio" name="answer" value="${ans}">${ans}</p>
    </c:forEach>

    <button type="submit" name="send" value="exam"><fmt:message key="answerQuestion"/></button>
  </form>
</center>
</body>
</html>
