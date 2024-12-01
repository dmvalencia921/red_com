package co.redcom.restController;

import co.redcom.entity.Comentario;
import co.redcom.entity.Emprendimiento;
import co.redcom.service.impl.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comentario")
public class ComentarioRestController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/crearComentario")
    @Operation(summary = "Crear comentario", description = "Metodo que permite crear un comentario")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario){
        return  ResponseEntity.ok(comentarioService.crearComentario(comentario));
    }

    @GetMapping("/listarComentarios")
    @Operation(summary = "Listar comentarios", description = "Metodo que permite listar los comentarios")
    public ResponseEntity<List<Comentario>> listarComentario(){
        return  ResponseEntity.ok(comentarioService.listarComentario());
    }


    @PutMapping("/actualizarComentario")
    @Operation(summary = "Actualiza comentario", description = "Metodo que permite actualizar un comentario")
    public ResponseEntity<Comentario> actualizarComentario (@RequestBody Comentario comentario){
        return  ResponseEntity.ok(comentarioService.actualizarComentario(comentario));
    }

    @DeleteMapping("/eliminarComentario/{idComentario}")
    @Operation(summary = "Metodo para eliminar ", description = "Metodo que permite eliminar un comentario")
    public void  eliminarComentario (@PathVariable Integer idComentario){
        comentarioService.eliminarComentario(idComentario);
    }
}
