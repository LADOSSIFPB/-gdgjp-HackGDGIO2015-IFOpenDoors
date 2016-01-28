package br.edu.ifpb.ifopendoors.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tb_sala")
@XmlRootElement(name = "sala")
@NamedQuery(name = "Room.getAll", query = "from Room")
public class Room implements Serializable {

	private static final long serialVersionUID = 2556998583222059337L;

	@Id
    @GeneratedValue
    @Column(name="id_sala")
	private int id;
	
	@Column(name="nm_sala")
	private String nome;
	
	@Column(name="tp_sala")
	private String tipo;
	
	@Column(name="nm_descricao")
	private String descricao;

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@XmlElement
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
