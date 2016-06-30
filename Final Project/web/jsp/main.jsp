<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent" var="rb"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.title" bundle="${ rb }" /></title>
</head>
<body>
<%@ include file="common/header.jsp" %>

<c:import var="xmlContent" url="http://www.med2.ru/rss.php"/>

<x:parse var="doc" xml="${xmlContent}"/>
<h2 style="text-align: center">Новости</h2>
<table class="content-table" id="feed">
    <x:forEach var="story"
               select="$doc/rss/channel/item" varStatus="status">
        <tr>
            <td colspan="2">
                <hr/>
            </td>
        </tr>
        <tr>
            <td><h3><x:out select="title"/></h3></td>
        </tr>
        <tr>
            <td><x:out select="category"/></td>
        </tr>
        <tr valign="top">
            <td class="clearfix">
                <img class="rssImg" src=
                        <x:out select="enclosure/@url"/>>
                <div class="rssText">
                    <x:out select="description" escapeXml="false"/></div>
            </td>
        </tr>
    </x:forEach>
</table>
</body>
</html>