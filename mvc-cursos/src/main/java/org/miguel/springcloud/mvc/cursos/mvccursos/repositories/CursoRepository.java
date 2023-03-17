package org.miguel.springcloud.mvc.cursos.mvccursos.repositories;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CursoRepository  extends CrudRepository<Curso, Long> {
    /**
     * Elimina al usuario de la entidad CursoUsuario cuando te envie el id por parametro
     * **/
    @Modifying
    @Query("DELETE FROM CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);

}
