<%-- 
    Document   : sub
    Created on : 30 мар. 2022 г., 19:00:25
    Author     : Alex
--%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование дисциплины</title>
    </head>
    <body>
        <form action='subdir.jsp' method="post">
            <jsp:useBean id="sub" class="beans.Subject" scope="page" />
            
            <c:if test="${empty param.create}" >
                ${sub.setCon(sessionScope.DataSource)}
                ${sub.load(param.id)}
            </c:if> 
            
            <table>
                <c:choose>
                    <c:when test="${empty param.create}">
                        <tr> <th colspan="2" align="center">Редактирование дисциплины </th> </tr> 
                    </c:when>
                    <c:otherwise>
                        <tr> <th colspan="2" align="center">Новая дисциплина </th> </tr> 
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td align="right">Наименование:</td> 
                    <td align="left" ><input type="text" name='name' value='${sub.name}' /> </td>
                </tr>   <tr>
                    <td align="right"><input type="submit" name='save' value='Сохранить' formMethod="post" formAction="subsave"/></td> 
                    <td align="left"><input type="submit" name='cancel' value='Отмена' formMethod="post" formAction="subdir.jsp"/></td>
                </tr>   
            </table>
            <input type="hidden" name="id" value="${param.id}" />
        </form>

    </body>
</html>
