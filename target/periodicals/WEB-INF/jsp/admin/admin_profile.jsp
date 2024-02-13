<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Client profile</title>
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
        <div class="header-section inner-menu">
            <ul>
                <li>
                    <h2>${user.firstName} &#9660</h2>
                    <ul>
                        <li>
                            <form action="controller">
                                <input type="hidden" name="command" value="showUsers"/>
                                <input type="submit" value="<fmt:message key='users'/>">
                            </form>
                        </li>
                        <li>
                            <form action="controller">
                                <input type="hidden" name="command" value="addPeriodical"/>
                                <input type="submit" value="<fmt:message key='add_periodical'/>">
                            </form>
                        </li>
                        <li>
                            <form action="controller">
                                <input type="hidden" name="command" value="logout"/>
                                <input type="submit" value="<fmt:message key='logout'/>">
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="content">
        <div class="client-info">
            <table class="client-table">
                <tr>
                    <th class="profile-name" colspan="2"><fmt:message key='profile'/></th>
                </tr>
                <tr>
                    <th><fmt:message key='email'/></th>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <th><fmt:message key='name'/></th>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <th><fmt:message key='login_page_surname'/></th>
                    <td>${user.lastName}</td>
                </tr>
                <tr class="admin-profile-gender">
                    <th><fmt:message key='login_page_gender'/></th>
                    <td><fmt:message key='${user.gender}'/></td>
                </tr>
            </table>
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