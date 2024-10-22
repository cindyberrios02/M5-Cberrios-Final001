<%--
  Created by IntelliJ IDEA.
  User: cindy
  Date: 22-10-2024
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Error - Horóscopo Chino</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow text-center">
        <div class="card-header bg-danger text-white">
          <h4 class="mb-0">Error</h4>
        </div>
        <div class="card-body">
          <h1 class="display-1 text-danger">Oops!</h1>
          <p class="lead">Algo salió mal.</p>
          <p>Por favor, intenta nuevamente más tarde.</p>
          <div class="mt-4">
            <a href="login.jsp" class="btn btn-primary">Volver al inicio</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
