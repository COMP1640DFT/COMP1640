<%@page import="entity.Claim"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Agile Manifeso</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/bootstrap-theme.css">
    </head>
    <body>
        <jsp:useBean id="ClaimDetail" class="entity.Claim" scope="session"></jsp:useBean>
        <jsp:useBean id="DecisionDetail" class="entity.Decision" scope="session"></jsp:useBean>
        <% Claim c = (Claim) session.getAttribute("ClaimDetail");%>

        <h1>${ClaimDetail.title}</h1>
        <hr/>
        <h3>Feedback from: <strong>${ClaimDetail.idUser} (${ClaimDetail.userFullName})</strong></h3>
        <p>
            ${ClaimDetail.content}
        </p>
        <a href="${ClaimDetail.filedata}" target="_blank"><img src="http://placehold.it/350x150?text=File" class="img-thumbnail"></a>
        <hr/>
        <h3>Reply from: <strong>${DecisionDetail.idUser}</strong></h3>
        <p>${DecisionDetail.content}</p>
    </body>
</html>