<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganisationSection" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>

        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
    <h3><a name="type.name">${type.title}</a></h3>
    <c:choose>
        <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
            <h4><li><%=((TextSection) section).getContent()%></li></h4>
        </c:when>
        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
            <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                <h4><li>${item}</li></h4>
            </c:forEach>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
            <c:forEach var="organisation" items="<%=((OrganisationSection) section).getOrganisations()%>">
                <h4><a href="${organisation.homePage.url}">${organisation.homePage.name}</a></h4>
                <c:forEach var="position" items="${organisation.positions}">
                    <jsp:useBean id="position" type="com.urise.webapp.model.Organisation.Position"/>
                    <h4><%=DateUtil.date(position)%></h4>
                    <h4>${position.title}</h4>
                    <h4>${position.description}</h4>
                    <hr>
                </c:forEach>
            </c:forEach>
        </c:when>
    </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>