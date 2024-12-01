package co.redcom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Entidad que nos permite saber el usuario
 * @Data notacion de loombok que nos permite obtener el construtor, get y set
 * @Entity notacion que nos permite identificar la entidad para convertila en una tabla
 * @table esquema de DB en el cual se crea la entidad (tabla)
 */

@Data
@Entity
@Table(schema = "redCom")
public class Reaccion {

    /**
     * identificador de la clase autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReaccion;

    /**
     * tipo de reacción
     */

    @Column(nullable = false)
    @NotEmpty(message = "El tipo de reacción no debe ser nula")
    private String tipoReaccion;

    /**
     * relación con la tabla usuario
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /**
     * relación con la tabla emprendimiento
     */
    @ManyToOne
    @JoinColumn(name = "id_emprendimiento")
    private Emprendimiento emprendimiento;

    /**
     * relación con la tabla evento
     */
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

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
