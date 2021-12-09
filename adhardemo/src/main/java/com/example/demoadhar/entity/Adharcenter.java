package com.example.demoadhar.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.persistence.OrderBy;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="adharcenter")
public class Adharcenter {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adharcenter_sequence")
	@SequenceGenerator(name = "adharcenter_sequence", sequenceName = "adharcenter_sequence", allocationSize = 1)
	@Column(name = "center_id")
	private Integer id;
	
	@Column(name="center_name")
	private Integer code;
	
	
	@Column(name="city")
	private String city;
	
	@Pattern(regexp ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",message="mail id is not valid")
	@NotEmpty
	@Column(name="email",unique = true)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn
	private User userId; 

}
