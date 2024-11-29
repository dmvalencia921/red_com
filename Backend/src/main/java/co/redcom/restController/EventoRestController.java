package co.redcom.restController;

import co.redcom.entity.Emprendimiento;
import co.redcom.entity.Evento;
import co.redcom.service.impl.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/crearEvento")
    @Operation(summary = "Crear evento", description = "Metodo que permite crear evento")
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento){
        return ResponseEntity.ok(eventoService.crearEvento(evento));
    }

    @GetMapping("/listarEventos")
    @Operation(summary = "Listar eventos", description = "Metodo que permite listar los eventos")
    public ResponseEntity<List<Evento>> listarEvento(){
        return  ResponseEntity.ok(eventoService.listarEvento());
    }

    @PutMapping("/actualizarEvento")
    @Operation(summary = "Actualiza evento", description = "Metodo que permite actualizar un evento")
    public ResponseEntity<Evento> actualizarEvento (@RequestBody Evento evento){
        return  ResponseEntity.ok(eventoService.actualizarEvento(evento));
    }

    @DeleteMapping("/eliminarEvento/{idEvento}")
    @Operation(summary = "Metodo para eliminar ", description = "Metodo que permite eliminar un evento")
    public void  eliminarEvento (@PathVariable Integer idEvento ){
        eventoService.eliminarEvento(idEvento);
    }
}
