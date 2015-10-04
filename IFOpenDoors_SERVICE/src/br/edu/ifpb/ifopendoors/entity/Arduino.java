package br.edu.ifpb.ifopendoors.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "arduino")
public class Arduino {

	
	private String ip;
	private int porta;
	private int lab;

	public Arduino() {
	}

	public Arduino(String ip, int porta, int lab) {
		this.ip = ip;
		this.porta = porta;
		this.lab = lab;
	}

	@XmlElement
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@XmlElement
	public int getLab() {
		return lab;
	}

	public void setLab(int lab) {
		this.lab = lab;
	}
	
	@Override
	public String toString() {
		return "Arduino [ip=" + ip + ", lab=" + lab + "]";
	}

}
