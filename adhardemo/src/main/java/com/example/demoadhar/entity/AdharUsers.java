package com.example.demoadhar.entity;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import com.example.demoadhar.entity.User;
import com.example.demoadhar.entity.Adharcenter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="adharusers")

public class AdharUsers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="auid")
	private Integer auid;
	
	@Column(name="Father_name")
	private String fathername;
	
	@Column(name="Age")
	private Integer age;
	
	@Column(name="mobile")
	private Long mno;
	
	@Column(name="Address")
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn
	private User userId;

	
}
