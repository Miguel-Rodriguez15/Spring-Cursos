package org.miguel.springcloud.mvc.cursos.mvccursos.controllers;

import org.miguel.springcloud.mvc.cursos.mvccursos.models.entity.Curso;
import org.miguel.springcloud.mvc.cursos.mvccursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCurso(){
        return ResponseEntity.ok(cursoService.ListarCurso());

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalleDelCurso(@PathVariable Long id){
     Optional<Curso> o = cursoService.BuscarCursoPorId(id);
     if(o.isPresent()){
         return  ResponseEntity.ok(o.get());

     }else
         return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> crearCurso(@Valid @RequestBody Curso curso, BindingResult result){

        if(result.hasErrors()){
            return  validar(result);
        }
        Curso cursodb = cursoService.GuardarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursodb);

    }
   @PutMapping("/{id}")
   public ResponseEntity<?> editarCurso(@Valid @RequestBody Curso curso,BindingResult result , @PathVariable Long id){
    if(result.hasErrors()){
        return  validar(result);
    }
      Optional<Curso> o = cursoService.BuscarCursoPorId(id);
      if (o.isPresent()){
          Curso cursdodb = o.get();
          cursdodb.setNombre(curso.getNombre());
          return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.GuardarCurso(cursdodb));

      }
      return ResponseEntity.notFound().build();
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id){
        Optional<Curso> o = cursoService.BuscarCursoPorId(id);
        if (o.isPresent()){
            cursoService.EliminarCurso(id);
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
   }
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
