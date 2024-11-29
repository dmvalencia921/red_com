package co.redcom.restController;

import co.redcom.entity.Rol;
import co.redcom.service.impl.RolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolRestcontroller {

    @Autowired
    private RolService rolService;

  @PostMapping ("/crearRol")
  @Operation(summary = "Este metodo me permite crear un rol ", description = "Metodo para crear un rol")
  public ResponseEntity<Rol> crearRol(@RequestBody  Rol rol) {
      return  ResponseEntity.ok(rolService.crearRol(rol));
  }

  @GetMapping("/listarRol")
  @Operation(summary = "Listar roles", description = "Metodo que se usa para listar los roles")
  public ResponseEntity<List<Rol>> listarRol(){
        return  ResponseEntity.ok(rolService.listarRols());
  }

  @PutMapping("/actualizarRol")
  @Operation(summary = "actualizar rol", description = "Metodo que se usa para actualizar un rol")
  public  ResponseEntity<Rol> actualizarRol(@RequestBody Rol rol){
        return  ResponseEntity.ok(rolService.actualizarRol(rol));
  }

  @DeleteMapping("/eliminarRol/{idRol}")
  public void eliminarRol(@PathVariable  Integer idRol){
        rolService.eliminarRol(idRol);
  }
}
