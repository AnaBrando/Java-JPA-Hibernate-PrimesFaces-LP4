package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.CategoriaDAO;
import br.edu.ifms.enums.Tipo;
import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.modelo.Produto;
import br.edu.ifms.service.CadastroProdutoService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	private List<Categoria> categorias;
	
	private List<Tipo> tipos;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	@Inject
	private CategoriaDAO categoriaDAO; 
	
	public void salvar() {
		try {
			this.cadastroProdutoService.salvar(produto);
			FacesUtil.addSuccessMessage("produto salvo com sucesso!");
		}catch(NegocioException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.categorias = categoriaDAO.buscarTodos();
		this.tipos = Arrays.asList(Tipo.values());
	}
	
	public void limpar() {
		this.produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Categoria> getCategorias(){
		return categorias;
	}
	
	public List<Tipo> getTipos(){
		return tipos;
	}
}
