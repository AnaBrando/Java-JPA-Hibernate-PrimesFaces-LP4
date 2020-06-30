package br.edu.ifms.enums;

public enum Setor {
	ADMINISTRA��O("Administra��o"),
	GERENCIA("Ger�ncia"),
	OPERA��O("Operacional");
	
	private String descricao;
	
	Setor(String descricao){
		this.descricao= descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	
}
