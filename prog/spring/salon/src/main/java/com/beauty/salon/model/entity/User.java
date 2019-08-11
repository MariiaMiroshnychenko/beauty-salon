package com.beauty.salon.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String role;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterId")
    private List<Schedule> schedules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterId")
    private List<Record> masterRecords;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterId")
    private List<Feedback> masterFeedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<Feedback> clientFeedbacks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId")
    private List<Record> clientRecords;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this::getRole);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
