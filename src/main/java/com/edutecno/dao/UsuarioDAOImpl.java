package com.edutecno.dao;

import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.AdministradorConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl extends AdministradorConexion implements UsuarioDAO {

    @Override
    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, animal) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            System.out.println("Intentando crear usuario: " + usuario.getUsername());

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getUsername());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pstmt.setString(5, usuario.getPassword());
            pstmt.setString(6, usuario.getAnimal());

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Usuario creado. Filas afectadas: " + filasAfectadas);

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                    System.out.println("ID generado: " + usuario.getId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al crear usuario", e);
        }
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        Usuario usuario = null;

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsername(rs.getString("username"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAnimal(rs.getString("animal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsername(rs.getString("username"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAnimal(rs.getString("animal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }


    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, fecha_nacimiento = ?, password = ?, animal = ? WHERE id = ?";

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pstmt.setString(4, usuario.getPassword());
            pstmt.setString(5, usuario.getAnimal());
            pstmt.setLong(6, usuario.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = generaPoolConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Intentando eliminar usuario con ID: " + id);
            pstmt.setLong(1, id);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el usuario con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = generaPoolConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setUsername(rs.getString("username"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setPassword(rs.getString("password"));
                usuario.setAnimal(rs.getString("animal"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


}