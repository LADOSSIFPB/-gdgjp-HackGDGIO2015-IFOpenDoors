package br.edu.ifpb.ifopendoors.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Erro {
	
	private int codigo;
	
	private String mensagem;

	public Erro() {}
	
	public Erro(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	@XmlElement
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@XmlElement
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Erro [codigo:" + codigo + "; mensagem:" + mensagem + "]";
	}
}
