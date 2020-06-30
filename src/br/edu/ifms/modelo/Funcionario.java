package br.edu.ifms.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifms.enums.Setor;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPO_FUNCIONARIO",discriminatorType=DiscriminatorType.INTEGER)
public abstract class Funcionario {
	
	private Long codigo;
	private String nome;
	private Date dataMascimento;
	private String cpf;
	private Setor setor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	public Date getDataMascimento() {
		return dataMascimento;
	}
	public void setDataMascimento(Date dataMascimento) {
		this.dataMascimento = dataMascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if(codigo == null) {
			if(other.codigo != null)
				return false;
		}else if(!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
