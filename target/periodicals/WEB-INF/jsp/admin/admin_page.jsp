<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Admin page</title>
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
                <li>
                    <div class="header-select-section">
                        <form class="header-item header-button header-select" action="changeLocale.jsp" method="get">

                            <fmt:message key="settings_jsp.label.set_locale"/>:
                            <div class="header-select-item">
                                <select name="locale">
                                    <c:forEach items="${applicationScope.locales}" var="locale">
                                        <c:set var="selected"
                                               value="${locale.key == currentLocale ? 'selected' : '' }"/>
                                        <option value="${locale.key}" ${selected}>${locale.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="header-select-item">
                                <input type="submit" value="<fmt:message key='main_page.save'/>">
                            </div>
                        </form>
                    </div>
                </li>
                <li>
                    <div class="header-select-section">
                        <form class="header-item header-button header-select" action="controller" method="get">
                            <div class="header-select-item">
                                <select name="sortBy">
                                    <c:set var="selectedType" value="${sortName == 'type' ? 'selected' : '' }"/>
                                    <c:set var="selectedName" value="${sortName == 'name' ? 'selected' : '' }"/>
                                    <c:set var="selectedPrice" value="${sortName == 'price' ? 'selected' : '' }"/>
                                    <option ${selectedType} value="type">
                                        <fmt:message key='type'/></option>
                                    <option ${selectedName} value="name">
                                        <fmt:message key='name'/></option>
                                    <option ${selectedPrice} value="price">
                                        <fmt:message key='price'/></option>
                                </select>
                            </div>
                            <div class="header-select-item">
                                <input type="submit" value="<fmt:message key='main_page.sort'/>">
                            </div>
                            <input type="hidden" name="command" value="mainPage"/><br/>
                        </form>
                    </div>
                </li>
                <li>
                    <div class="header-select-section">
                        <form class="header-item header-button header-select" action="controller" method="get">
                            <input type="hidden" name="command" value="mainPage"/>
                            <input type="hidden" name="isSearch" value="true"/>
                            <div class="header-select-item header-search">
                                <input type="search" name="search" required>
                            </div>
                            <div class="header-select-item">
                                <input type="submit" value="<fmt:message key='main_page.search'/>"/>
                            </div>
                        </form>
                    </div>
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
        <c:forEach var="num" items="${periodicals}">
            <div class="periodical-form">
                <table class="periodical-table">
                    <tr>
                        <th class="name" colspan="2">${num.name}</th>
                    </tr>
                    <tr>
                        <th class="other"><fmt:message key='type'/></th>
                        <td><fmt:message key='${num.type}'/></td>
                    </tr>
                    <tr>
                        <th class="other"><fmt:message key='publisher'/></th>
                        <td>${num.publisher}</td>
                    </tr>
                    <tr>
                        <th class="other"><fmt:message key='frequency'/></th>
                        <td><fmt:message key='${num.frequency}'/></td>
                    </tr>
                    <tr>
                        <th class="other"><fmt:message key='month_price'/></th>
                        <td class="periodical-price">${num.price}</td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <div class="subscribe-button">
                                <form class="sub1" action="controller" method="get">
                                    <input type="submit" value="<fmt:message key='edit'/>">
                                    <input type="hidden" name="command" value="editPeriodical"/>
                                    <input type="hidden" name="periodicalId" value="${num.id}"/>
                                </form>
                            </div>
                        </th>
                    </tr>
                    <tr class="admin-page-delete">
                        <th colspan="2">
                            <div class="subscribe-button">
                                <form class="sub1" action="controller" method="post">
                                    <input type="submit" value="<fmt:message key='delete'/>">
                                    <input type="hidden" name="command" value="deletePeriodical"/>
                                    <input type="hidden" name="periodicalId" value="${num.id}"/>
                                </form>
                            </div>
                        </th>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>

    <div class="footer">
        <div class="footer-info">
            <h4>2023 Rahman Daria</h4>
        </div>
    </div>
</div>
</body>
</html>