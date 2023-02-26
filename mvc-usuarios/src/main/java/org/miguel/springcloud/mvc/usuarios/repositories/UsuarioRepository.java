package org.miguel.springcloud.mvc.usuarios.repositories;

import org.miguel.springcloud.mvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository  extends CrudRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
}
