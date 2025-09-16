package com.sisko.exam.master.user.model;

import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at=NOW() WHERE id=?")
public class UserEntity extends BaseAuditableSoftDelete implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 191)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.STUDENT;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}