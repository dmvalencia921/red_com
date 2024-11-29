package co.redcom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que nos permite saber el emprendimiento
 * @Data notacion de loombok que nos permite obtener el construtor, get y set
 * @Entity notacion que nos permite identificar la entidad para convertila en una tabla
 * @table esquema de DB en el cual se crea la entidad (tabla)
 */

@Data
@Entity
@Table(schema = "redCom")
public class Evento {
    /**
     * identificador de la clase autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    /**
     * Titulo del evento
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "El titulo no puede ser nulo")
    private String titulo;

    /**
     *Descripcion del evento
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "La descripcion no puede ser nula")
    private String descripcion;

    /**
     * Fecha de publicación del evento
     */
    @Column(nullable = false, length = 30)
    private Date fechaEvento;

    /**
     * Fecha de publicación del evento
     */
    @Column(nullable = false, length = 30)
    private Date fechaPublicacion;

    /**
     * Relación con tabla categoria
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    /**
     * Relacion con tabla usuario
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /**
     * Relación con tabla reaccion
     */
    @OneToMany(mappedBy = "evento", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reaccion> listaReaccion = new HashSet<Reaccion>();

    //-----------------> Auditoria <--------------------
    /**
     * Id del usuario que creo el registro.
     */
    @Column(name = "id_usu_creacion", nullable = false, length = 15)
    private String idUsuarioCreacion;

    /**
     * Id del usuario que modifico el registro.
     */
    @Column(name = "id_usu_actualizacion", nullable = true, length = 15)
    private String idUsuarioModificacion;

    /**
     * Fecha de creación del registro
     */
    @Column(name = "fecha_creacion", nullable = false, length = 30)
    private java.util.Date fechaCreacion = new java.util.Date();

    /**
     * Fecha de modificación del registro
     */
    @Column(name = "fecha_actualizacion", nullable = true, length = 30)
    private java.util.Date fechaModificacion;
}
