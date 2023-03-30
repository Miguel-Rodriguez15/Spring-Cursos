package org.miguel.springcloud.mvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * Clase curso para cliente rest comuncacion entre microservicios
 * y uso de los metodos del microservico curso
 * */
@FeignClient(name = "mvc-cursos", url = "${mvc.cursos.url}" )
public interface CursoClienteRest {
    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
