<%--
  Created by IntelliJ IDEA.
  User: cindy
  Date: 22-10-2024
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edutecno.modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Horóscopo Chino - Menú</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">Horóscopo Chino</span>
        <div>
            <span class="text-white me-3">Bienvenido, ${usuario.nombre}</span>
            <form action="logout" method="post" class="d-inline">
                <button type="submit" class="btn btn-outline-light">Cerrar sesión</button>
            </form>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 class="text-center mb-4">¿Qué deseas hacer?</h2>
            <div class="row g-4">
                <div class="col-md-6">
                    <div class="d-grid">
                        <a href="consultaHoroscopo" class="btn btn-primary btn-lg">
                            <i class="bi bi-stars"></i>
                            Conoce tu animal
                        </a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="d-grid">
                        <a href="listarUsuarios" class="btn btn-info btn-lg text-white">
                            <i class="bi bi-people"></i>
                            Buscar usuarios
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

