package org.miguel.springcloud.mvc.usuarios.repositories;

import org.miguel.springcloud.mvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository  extends CrudRepository<Usuario,Long> {
}
