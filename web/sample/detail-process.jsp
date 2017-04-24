<%@page import="entity.Claim"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Agile Manifeso</title>
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/bootstrap-theme.css">
    </head>
    <body>
        <jsp:useBean id="ClaimDetail" class="entity.Claim" scope="session"></jsp:useBean>
        <jsp:useBean id="DecisionDetail" class="entity.Decision" scope="session"></jsp:useBean>
        <jsp:useBean id="account" class="entity.Account" scope="session"></jsp:useBean>
        <% if (account.getLever() != 3) {%>
        <jsp:forward page="logout.jsp"></jsp:forward>
        <%}%>
        <% Claim c = (Claim) session.getAttribute("ClaimDetail");%>

        <h1>${ClaimDetail.title}</h1>
        <hr/>
        <h3>Feedback from: <strong>${ClaimDetail.idUser} (${ClaimDetail.userFullName})</strong></h3>
        <p>
            ${ClaimDetail.content}
        </p>
        <c:if test="${ClaimDetail.filedata != ''}">
            <a href="${ClaimDetail.filedata}" target="_blank"><img src="http://placehold.it/350x150?text=File" class="img-thumbnail"></a>
        </c:if>
        <hr/>
        <h3>Reply from: <strong>${DecisionDetail.idUser}</strong></h3>
        <p>${DecisionDetail.content}</p>
    </body>
</html>