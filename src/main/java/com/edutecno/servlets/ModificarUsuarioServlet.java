package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dao.UsuarioDAOImpl;
import com.edutecno.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modificarUsuario")
public class ModificarUsuarioServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        try {
            Long id = Long.parseLong(idStr);
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);

            if(usuario != null) {
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/modificarUsuario.jsp").forward(request, response);
            } else {
                response.sendRedirect("listarUsuarios?error=Usuario no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=Error al procesar la solicitud");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
            if (usuario != null) {
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                if (password != null && !password.trim().isEmpty()) {
                    usuario.setPassword(password);
                }

                usuarioDAO.actualizarUsuario(usuario);
                response.sendRedirect("listarUsuarios?mensaje=Usuario modificado exitosamente");
            } else {
                response.sendRedirect("listarUsuarios?error=Usuario no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al modificar el usuario");
            request.getRequestDispatcher("/modificarUsuario.jsp").forward(request, response);
        }
    }
}
