<%-- 
    Document   : subdir
    Created on : 29 мар. 2022 г., 19:43:50
    Author     : Alex
--%>

<%@page import="beans.Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Справочник дисциплин</title>
    </head>
    <body>
        <%request.setCharacterEncoding("UTF-8");%>

        <jsp:useBean id="subDir" class="beans.SubjectDir" scope="session" />

        <c:if test="${!empty param.findstr}"  var="val" scope="session" >
            ${subDir.setFindStr(param.findstr)}
            ${subDir.fill( sessionScope.DataSource)}
        </c:if> 

        <h1>Справочник дисциплин</h1>
        <!--  Форма поиска -->
        <form action="subdir.jsp" method="get">
            <table>
                <tr>
                    <td align="left">Поиск: &nbsp; <input type="text" name='findstr' value='' /></td> 
                    <td align="left"><input type="submit" value="Найти"/></td>
                </tr>   
            </table>
        </form>
        <form action='#' method="get">
            <table>
                <tr>
                    <td align="left"><input type="submit" value="Выбрать" formMethod="get" formAction="exam.jsp"/></td> 
                    <td align="left"><input type="submit" name="create" value="Создать..." formMethod="get" formAction="sub.jsp"/></td>
                </tr>   
            </table>
            <hr/>
            <table border="1" >
                <th> </th><th>Наименование</th><th>Ред.</th><th>Удалить</th>
                <c:forEach var="sub" items="${subDir.subjects}" >
                    <jsp:useBean id="sub" class="beans.Subject" scope="page" />
                    <tr>
                        <td><input type="radio"  name="sub_rbt" value="${sub.subjectId}" /></td>
                        <td>${sub.name}</td>
                        <td><a href="sub.jsp?id=${sub.subjectId}">Изменить</a></td>
                        <td><a href="subDel?id=${sub.subjectId}">Удалить</a></td>
                    </tr>
                </c:forEach>

            </table>
        </form>

    </body>
</html>
