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

    @Override
    @Transactional
    public Curso GuardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void EliminarCurso(Long id) {
     cursoRepository.deleteById(id);
    }

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
