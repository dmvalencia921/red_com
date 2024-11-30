package co.redcom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad que nos permite saber el usuario
 * @Data notacion de loombok que nos permite obtener el construtor, get y set
 * @Entity notacion que nos permite identificar la entidad para convertila en una tabla
 * @table esquema de DB en el cual se crea la entidad (tabla)
 */
@Data
@Entity
@Table(schema = "redCom")
public class
Usuario {

    /**
     * identificador de la clase autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    /**
     * numero de indentifiacion del usuario
     */
    @Column(nullable = false)
    @NotEmpty(message = "El numero de identificacion no puede ser nulo")
    private String numIdentificacion;

    /**
     * nombres del usuario
     */
    @Column(nullable = false)
    @NotEmpty(message = "El nombre no puede ser nulo")
    private String nombres;

    /**
     * apellidos del usuario
     */
    @Column(nullable = false)
    @NotEmpty(message = "El apellido no puede ser nulo")
    private String apellidos;

    /**
     * telefono del usuario
     */
    @Column(nullable = false, length = 10)
    @NotEmpty(message = "El telefono no puede ser nulo")
    private String telefono;

    /**
     * Correo electronico el cual es le usuario de acceso
     */
    @Column(nullable = false)
    @NotEmpty(message = "El correo  no puede ser nulo")
    private String usuario;

    /**
     * contraseña del portal
     */
    @Column(nullable = false)
    @NotEmpty(message = "lA contraseña  no puede ser nulo")
    private String password;

    /**
     * token de recuperacion de contraseña
     */
    @Column(nullable = true)
    private String tokenRecuperacion;

    /**
     * realacion contabla rol
     */
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private  Rol rol;

    /**
     * Relacion con tabla usuario
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Emprendimiento> listaEmprendimiento = new HashSet<Emprendimiento>();

    /**
     * Relacion con tabla Reacción
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private  Set <Reaccion> listaReaccion = new HashSet<Reaccion>();

    /**
     * Relacion con tabla Reaccion
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comentario> listaComentario = new HashSet<Comentario>();

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
