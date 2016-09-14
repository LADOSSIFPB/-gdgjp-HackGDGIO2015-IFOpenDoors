package br.edu.ifpb.ifopendoors.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity 
@Table(name="tb_chave")
@NamedQuery(name = "Key.getAll", query = "from Key")
public class Key {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_chave")
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Open open;
	
	@Column(name="nm_chave")
	private String key;
	
	@Column(name="bl_valido", columnDefinition = "boolean default true")
	private boolean isValid;
	
	@XmlElement
	public Open getOpen() {
		return open;
	}

	public void setOpen(Open open) {
		this.open = open;
	}
	
	@XmlElement
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlElement
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
