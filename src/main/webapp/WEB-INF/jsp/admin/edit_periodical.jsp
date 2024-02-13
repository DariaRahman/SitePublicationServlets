<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit periodical</title>
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
                                <input type="hidden" name="command" value="showUsers"/>
                                <input type="submit" value="<fmt:message key='users'/>">
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
        <form action="controller" method="post">
            <div class="client-info">
                <table class="client-table">
                    <tr>
                        <th class="profile-name" colspan="2"><fmt:message key='edit_periodical'/></th>
                    </tr>
                    <tr>
                        <th><fmt:message key='name'/></th>
                        <td>
                            <input type="text" name="periodicalName" value="${periodical.name}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='type'/></th>
                        <td>
                            <select name="periodicalType">
                                <c:set var="periodicalType" value="${periodical.type}"/>
                                <c:set var="selectedComic" value="${periodicalType == 'Comic' ? 'selected' : '' }"/>
                                <c:set var="selectedMagazine"
                                       value="${periodicalType == 'Magazine' ? 'selected' : '' }"/>
                                <c:set var="selectedNewspaper"
                                       value="${periodicalType == 'Newspaper' ? 'selected' : '' }"/>
                                <option ${selectedComic} value="Comic"><fmt:message key='Comic'/></option>
                                <option ${selectedMagazine} value="Magazine"><fmt:message key='Magazine'/></option>
                                <option ${selectedNewspaper} value="Newspaper"><fmt:message key='Newspaper'/></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='publisher'/></th>
                        <td>
                            <input type="text" name="publisher" value="${periodical.publisher}" required>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='frequency'/></th>
                        <td>
                            <select name="frequency">
                                <c:set var="frequency" value="${periodical.frequency}"/>
                                <c:set var="selectedDaily" value="${frequency == 'Daily' ? 'selected' : '' }"/>
                                <c:set var="selectedWeekly" value="${frequency == 'Weekly' ? 'selected' : '' }"/>
                                <c:set var="selectedMonthly" value="${frequency == 'Monthly' ? 'selected' : '' }"/>
                                <option ${selectedDaily} value="Daily"><fmt:message key='Daily'/></option>
                                <option ${selectedWeekly} value="Weekly"><fmt:message key='Weekly'/></option>
                                <option ${selectedMonthly} value="Monthly"><fmt:message key='Monthly'/></option>
                            </select>
                        </td>
                    </tr>
                    <tr class="periodical-input1">
                        <th><fmt:message key='month_price'/></th>
                        <td>
                            <input type="number" name="periodicalPrice" value="${periodical.price}" required>
                        </td>
                    </tr>
                    <tr class="periodical-input1">
                        <th><fmt:message key='description'/></th>
                        <td>
                            <textarea name="periodicalDescription" required>${periodical.description}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <input type="submit" value="<fmt:message key='main_page.save'/>"/>
                        </th>
                    </tr>
                </table>
            </div>
            <input type="hidden" name="command" value="editPeriodical"/><br/>
            <input type="hidden" name="email" value="${user.email}"/><br/>
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