package co.redcom.restController;

import co.redcom.entity.Reaccion;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import co.redcom.service.impl.ReaccionService;
@RestController
@RequestMapping("api/reaccion")
public class ReaccionRestController {
    @Autowired
    private ReaccionService reaccionService;

    @PostMapping("/crearReaccion")
    @Operation(summary = "Crear Reaccion", description = "Metodo que permite crear una reaccion")
    public ResponseEntity<Reaccion> crearReaccion(@RequestBody Reaccion reaccion){
        return ResponseEntity.ok(reaccionService.crearReaccion(reaccion));
    }

    @GetMapping("/listarReacciones")
    @Operation(summary = "Listar Reacciones", description = "Metodo que permite listar las reacciones")
    public ResponseEntity<List<Reaccion>> listarReaccion(){
        return ResponseEntity.ok(reaccionService.listarReaccion());
    }

    @PutMapping("/actualizarReaccion")
    @Operation(summary = "Actualizar Reaccion", description = "Metodo que permite actualizar una reaccion")
    public ResponseEntity<Reaccion> actualizarReaccion(@RequestBody Reaccion reaccion){
        return ResponseEntity.ok(reaccionService.actualizarReaccion(reaccion));
    }

    @DeleteMapping("/eliminarReaccion/{idReaccion}")
    @Operation(summary = "Metodo para eliminar", description = "Metodo que permite eliminar una reaccion")
    public void eliminarReaccion(@PathVariable Integer idReaccion){
        reaccionService.eliminarReaccion(idReaccion);
    }
}
