package co.redcom.restController;

import co.redcom.security.entity.AuthRequest;
import co.redcom.security.entity.AuthResponse;
import co.redcom.security.entity.UsuarioAuthorizationDto;
import co.redcom.security.service.IAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Autenticacion")
@RestController
@RequestMapping("/api/v1/security")
@RequiredArgsConstructor
public class SecurityRestContoller {
    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    private ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

    @GetMapping("/info")
    private ResponseEntity<UsuarioAuthorizationDto> info(Principal principal) {
        return ResponseEntity.ok(authenticationService.infoUsuario(principal.getName()));
    }
}
