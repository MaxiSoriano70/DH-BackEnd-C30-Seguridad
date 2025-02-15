package dh.backend.maxisoriano.ClinicaMVC.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    /*LISTA DE PERMISOS*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    /*CUANTA EXPIRADA*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /*CEUNTA BLOQUEADA*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /*CREDENCIALES EXPIRADAS*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /*SI ESAT ACTIVO*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
