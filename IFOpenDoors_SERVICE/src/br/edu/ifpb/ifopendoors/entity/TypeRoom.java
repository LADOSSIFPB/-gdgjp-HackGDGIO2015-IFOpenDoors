package br.edu.ifpb.ifopendoors.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="tb_tipo_sala")
@NamedQuery(name = "TypeRoom.getAll", query = "from TypeRoom")
public class TypeRoom implements Serializable {

	private static final long serialVersionUID = -7848189710234112047L;

	@Id
    @GeneratedValue
    @Column(name="id_tipo_sala")
	private int id;
	
	@Column(name="nm_tipo_sala")
	private String name;

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
}
