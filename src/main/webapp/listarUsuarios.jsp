<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edutecno.modelo.Usuario" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listar Usuarios - Horóscopo Chino</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
  <link href="css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary">
  <div class="container">
    <span class="navbar-brand">Horóscopo Chino</span>
    <div>
      <a href="menu.jsp" class="btn btn-outline-light me-2">
        <i class="bi bi-house-door-fill"></i> Menú
      </a>
      <form action="logout" method="post" class="d-inline">
        <button type="submit" class="btn btn-outline-light">
          <i class="bi bi-box-arrow-right"></i> Cerrar sesión
        </button>
      </form>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <div class="card shadow">
    <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
      <h4 class="mb-0">Lista de Usuarios</h4>
      <span class="badge bg-light text-primary">
                    <%
                      List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
                      out.print(usuarios != null ? usuarios.size() : 0);
                    %> usuarios
                </span>
    </div>
    <div class="card-body">
      <% if(request.getParameter("mensaje") != null) { %>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= request.getParameter("mensaje") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <% } %>

      <% if(request.getParameter("error") != null) { %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= request.getParameter("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <% } %>

      <div class="table-responsive">
        <table class="table table-hover">
          <thead class="table-light">
          <tr>
            <th>Nombre</th>
            <th>Usuario</th>
            <th>Email</th>
            <th>Animal</th>
            <th class="text-center">Acciones</th>
          </tr>
          </thead>
          <tbody>
          <% if(usuarios != null && !usuarios.isEmpty()) {
            for(Usuario user : usuarios) { %>
          <tr>
            <td><%= user.getNombre() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td>
                                        <span class="badge bg-info text-dark">
                                            <%= user.getAnimal() != null ? user.getAnimal() : "No asignado" %>
                                        </span>
            </td>
            <td class="text-center">
              <a href="modificarUsuario?id=<%= user.getId() %>"
                 class="btn btn-warning btn-sm me-1">
                <i class="bi bi-pencil-fill"></i> Modificar
              </a>
              <button onclick="prepararEliminacion('<%= user.getId() %>', '<%= user.getNombre() %>')"
                      class="btn btn-danger btn-sm">
                <i class="bi bi-trash-fill"></i> Eliminar
              </button>
            </td>
          </tr>
          <% }
          } else { %>
          <tr>
            <td colspan="5" class="text-center">No hay usuarios registrados</td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Modal de Confirmación para Eliminar -->
<div class="modal fade" id="eliminarModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title">
          <i class="bi bi-exclamation-triangle-fill"></i>
          Confirmar Eliminación
        </h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p>¿Está seguro que desea eliminar al usuario <strong id="nombreUsuario"></strong>?</p>
        <p class="text-danger"><small>Esta acción no se puede deshacer.</small></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
          <i class="bi bi-x-circle"></i> Cancelar
        </button>
        <form id="formEliminar" action="eliminarUsuario" method="post">
          <input type="hidden" id="userId" name="id" value="">
          <button type="submit" class="btn btn-danger">
            <i class="bi bi-trash-fill"></i> Eliminar
          </button>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function prepararEliminacion(id, nombre) {
    document.getElementById('userId').value = id;
    document.getElementById('nombreUsuario').textContent = nombre;
    new bootstrap.Modal(document.getElementById('eliminarModal')).show();
  }

  // Auto-cerrar alertas después de 5 segundos
  document.addEventListener('DOMContentLoaded', function() {
    setTimeout(function() {
      var alerts = document.getElementsByClassName('alert');
      for(var i = 0; i < alerts.length; i++) {
        var alert = alerts[i];
        var bsAlert = new bootstrap.Alert(alert);
        bsAlert.close();
      }
    }, 5000);
  });
</script>
</body>
</html>