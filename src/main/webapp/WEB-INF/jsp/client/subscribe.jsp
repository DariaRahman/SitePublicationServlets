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
                    <th class="profile-name" colspan="2">${periodical.name}</th>
                </tr>
                <tr>
                    <th class="periodical-description" colspan="2">
                        <p class="ds"><fmt:message key='description'/></p></br>
                        <p class="ds-info">${periodical.description}</p>
                    </th>
                </tr>
                <tr>
                    <th><fmt:message key='type'/></th>
                    <td><fmt:message key='${periodical.type}'/></td>
                </tr>
                <tr>
                    <th><fmt:message key='publisher'/></th>
                    <td>${periodical.publisher}</td>
                </tr>
                <tr>
                    <th><fmt:message key='frequency'/></th>
                    <td><fmt:message key='${periodical.frequency}'/></td>
                </tr>
                <tr>
                    <th><fmt:message key='month_price'/></th>
                    <td>${periodical.price}</td>
                </tr>
                <form action="controller" method="post">
                    <tr class="subscription-type">
                        <th><fmt:message key='subscription_period'/></th>
                        <td>
                            <select name="subscriptionType">
                                <option selected value="ONE_MONTH"><fmt:message key='one_month'/></option>
                                <option value="THREE_MONTHS"><fmt:message key='three_months'/></option>
                                <option value="SIX_MONTHS"><fmt:message key='six_months'/></option>
                                <option value="ONE_YEAR"><fmt:message key='one_year'/></option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">
                            <form class="subscribe-continue" action="controller" method="get">
                                <input type="submit" value="<fmt:message key='continue'/>">
                                <input type="hidden" name="command" value="payment"/><br/>
                            </form>
                        </th>
                    </tr>

                </form>
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