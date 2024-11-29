package co.redcom.repository;

import co.redcom.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRespository  extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNombreCategoriaIgnoreCase(String nombreCategoria);
    Categoria findOneByNombreCategoriaAndIdCategoriaNot(String nombreCategoria, Integer idCategoria);

}
