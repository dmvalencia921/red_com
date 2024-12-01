package co.redcom.service;

import co.redcom.entity.Evento;

import java.util.List;

public interface IEventoService {
    Evento crearEvento(Evento evento);
    List<Evento> listarEvento();
    Evento actualizarEvento(Evento evento);
    void eliminarEvento(Integer idEvento);
}
