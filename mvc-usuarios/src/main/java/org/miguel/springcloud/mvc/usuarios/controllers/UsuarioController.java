package org.miguel.springcloud.mvc.usuarios.controllers;

import org.miguel.springcloud.mvc.usuarios.models.entity.Usuario;
import org.miguel.springcloud.mvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
/**
 * @author Miguel Rodriguez
 * Clase controlador por los usuarios
 * */

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    /***
     * Metodo para el listado de todos los usuarios
     * */
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }
    /***
     * Metodo para consultar el usuario por ID
     * @param id : identificador del usuario en la base de datos
     * */
    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.buscarUsuarioPorId(id);
        if(usuarioOptional.isPresent())
            return  ResponseEntity.ok().body(usuarioOptional.get());

        return ResponseEntity.notFound().build();
    }
    /***
     *Si no hay errores de validación y el correo electrónico no existe en la base de datos,
     * el código guarda el objeto Usuario en la base de datos y devuelve una respuesta HTTP
     * con el código de estado 201 (Created) y el objeto Usuario guardado en el cuerpo de la respuesta.
     * @param result : resultado de la busqueda del usuario y validacion
     * @param usuario : entidad Usuario
     * */
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){

        if(result.hasErrors()){
            return validar(result);
        }
        if(!usuario.getEmail().isEmpty() && usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","ya existe un usuario con este correo!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    /***
     * si el usuario existe en la base de datos, atualiza los campos
     * del objeto usuario con los valores proporcionados y devuelve
     * una respuesta con el codigo de estado 201(created)
     * @param id : identificador del usuario en la base de datos
     * @param usuario : entidad Usuario
     * @param result : resultado de la busqueda del usuario y validacion
     * */

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result , @PathVariable Long id){

        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Usuario> aux = usuarioService.buscarUsuarioPorId(id);

        if(aux.isPresent()){
            Usuario editarUsuario = aux.get();
            if(!usuario.getEmail().isEmpty() &&
                    !usuario.getEmail().equalsIgnoreCase(editarUsuario.getEmail()) &&
                    usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","ya existe un usuario con este correo!"));
            }
            editarUsuario.setNombre(usuario.getNombre());
            editarUsuario.setEmail(usuario.getEmail());
            editarUsuario.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(editarUsuario));
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Consulta primero el id para validar de que este presente
     * eliminame el usuario por id y resturname el valor vacio
     * */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> optionalUsuario = usuarioService.buscarUsuarioPorId(id);
        if(optionalUsuario.isPresent()){
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    /**
     *Este código es un método auxiliar que se utiliza para procesar los errores
     * de validación en los controladores de servicios RESTful.
     * El método recibe un objeto BindingResult que contiene los errores
     * de validación producidos durante la validación de un objeto de entrada.
     * El método crea un mapa de errores (Map<String, String>) que se
     * utiliza para almacenar los errores de validación.
     * **/
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    /**
     * Recibe por parametros los Id y depliegame una lista de los alumnos ingresados
     * */
    @GetMapping("/usuarios-por-curso")
    public  ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(usuarioService.ListarUsuariosPorId(ids));
    }

}
