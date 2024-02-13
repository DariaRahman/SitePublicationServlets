<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login</title>
    <link href="style/stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="wrapper">
    <div class="header">
        <div class="header-section">
            <ul>
                <li>
                    <form class="header-item header-logo" action="controller" method="get">
                        <input type="hidden" name="command" value="mainPage"/>
                        <input type="submit" value="Periodicals"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>

    <div class="content">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="login-form">
                <h1><fmt:message key="login"/></h1>
                <div class="login-input">
                    <input type="email" name="email" placeholder="<fmt:message key='email'/>" required/><br/>
                    <input type="password" name="password" placeholder="<fmt:message key='password'/>" required/><br/>
                    <input type="submit" value="<fmt:message key='signin'/>"/>
                </div>
                <a href="signup.jsp" class="signup"><fmt:message key='signup'/></a>
            </div>
        </form>
    </div>

    <div class="footer">
        <div class="footer-info">
            <h4>2023 Rahman Daria</h4>
        </div>
    </div>
</div>
</body>
</html>