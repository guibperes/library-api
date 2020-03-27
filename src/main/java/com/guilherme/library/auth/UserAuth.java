package com.guilherme.library.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.guilherme.library.base.BaseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(value = {"roles"}, allowGetters = true)
public abstract class UserAuth extends BaseEntity implements UserDetails {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @Column(unique = true)
  @Size(max = 44)
  @Setter
  private String token;

  @JsonProperty(access = Access.WRITE_ONLY)
  @Size(min = 6, max = 60)
  @Setter
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  @Setter
  private Set<String> roles = new HashSet<>();

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

    this.roles
      .stream()
      .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

    return authorities;
  }

  @Override
  @JsonIgnore
  public String getUsername() {
    return null;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return false;
  }
}
