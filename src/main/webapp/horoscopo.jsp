<%--
  Created by IntelliJ IDEA.
  User: cindy
  Date: 22-10-2024
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Horóscopo Chino - Tu Animal</title>
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

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow text-center">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Tu Animal del Horóscopo Chino</h4>
                </div>
                <div class="card-body">
                    <h2 class="display-4 mb-4">${horoscopo}</h2>
                    <p class="lead">Este es tu animal según el horóscopo chino basado en tu fecha de nacimiento.</p>
                    <div class="mt-4">
                        <a href="menu.jsp" class="btn btn-primary">Volver al menú</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>