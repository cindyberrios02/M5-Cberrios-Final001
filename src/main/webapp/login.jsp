<%--
  Created by IntelliJ IDEA.
  User: cindy
  Date: 22-10-2024
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Horóscopo Chino - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Horóscopo Chino</h4>
                </div>
                <div class="card-body">
                    <h5 class="card-title mb-4">Iniciar Sesión</h5>

                    <% if (request.getParameter("mensaje") != null && request.getParameter("mensaje").equals("registro_exitoso")) { %>
                    <div class="alert alert-success" role="alert">
                        Registro exitoso. Por favor, inicie sesión.
                    </div>
                    <% } %>

                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                    <form action="login" method="post" onsubmit="return validateLoginForm(this);">
                        <div class="mb-3">
                            <label for="username" class="form-label">Nombre de usuario:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                        <div class="text-center mt-3">
                            <span>¿No tienes cuenta?</span>
                            <a href="registro.jsp" class="text-decoration-none">Regístrate aquí</a>
                        </div>
                    </form>
                    <div id="errorMessages"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/validations.js"></script>
</body>
</html>