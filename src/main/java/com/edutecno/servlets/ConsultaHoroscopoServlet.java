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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/consultaHoroscopo")
public class ConsultaHoroscopoServlet extends HttpServlet {
    private final HoroscopoDAO horoscopoDAO = new HoroscopoDAOImpl();
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Horoscopo> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
            String animal = calcularAnimal(usuario.getFechaNacimiento(), listaHoroscopo);
            usuario.setAnimal(animal);
            usuarioDAO.actualizarUsuario(usuario);

            request.setAttribute("horoscopo", animal);
            request.getRequestDispatcher("horoscopo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}