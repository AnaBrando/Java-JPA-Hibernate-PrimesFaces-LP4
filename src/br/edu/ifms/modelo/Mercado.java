package br.edu.ifms.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="mercado")
public class Mercado {
	
	private Long codigo;
	private BigDecimal valorVenda;
	private String descricao;
	private Date dataSaida;
	private Venda venda;
	private Gerente gerente;
	private Estoque estoque;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getValorProdutos() {
		return valorVenda;
	}
	public void setValorProdutos(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataEntrada() {
		return dataSaida;
	}
	public void setDataEntrada(Date dataSaida) {
		this.dataSaida = dataSaida;
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
		Mercado other = (Mercado) obj;
		if(codigo == null) {
			if(other.codigo != null)
				return false;
		}else if(!codigo.equals(other.codigo))
			return false;
		return true;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="codigo_venda_mercado")
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_gerente")
	public Gerente getGerente() {
		return gerente;
	}
	
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_estoque")
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
}
