package co.redcom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Data
@Entity
@Table(schema = "redCom")
public class Rol {

    /**
     * identificador de la clase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(nullable = false)
    @NotEmpty(message = "el nombre del rol no pude ser nulo")
    private String nombreRol;

    /**
     * Definimos por default el valor
     */
    @Column(columnDefinition = "boolean default true")
    private Boolean activo= true;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private  Set <Usuario> listaUsuarios = new HashSet<Usuario>();

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
