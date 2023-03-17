package org.miguel.springcloud.mvc.usuarios.services;

import org.miguel.springcloud.mvc.usuarios.models.entity.Usuario;
import org.miguel.springcloud.mvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/***
 * @author miguel rodriguuez
 * clase servicio para los usuarios
 */

public interface UsuarioService {
     /**
      * metodo para listar todos los usuarios
      * */
    List<Usuario> listarUsuarios();
    /**
     * metodo para buscar los usuarios por
     * el Id
     * */
    Optional<Usuario> buscarUsuarioPorId(Long id);
    /****
     * Metodo para guardar los Usuarios en BD
     */

    Usuario guardarUsuario(Usuario usuario);
    /****
     * Metodo para Eliminar los Usuarios en BD
     */
    void eliminarUsuario(Long id);
    /****
     * Metodo para Listar los Usuarios por id
     */
    List<Usuario> ListarUsuariosPorId(Iterable<Long> ids);
    /****
     * Metodo para buscar los Usuarios por Email
     */
    Optional<Usuario> buscarPorEmail(String email);

}
