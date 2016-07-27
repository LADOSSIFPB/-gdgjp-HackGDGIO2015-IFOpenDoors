package br.edu.ifpb.ifopendoors.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
@Entity
@Table(name = "tb_role")
@NamedQuery(name = "Role.getAll", query = "from Role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private Integer id; 

	@Column(name = "nm_role")
	private String name;

	@XmlElement
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id +", name=" + name + "]";
	}
	
	@Override
	public boolean equals(Object value) {
		
		if (value instanceof String) {
			return this.name.equals(value);
		}
		
		if (value instanceof Role) {
			
			Role role = (Role) value;
			
			if (this.id == role.getId() 
					&& this.name.equals(role.getName())) {
				return true;
			}
		}
		
		return false;
	}
}