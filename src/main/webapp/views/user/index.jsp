<%--
  Created by IntelliJ IDEA.
  User: Erick Morales
  Date: 05/07/2023
  Time: 10:32 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--sIGNIFICA QUE SE USA LENGUAJE JAVA  o que se incrusta JAVA-->
<!DOCTYPE html>
<html>
<head>
    <title>Usuarios</title>
    <jsp:include page="../../layouts/head.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col text-center mt-5">
            <h2>USUARIOS</h2>
            <p>
                CRUD de usuarios
            </p>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col">listado de usuarios</div>
                        <div class="col text-end">
                            <a href="/user/user-view" class="btn btn-outline-success btn-sm">
                                Agregar
                            </a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-stripped">
                        <thead>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Fecha de nacimiento</th>
                        <th>Correo</th>
                        <th>Acciones</th>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}" varStatus="s">
                            <tr>
                                <td>
                                    <c:out value="${s.count}" />
                                </td>
                                <td>
                                    <c:out value="${user.name}"/> <c:out value="${user.surname}"/> <c:out value="${user.lastname}"/>
                                </td>
                                <td>
                                    <c:out value="${user.birthday}" />
                                </td>
                                <td>
                                    <c:out value="${user.email}" />
                                </td>
                                <td>
                                    <form method="get" action="/user/user-view-update">
                                        <input hidden value="${user.id}" name="id">
                                        <button type="submit" class="btn btn-outline-warning btn-sm"></button>
                                    </form>
                                    <form method="post" action="/user/delete">
                                        <input hidden value="${user.id}" name="id">
                                        <button type="submit" class="btn btn-outline-danger btn-sm"></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6">
                                Sin registro
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/footer.jsp"/>
</body>
</html>
