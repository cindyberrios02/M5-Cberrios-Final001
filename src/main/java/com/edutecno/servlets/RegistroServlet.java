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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar si el usuario ya existe
            String username = request.getParameter("username");
            if (usuarioDAO.obtenerUsuario(username) != null) {
                request.setAttribute("error", "El nombre de usuario ya existe");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setUsername(username);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(request.getParameter("fechaNacimiento"));
            usuario.setFechaNacimiento(fechaNacimiento);

            usuario.setPassword(request.getParameter("password"));

            usuarioDAO.crearUsuario(usuario);

            // Redirigir con mensaje de éxito
            response.sendRedirect("login.jsp?mensaje=registro_exitoso");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar el registro");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}
