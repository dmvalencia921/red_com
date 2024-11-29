package co.redcom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(schema = "redCom")
public class
Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false)
    @NotEmpty(message = "El numero de identificacion no puede ser nulo")
    private String numIdentificacion;

    @Column(nullable = false)
    @NotEmpty(message = "El nommbre no puede ser nulo")
    private String nombres;

    @Column(nullable = false)
    @NotEmpty(message = "El apellido no puede ser nulo")
    private String apellidos;

    @Column(nullable = false, length = 10)
    @NotEmpty(message = "El telefono no puede ser nulo")
    private String telefono;

    @Column(nullable = false)
    @NotEmpty(message = "El correo  no puede ser nulo")
    private String usuario;

    @Column(nullable = false)
    @NotEmpty(message = "lA contraseña  no puede ser nulo")
    private String password;

    @Column(nullable = true)
    private String tokenRecuperacion;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private  Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Emprendimiento> listaEmprendimiento = new HashSet<Emprendimiento>();

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
