package org.miguel.springcloud.mvc.cursos.mvccursos.clients;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
     Usuario listarUsuarioPorId(@PathVariable Long id);

    @PostMapping("/")
    Usuario crearUsuario( @RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);


}
