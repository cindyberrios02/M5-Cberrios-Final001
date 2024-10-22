<%--
  Created by IntelliJ IDEA.
  User: cindy
  Date: 22-10-2024
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edutecno.modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Usuario - Horóscopo Chino</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">Horóscopo Chino</span>
        <div>
            <a href="menu.jsp" class="btn btn-outline-light me-2">Menú</a>
            <form action="logout" method="post" class="d-inline">
                <button type="submit" class="btn btn-outline-light">Cerrar sesión</button>
            </form>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Modificar Usuario</h4>
                </div>
                <div class="card-body">
                    <%
                        Usuario usuario = (Usuario)request.getAttribute("usuario");
                        if(usuario != null) {
                    %>
                    <form action="modificarUsuario" method="post">
                        <input type="hidden" name="id" value="<%= usuario.getId() %>">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre completo</label>
                            <input type="text" class="form-control" id="nombre" name="nombre"
                                   value="<%= usuario.getNombre() %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   value="<%= usuario.getEmail() %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Nueva contraseña (dejar en blanco para mantener la actual)</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                            <a href="listarUsuarios" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                    <%
                    } else {
                    %>
                    <div class="alert alert-danger">Usuario no encontrado</div>
                    <div class="d-grid">
                        <a href="listarUsuarios" class="btn btn-secondary">Volver</a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>