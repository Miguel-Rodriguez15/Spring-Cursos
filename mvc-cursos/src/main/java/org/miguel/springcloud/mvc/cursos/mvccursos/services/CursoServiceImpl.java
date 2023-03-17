package org.miguel.springcloud.mvc.cursos.mvccursos.services;

import org.miguel.springcloud.mvc.cursos.mvccursos.clients.UsuarioClientRest;
import org.miguel.springcloud.mvc.cursos.mvccursos.models.Usuario;
import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;
import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.CursoUsuario;
import org.miguel.springcloud.mvc.cursos.mvccursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl  implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioClientRest cliente;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> ListarCurso() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> BuscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

     /**
      * consultame el curso por id
      * en caso de que si exista y si  no esta vacio
      * listame los datos del usuario
      * */
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> BuscarCursoPorIdConUsuarios(Long id) {
        Optional<Curso> o = cursoRepository.findById(id);
        if(o.isPresent()){
            Curso curso = o.get();
            if(!curso.getCursoUsuarios().isEmpty()){
                List<Long> ids = curso.getCursoUsuarios().stream().map(cu -> cu.getUsuarioId())
                        .collect(Collectors.toList());
                List<Usuario> usuarios = cliente.obtenerAlumnosPorCurso(ids);
                curso.setUsuarios(usuarios);
            }
            return Optional.of(curso);
        }
        return Optional.empty();
    }
    /**
     * metodo paara guardar un curso en BD
     * */
    @Override
    @Transactional
    public Curso GuardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
    /**
     * metodo paara eliminar un curso en BD por id
     * */
    @Override
    @Transactional
    public void EliminarCurso(Long id) {
     cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void eliminarCursoUsuarioPorId(Long id) {
        cursoRepository.eliminarCursoUsuarioPorId(id);
    }
  /***
   * consultame por id el curso
   * en caso de que este presente si envia los paramtros por json
   * guardandolos en BD
   * */
    @Override
    @Transactional
    public Optional<Usuario> asignarUsuarioCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> aux = cursoRepository.findById(cursoId);
        if(aux.isPresent()){
            Usuario usuarioMSVC = cliente.listarUsuarioPorId(usuario.getId());

            Curso curso = aux.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMSVC.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMSVC);
        }

        return Optional.empty();
    }
    /***
     * consultame el curso por id en caso de que este presente
     * usa el metodo crearUsuario devuele el curso y los datos del usuario
     * despues agrega el usuario al curso por ultimo guardarlo en el curso
     * */
    @Override
    @Transactional
    public Optional<Usuario> crearUsuarioCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> aux = cursoRepository.findById(cursoId);
        if(aux.isPresent()){
            Usuario usuarioNuevoMSVC = cliente.crearUsuario(usuario);

            Curso curso = aux.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMSVC.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioNuevoMSVC);
        }

        return Optional.empty();
    }
    /***
     * consultame el curso por id en caso de que este presente
     * listame el usuario por el metodo listarUsuarioPorId
     * despues por el metodo removeCursoUsuario eliminalo y guarda los cambios
     * despues agrega el usuario al curso por ultimo guardarlo en el curso
     * */
    @Override
    @Transactional
    public Optional<Usuario> desasignarUsuarioCurso(Usuario usuario, Long cursoId) {
        Optional<Curso> aux = cursoRepository.findById(cursoId);
        if(aux.isPresent()){
            Usuario usuarioNuevoMSVC = cliente.listarUsuarioPorId(usuario.getId());

            Curso curso = aux.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMSVC.getId());

            curso.removeCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioNuevoMSVC);
        }

        return Optional.empty();
    }
}
