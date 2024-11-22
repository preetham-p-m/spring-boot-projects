package com.pmp.restful_web_service.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "user_details_2")
public class AppUser extends org.springframework.security.core.userdetails.User {

    public AppUser(Long id, String name, LocalDate dateOfBirth,
            String username, String password,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public AppUser(Long id, String name, LocalDate dateOfBirth,
            String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, message = "Name must be minimum of 3 characters")
    @Size(max = 50, message = "Name must be maximum of 5 character")
    private String name;

    @Past(message = "Date of Birth must be a past date")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
