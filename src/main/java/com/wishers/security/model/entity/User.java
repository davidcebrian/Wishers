package com.wishers.security.model.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wishers.model.entity.BaseEntity;
import com.wishers.model.entity.Customer;
import com.wishers.security.model.enumerate.UserRole;


@Entity
public class User extends BaseEntity implements UserDetails{

	private static final long serialVersionUID = 2046866248113544418L;
	private static final int MAX_AUTH_ATTEMPTS = 3;
	
	private String name;
	private String surname;
	private Integer edad;
	
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
	private Customer customer;
	
	@CreatedDate
	private LocalDateTime createTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
	private LocalDateTime deleteTime;
	
	private LocalDateTime lastPasswordChange;
	
	private Boolean locked;
	
	private Boolean enabled;
	
	private Integer authenticationAttempts;
	
	private LocalDateTime passwordPolicyExpDate;

	
	
	
	public User() {
		super();
		this.customer = new Customer(this);
		this.roles = new HashSet<>();
		this.roles.add(UserRole.USER);
		this.createTime = LocalDateTime.now();
		this.updateTime = LocalDateTime.now();
		this.deleteTime = null;
		this.lastPasswordChange = LocalDateTime.now();
		this.locked = false;
		this.enabled = true;
		this.authenticationAttempts = MAX_AUTH_ATTEMPTS;
		this.passwordPolicyExpDate = LocalDateTime.now().plusYears(3);
	}
	
	
	public User(String name, String surname, Integer edad, String username, String email, String password) {
		super();
		this.customer = new Customer(this);
		this.name = name;
		this.surname = surname;
		this.edad = edad;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = new HashSet<>();
		this.roles.add(UserRole.USER);
		this.createTime = LocalDateTime.now();
		this.updateTime = LocalDateTime.now();
		this.deleteTime = null;
		this.lastPasswordChange = LocalDateTime.now();
		this.locked = false;
		this.enabled = true;
		this.authenticationAttempts = MAX_AUTH_ATTEMPTS;
		this.passwordPolicyExpDate = LocalDateTime.now().plusYears(3);
	}
	
	
	public void addRole(UserRole role) {
		this.roles.add(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_"+ur.name())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getDeleteTime()==null;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getAuthenticationAttempts() < MAX_AUTH_ATTEMPTS;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getLastPasswordChange().isBefore(this.passwordPolicyExpDate);
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}

	public LocalDateTime getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(LocalDateTime lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getAuthenticationAttempts() {
		return authenticationAttempts;
	}

	public void setAuthenticationAttempts(Integer authenticationAttempts) {
		this.authenticationAttempts = authenticationAttempts;
	}

	public LocalDateTime getPasswordPolicyExpDate() {
		return passwordPolicyExpDate;
	}

	public void setPasswordPolicyExpDate(LocalDateTime passwordPolicyExpDate) {
		this.passwordPolicyExpDate = passwordPolicyExpDate;
	}

	public static int getMaxAuthAttempts() {
		return MAX_AUTH_ATTEMPTS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
