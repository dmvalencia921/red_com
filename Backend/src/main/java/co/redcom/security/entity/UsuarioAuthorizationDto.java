package co.redcom.security.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioAuthorizationDto {
    private Integer id;
    private String token;
    private String userName;
    private Boolean isAdmin;
}
