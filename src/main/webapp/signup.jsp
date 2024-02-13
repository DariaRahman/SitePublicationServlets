<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Sign up</title>
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
            </ul>
        </div>
    </div>

    <div class="content">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="signup"/>
            <div class="signup-form">
                <h1><fmt:message key='signup'/></h1>
                <div class="signup-input">
                    <p><fmt:message key='email'/></p>
                    <input type="email" name="email" placeholder="<fmt:message key='email'/>" required/><br/>
                    <p><fmt:message key='login_page_your_name'/></p>
                    <input type="text" name="first_name" placeholder="<fmt:message key='login_page_name'/>"
                           required/><br/>
                    <p><fmt:message key='login_page_your_surname'/></p>
                    <input type="text" name="last_name" placeholder="<fmt:message key='login_page_surname'/>" required/><br/>
                    <p><fmt:message key='login_page_your_gender'/></p>
                    <select name="gender">
                        <option selected value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                    </select>
                    <p><fmt:message key='password'/></p>
                    <input type="password" name="password" placeholder="<fmt:message key='password'/>" required/><br/>
                    <input type="submit" value="<fmt:message key='signup'/>"/>
                </div>
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