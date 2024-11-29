package co.redcom.restController;

import co.redcom.entity.Emprendimiento;
import co.redcom.service.impl.EmprendimientoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/emprendimiento")
public class EmprendimientoRestController {

    @Autowired
    private EmprendimientoService emprendimientoService;

    @PostMapping("/crearEmprendimiento")
    @Operation(summary = "Crear emprendimiento", description = "Metodo que permite crear un emprendimietno")
    public ResponseEntity<Emprendimiento> crearEmprendimiento(@RequestBody  Emprendimiento emprendimiento){
        return  ResponseEntity.ok(emprendimientoService.crearEmprendimiento(emprendimiento));
    }

    @GetMapping("/listarEmprendimientos")
    @Operation(summary = "Listar emprendimientos", description = "Metodo que permite listar los emprendimientos")
    public ResponseEntity<List<Emprendimiento>> listarEmprendimiento(){
        return  ResponseEntity.ok(emprendimientoService.listarEmprendimiento());
    }


    @PutMapping("/actualizarEmprendimiento")
    @Operation(summary = "Actualiza emprendimiento", description = "Metodo que permite actualizar un emprendimiento")
    public ResponseEntity<Emprendimiento> actualizarEmprendimiento (@RequestBody Emprendimiento emprendimiento){
        return  ResponseEntity.ok(emprendimientoService.actualizarEmprendimiento(emprendimiento));
    }

    @DeleteMapping("/eliminarEmprendimiento/{idEmprendimiento}")
    @Operation(summary = "Metodo para eliminar ", description = "Metodo que permite eliminar un emprendimiento")
    public void  eliminarEmprendimiento (@PathVariable Integer idEmprendimiento ){
        emprendimientoService.eliminarEmprendimiento(idEmprendimiento);
    }

}

