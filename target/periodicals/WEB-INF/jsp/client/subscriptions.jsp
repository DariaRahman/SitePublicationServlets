<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" uri="http://com.ermandary/mylib" %>

<html>
<head>
    <title>Subscriptions</title>
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
                                <input type="hidden" name="command" value="clientProfile"/>
                                <input type="submit" value="<fmt:message key='view_profile'/>">
                            </form>
                        </li>
                        <li>
                            <form class="profile-header" action="controller" method="get">
                                <input type="hidden" name="command" value="topUpBalance"/>
                                <input type="submit" value="<fmt:message key='top_up_balance'/>">
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
                    <th><fmt:message key='type'/></th>
                    <th><fmt:message key='frequency'/></th>
                    <th><fmt:message key='active_from'/></th>
                    <th><fmt:message key='active_to'/></th>
                </tr>
                <c:forEach var="num" items="${subscriptionsInfo}">
                    <mylib:showSubscriptionInfo subscriptionInfo="${num}"/>
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
</div>
</body>