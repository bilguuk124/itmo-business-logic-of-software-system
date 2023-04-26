package com.roclh.blps.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@JacksonXmlRootElement(localName = "account")
public class Account implements UserDetails, Serializable {
    @JacksonXmlProperty(localName = "id")
    private Long id;
    @JacksonXmlProperty
    private String firstName;

    @JacksonXmlProperty
    private String lastName;

    @JacksonXmlProperty
    private String username;
    @JacksonXmlProperty
    private String password;

//    private List<Comment> comments;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Account() {
    }

//    public Account(Long id, String lastName, String firstName, String username, String password, List<Comment> comments, Role role) {
//        this.id = id;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.username = username;
//        this.password = password;
//        this.comments = comments;
//        this.role = role;
//    }


    public Account(Long id, String firstName, String lastName, String username, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
