package co.redcom.restController;

import co.redcom.entity.Categoria;
import co.redcom.service.impl.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/crearCategoria")
    @Operation(summary = "Metodo para crear categoria", description = "Este metodo me permite crear una categoria")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody  Categoria categoria) {
        return  ResponseEntity.ok(categoriaService.crearCategoria(categoria));
    }

    @GetMapping("/listarCategorias")
    @Operation(summary = "Metodo para lsitar", description = "Este metodo nos permite listar las categorias")
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return  ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PutMapping("/actualizarCategoria")
    @Operation(summary = "Metodo para actualizar", description = "Este metodo permite actualizar una categoria")
    public ResponseEntity<Categoria> actualizarCategoria(@RequestBody  Categoria categoria) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(categoria));
    }

    @DeleteMapping("/eliminarCategoria/{idCategoria}")
    @Operation(summary = "Metodo para eliminar", description = "Este metodo permite elimina una categoria")
    public void  eliminarCategoria (@PathVariable Integer idCategoria) {
        categoriaService.eliminarCategoria(idCategoria);
    }
}
