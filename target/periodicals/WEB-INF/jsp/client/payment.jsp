<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Subscribe</title>
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
                            <form action="controller">
                                <input type="hidden" name="command" value="showSubscriptions"/>
                                <input type="submit" value="<fmt:message key='subscriptions'/>">
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
        <div class="periodical-info">
            <table class="periodical-info-table">
                <tr>
                    <th class="profile-name" colspan="2"><fmt:message key='payment'/></th>
                </tr>
                <tr>
                    <th><fmt:message key='payer'/></th>
                    <td>${user.firstName} ${user.lastName}</td>
                </tr>
                <tr>
                    <th><fmt:message key='periodical_name'/></th>
                    <td>${periodical.name}</td>
                </tr>
                <tr>
                    <th><fmt:message key='frequency'/></th>
                    <td><fmt:message key='${periodical.frequency}'/></td>
                </tr>
                <tr>
                    <th><fmt:message key='subscription_period'/></th>
                    <td><fmt:message key='${subscriptionPeriod}'/></td>
                </tr>
                <tr>
                    <th><fmt:message key='month_price'/></th>
                    <td>${periodical.price}</td>
                </tr>
                <tr>
                    <th><fmt:message key='total_price'/></th>
                    <td>${periodical.price * subscriptionPeriod.number}</td>
                </tr>
                <tr class="yb">
                    <th><fmt:message key='your_balance'/></th>
                    <td>${user.balance}</td>
                </tr>
                <tr>
                    <th colspan="2">
                        <form class="payment-container" action="controller" method="get">
                            <input type="submit" value="<fmt:message key='top_up_balance'/>">
                            <input type="hidden" name="command" value="topUpBalance"/>
                        </form>
                        <form class="payment-container" action="controller" method="get">
                            <input type="submit" value="<fmt:message key='pay'/>">
                            <input type="hidden" name="command" value="paymentForm"/>
                        </form>
                    </th>
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