package co.redcom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(schema = "redCom")
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprendimiento;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "El titulo no puede ser nulo")
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotEmpty(message = "La descripcion no puede ser nula")
    private String descripcion;

    /**
     * Fecha de publicación del emprendimiento
     */
    @Column(nullable = false, length = 30)
    private Date fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
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
