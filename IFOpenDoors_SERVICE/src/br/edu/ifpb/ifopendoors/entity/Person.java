package br.edu.ifpb.ifopendoors.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="tb_pessoa")
public class Person {

	@Id
    @GeneratedValue
    @Column(name="id_pessoa")
	private int id;
	
	@Column(name="nm_pessoa")
	private String name;
	
	@Column(name="nr_cpf")
	private String cpf;
	
	@Column(name="nr_senha")
	private String password;
	
	 @Column(name="nr_key")
	private String key;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
