package org.miguel.springcloud.mvc.cursos.mvccursos.services;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.Usuario;
import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> ListarCurso();
    Optional<Curso> BuscarCursoPorId(Long id);
    Curso GuardarCurso(Curso curso);
    void EliminarCurso(Long id);
    Optional<Usuario> asignarUsuarioCurso(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuarioCurso(Usuario usuario, Long cursoId);
    Optional<Usuario> desagsinarUsuarioCurso(Usuario usuario, Long cursoId);
}
