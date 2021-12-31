package com.example.demoadhar.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_name" }) })
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class User implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903243335716548729L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="user_name",nullable = false)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name",nullable = false)
	private String firstName;
	
	@Column(name="last_name",nullable = false)
private String lastName;
	
	@Column(name="email",unique = true,nullable = false)
	@Pattern(regexp ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",message="mail id is not valid")
	@NotEmpty
	private String email;
	
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_authority", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	@OrderBy
	@JsonIgnore
	private Collection<Authority> authorities;


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