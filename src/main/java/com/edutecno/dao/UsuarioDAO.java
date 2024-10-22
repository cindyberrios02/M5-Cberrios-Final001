package com.edutecno.dao;

import com.edutecno.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    Usuario obtenerUsuario(String username);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuarioPorId(Long id);

}