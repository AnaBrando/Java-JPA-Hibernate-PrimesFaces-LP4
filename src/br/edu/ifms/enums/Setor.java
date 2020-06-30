package br.edu.ifms.enums;

public enum Setor {
	ADMINISTRAÇÃO("Administração"),
	GERENCIA("Gerência"),
	OPERAÇÃO("Operacional");
	
	private String descricao;
	
	Setor(String descricao){
		this.descricao= descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	
}
