package org.miguel.springcloud.mvc.cursos.mvccursos.services;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.Usuario;
import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;
/**
 * @author Miguel Rodriguez
 * Clase para los servicios del curso
 * */
public interface CursoService {
    /**
     * metodo para Listar todos los cursos
     * */
    List<Curso> ListarCurso();
    /**
     * metodo para buscar  los cursos por id
     * */
    Optional<Curso> BuscarCursoPorId(Long id);
    /**
     * metodo para buscar  los cursos por id y consultar el id del usuario
     * */
    Optional<Curso> BuscarCursoPorIdConUsuarios(Long id);
    /**
     * metodo para guardar  los cursos en BD
     * */
    Curso GuardarCurso(Curso curso);
    /**
     * metodo para eliminar  los cursos en por ID
     * */
    void EliminarCurso(Long id);
    /**
     * metodo para eliminar  un usuario por id en el curso y microservicio usuario
     * */
    void eliminarCursoUsuarioPorId(Long id);
    /**
     * metodo para asignar  un usuario por el id curso
     * */
    Optional<Usuario> asignarUsuarioCurso(Usuario usuario, Long cursoId);
    /**
     * metodo para crear  un usuario por el id curso
     * */
    Optional<Usuario> crearUsuarioCurso(Usuario usuario, Long cursoId);
    /**
     * metodo para desasignar  un usuario por el id curso
     * */
    Optional<Usuario> desasignarUsuarioCurso(Usuario usuario, Long cursoId);
}
