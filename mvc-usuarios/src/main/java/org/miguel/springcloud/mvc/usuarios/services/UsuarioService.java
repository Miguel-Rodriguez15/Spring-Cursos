package org.miguel.springcloud.mvc.usuarios.services;

import org.miguel.springcloud.mvc.usuarios.models.entity.Usuario;
import org.miguel.springcloud.mvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();
    Optional<Usuario> buscarUsuarioPorId(Long id);
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
}
