package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.MarcaDAO;
import br.edu.ifms.dao.ProdutoDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.modelo.Marca;
import br.edu.ifms.modelo.Produto;
import br.edu.ifms.service.CadastroEstoqueService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstoqueBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Estoque estoque;
	
	private List<Produto> produtos;
	
	private List<Marca> marcas ;
	
	
	@Inject 
	private CadastroEstoqueService cadastroEstoqueService;
		
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Inject
	private MarcaDAO marcaDAO;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.marcas =  marcaDAO.buscarTodos();
		this.produtos = this.produtoDAO.buscarTodos();
	}
	
	public void salvar() {
		estoque.configuraDatasCriacaoAlteracao();
		try {
			this.cadastroEstoqueService.salvar(estoque);
			FacesUtil.addSuccessMessage("estoque cadastrada com sucesso!");
			this.limpar();
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void limpar() {
		this.estoque = new Estoque();
		this.estoque.setMarcas(new ArrayList<Marca>());
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public List<Marca> getMarcas() {
		return marcas;
	}

	
	
}
