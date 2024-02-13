<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit profile</title>
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
        <form action="controller" method="post">
            <div class="client-info">
                <table class="client-table">
                    <tr>
                        <th class="profile-name" colspan="2"><fmt:message key='edit_profile'/></th>
                    </tr>
                    <tr>
                        <th><fmt:message key='name'/></th>
                        <td>
                            <input type="text" name="first_name" value="${user.firstName}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='login_page_surname'/></th>
                        <td>
                            <input type="text" name="last_name" value="${user.lastName}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='login_page_gender'/></th>
                        <td>
                            <select name="gender">
                                <c:set var="gender" value="${user.gender}"/>
                                <c:set var="selectedMale" value="${gender == 'MALE' ? 'selected' : '' }"/>
                                <c:set var="selectedFemale" value="${gender == 'FEMALE' ? 'selected' : '' }"/>
                                <option ${selectedMale} value="MALE"><fmt:message key='male'/></option>
                                <option ${selectedFemale} value="FEMALE"><fmt:message key='female'/></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key='password'/></th>
                        <td>
                            <input type="password" name="password" value="${user.password}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <input type="submit" value="<fmt:message key='main_page.save'/>"/>
                        </th>
                    </tr>
                </table>
            </div>
            <input type="hidden" name="command" value="updateUser"/><br/>
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