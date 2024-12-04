package co.redcom.security.entity;

import co.redcom.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Integer id;
    private String token;
    private String userName;
    private Rol rol;
    Boolean isAdmin;
}
