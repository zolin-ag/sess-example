<%-- 
    Document   : stud
    Created on : 31 мар. 2022 г., 19:57:37
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
        <title>Редактирование данных о студенте</title>
    </head>
    <body>
        <form action='studdir.jsp' method="post">
            <jsp:useBean id="stud" class="beans.Stud" scope="page" />
            
            <c:if test="${empty param.create}" >
                ${stud.setCon(sessionScope.DataSource)}
                ${stud.load(param.id)}
            </c:if> 
            
            <table>
                <c:choose>
                    <c:when test="${empty param.create}">
                        <tr> <th colspan="2" align="center">Редактирование данных о студенте </th> </tr> 
                    </c:when>
                    <c:otherwise>
                        <tr> <th colspan="2" align="center">Новый студент </th> </tr> 
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td align="right">Фамилия:</td> 
                    <td align="left" ><input type="text" name='last_name' value='${stud.lastName}' /> </td>
                </tr>   
                <tr>
                    <td align="right">Имя:</td> 
                    <td align="left" ><input type="text" name='first_name' value='${stud.firstName}' /> </td>
                </tr>   
                <tr>
                    <td align="right">Полных лет:</td> 
                    <td align="left" ><input type="text" name='age' value='${stud.age}' /> </td>
                </tr>   
                <tr>
                    <td align="right"><input type="submit" name='save' value='Сохранить' formMethod="post" formAction="studsave"/></td> 
                    <td align="left"><input type="submit" name='cancel' value='Отмена' formMethod="post" formAction="studdir.jsp"/></td>
                </tr>   
            </table>
            <input type="hidden" name="id" value="${param.id}" />
        </form>

    </body>
</html>
