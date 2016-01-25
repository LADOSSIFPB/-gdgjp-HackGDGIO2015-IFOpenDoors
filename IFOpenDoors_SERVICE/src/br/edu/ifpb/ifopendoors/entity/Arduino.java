package br.edu.ifpb.ifopendoors.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "arduino")
public class Arduino {

	private String ip;
	
	private Room room;

	public Arduino() {}

	@XmlElement
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@XmlElement
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room laboratory) {
		this.room = laboratory;
	}
	
	@Override
	public String toString() {
		return "Arduino [ip=" + ip
				+ ", laboratorio=" + room.getNome() 
				+ "]";
	}
}
