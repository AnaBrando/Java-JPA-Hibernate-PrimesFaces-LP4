package br.edu.ifms.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries({
	@NamedQuery(name="Estoque.buscarTodos",query="select e from Estoque e"),
	@NamedQuery(name="Estoque.buscarEstoqueComMarcas",query="select e "+"from Estoque e JOIN e.marcas m "
													  +"where e.codigo = :codigo")
})
@Entity
public class Estoque {
	
	private Long codigo;
	private int quantidade;
	private Date dataCriacao;
	private Date dataModificacao;
	private List<Marca> marcas;
	private Produto produto;
	private double valorEntrada;
	private String descricao;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	@ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(name="estoque_marca",
	joinColumns=@JoinColumn(name="codigo_estoque"),
	inverseJoinColumns=@JoinColumn(name="codigo_marca"))
	public List<Marca> getMarcas() {
		return marcas;
	}
	
	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_produto")
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@PrePersist
	@PreUpdate
	public void configuraDatasCriacaoAlteracao() {
		this.dataModificacao= new Date();
		if(this.dataCriacao == null) {
			this.dataCriacao = new Date();
		}
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
		Estoque other = (Estoque) obj;
		if(codigo == null) {
			if(other.codigo != null)
				return false;
		}else if(!codigo.equals(other.codigo))
			return false;
		return true;
	}
	public double getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
