package com.edutecno.dao;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.AdministradorConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoroscopoDAOImpl extends AdministradorConexion implements HoroscopoDAO {

    @Override
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> horoscopo = new ArrayList<>();
        String consultaSql = "SELECT * FROM horoscopo ORDER BY fecha_inicio";

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstm = conn.prepareStatement(consultaSql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Horoscopo h = new Horoscopo(
                        rs.getString("animal"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin")
                );
                horoscopo.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horoscopo;
    }
}