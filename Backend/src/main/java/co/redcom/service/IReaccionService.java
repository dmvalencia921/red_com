package co.redcom.service;

import co.redcom.entity.Reaccion;

import java.util.List;

public interface IReaccionService {
    Reaccion crearReaccion(Reaccion reaccion);
    List<Reaccion> listarReaccion();
    Reaccion actualizarReaccion(Reaccion reaccion);
    void eliminarReaccion(Integer idReaccion);
}
