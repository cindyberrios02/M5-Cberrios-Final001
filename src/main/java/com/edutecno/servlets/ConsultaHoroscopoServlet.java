package com.edutecno.servlets;

import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dao.HoroscopoDAOImpl;
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
            Date fechaNacimiento = usuario.getFechaNacimiento();

            // Convertir la fecha de nacimiento a Calendar para manipulación más fácil
            Calendar calNacimiento = Calendar.getInstance();
            calNacimiento.setTime(fechaNacimiento);

            // Variables para almacenar el año de referencia y el año de nacimiento
            int yearNacimiento = calNacimiento.get(Calendar.YEAR);
            int mesNacimiento = calNacimiento.get(Calendar.MONTH) + 1; // Los meses en Calendar empiezan en 0
            int diaNacimiento = calNacimiento.get(Calendar.DAY_OF_MONTH);

            for (Horoscopo horoscopo : listaHoroscopo) {
                Calendar calInicio = Calendar.getInstance();
                Calendar calFin = Calendar.getInstance();
                calInicio.setTime(horoscopo.getFecha_inicio());
                calFin.setTime(horoscopo.getFecha_fin());

                // Ajustar al año de nacimiento
                calInicio.set(Calendar.YEAR, yearNacimiento);
                calFin.set(Calendar.YEAR, yearNacimiento);

                // Si la fecha fin es menor que la fecha inicio, significa que cruza año nuevo
                if (calFin.before(calInicio)) {
                    calFin.add(Calendar.YEAR, 1);
                }

                Date inicioAjustado = calInicio.getTime();
                Date finAjustado = calFin.getTime();

                // Verificar si la fecha de nacimiento está en el rango
                if ((fechaNacimiento.equals(inicioAjustado) || fechaNacimiento.after(inicioAjustado)) &&
                        (fechaNacimiento.equals(finAjustado) || fechaNacimiento.before(finAjustado))) {
                    usuario.setAnimal(horoscopo.getAnimal());
                    break;
                }
            }

            request.setAttribute("horoscopo", usuario.getAnimal());
            request.getRequestDispatcher("horoscopo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}