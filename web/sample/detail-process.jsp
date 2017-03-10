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
        <% Claim c = (Claim) session.getAttribute("ClaimDetail");%>

        <h1><%=c.getTitle()%></h1>
        <hr/>
        <h3>Feedback from: <strong><%=c.getIdUser()%> (<%=c.getUserFullName()%>)</strong></h3>
        <p>
            <%=c.getContent()%>
        </p>
        <a href="Agile-Manifesto.pdf" target="_blank"><img src="http://placehold.it/350x150?text=Mina Phan" class="img-thumbnail"></a>
        <a href="Agile-Manifesto.pdf" target="_blank"><img src="http://placehold.it/350x150? text=Mina Phan" class="img-thumbnail"></a>
        <hr/>
        <h3>Reply from: <strong><%=c.getDecision().getIdUser()%></strong></h3>
        <p><%=c.getDecision().getContent()%></p>
    </body>
</html>