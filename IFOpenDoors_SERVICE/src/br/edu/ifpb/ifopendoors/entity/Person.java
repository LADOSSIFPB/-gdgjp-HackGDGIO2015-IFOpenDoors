package br.edu.ifpb.ifopendoors.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity 
@Table(name="tb_pessoa")
@NamedQuery(name = "Person.getAll", query = "from Person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pessoa")
	private int id;
	
	@Column(name="nm_pessoa")
	private String name;
	
	@Column(name="nr_cpf")
	private String cpf;
	
	@Column(name="nr_email")
	private String email;
	
	@Column(name="nr_senha")
	private String password;
	
	@Column(name="nr_key")
	private String key;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_role", referencedColumnName="id_role", nullable=false)
	private Role role;

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@XmlElement
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
