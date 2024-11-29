package co.redcom.service;

import co.redcom.entity.Categoria;

import java.util.List;

public interface ICategoriaService {

    Categoria crearCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    Categoria actualizarCategoria(Categoria categoria);
    void eliminarCategoria(Integer idCategoria);
}
