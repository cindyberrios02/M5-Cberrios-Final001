package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dao.UsuarioDAOImpl;
import com.edutecno.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.obtenerUsuario(username);

        if (usuario == null) {
            request.setAttribute("error", "Usuario no encontrado. Por favor, regístrese.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (!usuario.getPassword().equals(password)) {
            request.setAttribute("error", "Contraseña incorrecta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario);
        response.sendRedirect("menu.jsp");
    }
}