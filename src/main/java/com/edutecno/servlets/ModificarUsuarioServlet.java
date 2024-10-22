package com.edutecno.servlets;

import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dao.HoroscopoDAOImpl;
import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dao.UsuarioDAOImpl;
import com.edutecno.modelo.Horoscopo;
import com.edutecno.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/modificarUsuario")
public class ModificarUsuarioServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private final HoroscopoDAO horoscopoDAO = new HoroscopoDAOImpl();

    private String calcularAnimal(Date fechaNacimiento, List<Horoscopo> listaHoroscopo) {
        for (Horoscopo horoscopo : listaHoroscopo) {
            Date fechaInicio = horoscopo.getFecha_inicio();
            Date fechaFin = horoscopo.getFecha_fin();

            // Ajustar al año de nacimiento
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaNacimiento);
            int yearNacimiento = cal.get(Calendar.YEAR);

            Calendar calInicio = Calendar.getInstance();
            Calendar calFin = Calendar.getInstance();
            calInicio.setTime(fechaInicio);
            calFin.setTime(fechaFin);

            calInicio.set(Calendar.YEAR, yearNacimiento);
            calFin.set(Calendar.YEAR, yearNacimiento);

            // Si la fecha fin es menor que la fecha inicio, significa que cruza año nuevo
            if (calFin.before(calInicio)) {
                calFin.add(Calendar.YEAR, 1);
            }

            if ((fechaNacimiento.after(calInicio.getTime()) || fechaNacimiento.equals(calInicio.getTime())) &&
                    (fechaNacimiento.before(calFin.getTime()) || fechaNacimiento.equals(calFin.getTime()))) {
                return horoscopo.getAnimal();
            }
        }
        return "No asignado";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);

            if (usuario != null) {
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setEmail(request.getParameter("email"));

                // Manejar la fecha de nacimiento
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = sdf.parse(request.getParameter("fechaNacimiento"));
                usuario.setFechaNacimiento(fechaNacimiento);

                // Manejar la contraseña
                String password = request.getParameter("password");
                if (password != null && !password.trim().isEmpty()) {
                    usuario.setPassword(password);
                }

                // Calcular el animal basado en la fecha de nacimiento
                List<Horoscopo> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
                String animal = calcularAnimal(fechaNacimiento, listaHoroscopo);
                usuario.setAnimal(animal);

                usuarioDAO.actualizarUsuario(usuario);
                response.sendRedirect("listarUsuarios?mensaje=Usuario modificado exitosamente");
            } else {
                response.sendRedirect("listarUsuarios?error=Usuario no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarUsuarios?error=Error al modificar usuario");
        }
    }
}

