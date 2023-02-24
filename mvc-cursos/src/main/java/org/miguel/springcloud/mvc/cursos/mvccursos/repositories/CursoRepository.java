package org.miguel.springcloud.mvc.cursos.mvccursos.repositories;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CursoRepository  extends CrudRepository<Curso, Long> {


}
