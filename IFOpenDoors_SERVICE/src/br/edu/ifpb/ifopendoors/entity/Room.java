package br.edu.ifpb.ifopendoors.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="tb_sala")
public class Room {

	@Id
    @GeneratedValue
    @Column(name="id_sala")
	private int id;
	
	@Column(name="nm_sala")
	private String nome;
	
	@Column(name="tp_sala")
	private String tipo;
	
	private String mensage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
