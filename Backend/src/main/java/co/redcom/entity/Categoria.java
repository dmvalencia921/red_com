package co.redcom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que nos permite saber la categoria
 * @Data notacion de loombok que nos permite obtener el construtor, get y set
 * @Entity notacion que nos permite identificar la entidad para convertila en una tabla
 * @table esquema de DB en el cual se crea la entidad (tabla)
 */
@Data
@Entity
@Table(schema = "redCom")
public class Categoria {

    /**
     * Identificador de la clase autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    /**
     * Nombre de la categoria
     */
    @Column(nullable = false)
    @NotEmpty(message = "El nombre de la categoria no puede ser nulo")
    private String nombreCategoria;

    /**
     * relacion con la entidad Emprendimiento
     */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Emprendimiento> listaEmprendimientos = new HashSet<Emprendimiento>();

    /**
     * relacion con la entidad Evento
     */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Evento> listaEventos = new HashSet<Evento>();

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
    private Date fechaCreacion = new Date();

    /**
     * Fecha de modificación del registro
     */
    @Column(name = "fecha_actualizacion", nullable = true, length = 30)
    private Date fechaModificacion;
}
