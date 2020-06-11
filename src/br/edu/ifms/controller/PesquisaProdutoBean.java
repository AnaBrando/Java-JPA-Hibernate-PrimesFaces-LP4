package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.ProdutoDAO;
import br.edu.ifms.modelo.Produto;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Produto> produtos;
	
	private Produto produtoSelecionado;
	
	@Inject
	ProdutoDAO produtoDAO;
	
	public List<Produto> getProdutos(){
		return produtos;
	}
	
	@PostConstruct
	public void inicializar() {
		this.produtos = this.produtoDAO.buscarTodos();
	}
	
	public void excluir() {
		try {
			produtoDAO.excluir(produtoSelecionado);
			this.produtos.remove(produtoSelecionado);
			FacesUtil.addSuccessMessage("Registro excluído com sucesso!" );
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	
}
