<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <input type="hidden" name="command" value="adminProfile"/>
                                <input type="submit" value="<fmt:message key='view_profile'/>">
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
        <div class="subscription-info">
            <table class="subscription-table">
                <tr>
                    <th><fmt:message key='name'/></th>
                    <th><fmt:message key='login_page_surname'/></th>
                    <th><fmt:message key='email'/></th>
                    <th><fmt:message key='balance'/></th>
                    <th><fmt:message key='status'/></th>
                    <th><fmt:message key='actions'/></th>
                </tr>
                <c:forEach var="num" items="${users}">
                    <tr>
                        <td>${num.firstName}</td>
                        <td>${num.lastName}</td>
                        <td>${num.email}</td>
                        <td>${num.balance}</td>
                        <td>${num.status}</td>
                        <td>
                            <form class="users-block" action="controller" method="get">
                                <input type="hidden" name="command" value="block"/>
                                <input type="hidden" name="userId" value="${num.id}"/>
                                <p><input type="submit" value="<fmt:message key='block'/>"></p>
                            </form>
                            <form class="users-unblock" action="controller" method="get">
                                <input type="hidden" name="command" value="unblock"/>
                                <input type="hidden" name="userId" value="${num.id}"/>
                                <p><input type="submit" value="<fmt:message key='unblock'/>"></p>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
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