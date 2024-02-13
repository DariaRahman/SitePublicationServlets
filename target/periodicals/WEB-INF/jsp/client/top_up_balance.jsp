<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Top up balance</title>
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
            <input type="hidden" name="command" value="topUpBalance"/>
            <div class="signup-form">
                <h1><fmt:message key='payment_form'/></h1>
                <div class="signup-input">
                    <p><fmt:message key='amount'/></p>
                    <input type="number" name="amount" required/><br/>
                    <p><fmt:message key='card_number'/></p>
                    <input type="text" name="cardNumber" placeholder="XXXX XXXX XXXX XXXX" value="4145 3312 4315 9843"
                           required/><br/>
                    <p><fmt:message key='expiration'/></p>
                    <input type="text" name="validity" placeholder="XX/XX" value="21/07" required/></br>
                    <p>CVV2</p>
                    <input type="text" name="CVV2" value="221" required/><br/>
                    <input type="submit" value="<fmt:message key='top_up_balance'/>"/>
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