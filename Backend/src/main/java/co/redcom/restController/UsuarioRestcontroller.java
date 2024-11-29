package co.redcom.restController;

import co.redcom.entity.Usuario;
import co.redcom.service.impl.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestcontroller {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crearUsuario")
    @Operation(summary = "crear usuario ", description = "Este metodo se usa para crear un usuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody  Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping("/listarUsuarios")
    @Operation(summary = "listar usuario", description = "Este metodo se usa para listar usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/buscarUSuario/{usuario}")
    @Operation(summary = "Buscar usuario por correo", description = "Metodo que permite obtener un usuario por el nombre de su correo (usuario)")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsuario(@PathVariable String usuario) {
        return  ResponseEntity.ok(usuarioService.buscarUsuarioPorUsuario(usuario));
    }

    @PutMapping("/actualizarUsuario")
    @Operation(summary = "actualizar usuario ", description = "Metodo usado para actualizar un usuario")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody  Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario));
    }

    @DeleteMapping("/eliminarUsuario/{idUsuario}")
    @Operation(summary = "eliminar usuario" , description = "Metodo usado para eliminar un usuario")
    public void  eliminarUsuario(@PathVariable Integer idUsuario) { usuarioService.eliminarUsuario(idUsuario); }
}
