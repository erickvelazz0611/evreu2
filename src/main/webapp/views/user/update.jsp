<%--
  Created by IntelliJ IDEA.
  User: Erick Morales
  Date: 05/07/2023
  Time: 10:42 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--sIGNIFICA QUE SE USA LENGUAJE JAVA  o que se incrusta JAVA-->
<html>
<head>
    <title>Modificación de usuario</title>
    <jsp:include page="../../layouts/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <container class="row">
        <div class="col">
            <div class="card mt-5">
                <div class="card-header">Registro de usuario</div>
                <div class="card-body">
                    <form id="user-form" class="needs-validation" novalidate action="./user/save" method="post">
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col">
                                    <label for="name" class="fw-bold">Nombre:</label>
                                    <input type="text" name="name" id="name" class="form-control" value="${user.name}" required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <div class="col">
                                    <label for="surname" class="fw-bold">Primer Apellido:</label>
                                    <input type="text" name="surname" id="surname" class="form-control" value="${user.surname}" required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <div class="col">
                                    <label for="lastname" class="fw-bold">Segundo Apellido:</label>
                                    <input type="text" name="lastname" id="lastname" class="form-control" value="${user.lastname}" required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group md-3">
                            <div class="row">
                                <div class="col">
                                    <label for="birthday" class="fw-bold">Fecha de nacimiento:</label>
                                    <input type="date" name="birthday" id="birthday" class="form-control" value="${user.birthday}" required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <div class="col">
                                    <label for="email" class="fw-bold">Correo:</label>
                                    <input type="email" name="email" id="email" class="form-control" value="${user.email}" required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>
                        </div>
                        <input hidden value="${user.id}" name="id" />
                        <div class="form-group mb-5">
                            <div class="row">
                                <div class="col text-end">
                                    <a href="./user/users" class="button btn btn-outline-danger btn-sm">CANCELAR</a>
                                    <button type="submit" class="btn btn-outline-success btn-sm">ACEPTAR</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </container>
</div>
<jsp:include page="../../layouts/footer.jsp"/>
<script>
    (function (){
        const form = document.getElementById("user-form");
        form.addEventListener("submit", function (event){
            if(!form.checkValidity()){
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validated");
        }, false);
    })();
</script>
</body>
</html>
