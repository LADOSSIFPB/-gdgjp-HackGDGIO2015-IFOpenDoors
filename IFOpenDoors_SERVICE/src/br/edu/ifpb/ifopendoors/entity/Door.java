package br.edu.ifpb.ifopendoors.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="tb_porta")
@NamedQuery(name = "Door.getAll", query = "from Door")
public class Door implements Serializable {

	private static final long serialVersionUID = 3625461101278344561L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_porta")
	private int id;
	
	@Column(name="nm_chave")
	private String key;
	
	@Column(name="nm_ip")
	private String ip;

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@XmlElement
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}