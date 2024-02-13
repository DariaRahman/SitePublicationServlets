<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>You are blocked</title>
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
        <div class="header-section">
            <ul>
                <li>
                    <form class="header-item header-button" action="controller" method="get">
                        <input type="hidden" name="command" value="login"/>
                        <input type="submit" value="<fmt:message key='login'/>"/>
                    </form>
                </li>
                <li>
                    <form class="header-item header-button" action="controller" method="get">
                        <input type="hidden" name="command" value="signup"/>
                        <input type="submit" value="<fmt:message key='signup'/>"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>

    <div class="content">
        <div class="error-info">
            <h2>${error.message}</h2>
        </div>
    </div>

    <div class="footer">
        <div class="footer-info">
            <h4>2023 Rahman Daria</h4>
        </div>
    </div>
</div>
</body>
</html>