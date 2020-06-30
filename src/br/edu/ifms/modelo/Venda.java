package br.edu.ifms.modelo;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name="venda")
public class Venda {
	private Long codigo;
	private BigDecimal valorTotalProduto;
	private Estoque estoque;
	private Date dataEntrada;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getValorTotalProduto() {
		return valorTotalProduto;
	}
	public void setValorTotalProduto(BigDecimal valorTotalProduto) {
		this.valorTotalProduto = valorTotalProduto;
	}
	@ManyToOne
	@JoinColumn(name="codigo_estoque")
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
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
		Venda other = (Venda) obj;
		if(codigo == null) {
			if(other.codigo != null)
				return false;
		}else if(!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
