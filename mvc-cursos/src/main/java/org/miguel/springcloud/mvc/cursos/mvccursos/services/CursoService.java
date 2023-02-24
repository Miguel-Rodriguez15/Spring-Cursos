package org.miguel.springcloud.mvc.cursos.mvccursos.services;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> ListarCurso();
    Optional<Curso> BuscarCursoPorId(Long id);
    Curso GuardarCurso(Curso curso);
    void EliminarCurso(Long id);
}
