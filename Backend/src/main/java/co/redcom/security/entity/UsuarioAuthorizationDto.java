package co.redcom.security.entity;

import co.redcom.entity.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioAuthorizationDto {
    private Integer id;
    private String token;
    private String userName;
    private Rol rol;
    Boolean isAdmin;
}
