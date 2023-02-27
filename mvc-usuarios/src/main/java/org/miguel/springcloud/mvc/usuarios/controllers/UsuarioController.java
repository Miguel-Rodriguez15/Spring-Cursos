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

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.buscarUsuarioPorId(id);
        if(usuarioOptional.isPresent())
            return  ResponseEntity.ok().body(usuarioOptional.get());

        return ResponseEntity.notFound().build();
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> optionalUsuario = usuarioService.buscarUsuarioPorId(id);
        if(optionalUsuario.isPresent()){
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(),"El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
