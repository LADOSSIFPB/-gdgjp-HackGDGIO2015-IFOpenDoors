package br.edu.ifpb.ifopendoors.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="tb_sala")
@NamedQuery(name = "Room.getAll", query = "from Room")
public class Room implements Serializable {

	private static final long serialVersionUID = 2556998583222059337L;

	@Id
    @GeneratedValue
    @Column(name="id_sala")
	private int id;
	
	@Column(name="nm_sala")
	private String nome;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_tipo_sala", referencedColumnName="id_tipo_sala")
	private TypeRoom tipoSala;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_porta", referencedColumnName="id_porta")
	private Door door;
	
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
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@XmlElement
	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	@XmlElement
	public TypeRoom getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TypeRoom tipoSala) {
		this.tipoSala = tipoSala;
	}
}
