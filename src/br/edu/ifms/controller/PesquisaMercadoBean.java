package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.MercadoDAO;
import br.edu.ifms.dao.ProdutoDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.modelo.Mercado;
import br.edu.ifms.modelo.Produto;

@Named
@ViewScoped
public class PesquisaMercadoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<Produto> produtos;
	
	private Mercado mercado;
	
	private Estoque estoque;
	
	private List<Mercado> mercados;
	
	@Inject 
	private ProdutoDAO produtoDAO;
	
	@Inject
	private MercadoDAO mercadoDAO;
	
	@PostConstruct
	public void inicializar() {
		this.setEstoque(new Estoque());
		this.mercado = new Mercado();
		this.setProdutos(this.produtoDAO.buscarTodos());
		
		this.mercados = new ArrayList<Mercado>();
	}
	
	public void pesquisar() {
		this.mercados = mercadoDAO.buscarPorDataEstoque(this.mercado.getDataEntrada(), this.mercado.getEstoque());
	}
	
	public Mercado getMercado() {
		return mercado;
	}
	
	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Mercado> getMercados() {
		return mercados;
	}
	
}
