package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.EstoqueDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.modelolazy.LazyEstoqueDataModel;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstoqueBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EstoqueDAO estoqueDAO;
	
	private List<Estoque> estoques = new ArrayList<Estoque>();
	
	private LazyEstoqueDataModel lazyEstoques;
	
	private Estoque estoqueSelecionado;
	
	
	public void excluir() {
		try {
			estoqueDAO.excluir(estoqueSelecionado);
			this.estoques.remove(estoqueSelecionado);
			FacesUtil.addSuccessMessage("Estoque"+estoqueSelecionado.getCodigo()+"Excluído com sucesso");
			
		}catch(Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Estoque getEstoqueSelecionado() {
		return estoqueSelecionado;
	}

	public void setEstoqueSelecionado(Estoque estoqueSelecionado) {
		this.estoqueSelecionado = estoqueSelecionado;
	}
	
	public List<Estoque> getEstoques() {
		return estoques;
	}
	
	@PostConstruct
	public void inicializar() {
		lazyEstoques = new LazyEstoqueDataModel(estoqueDAO);
	}
	
	public void buscarEstoqueComMarcas() {
		estoqueSelecionado = estoqueDAO.buscarEstoqueComMarcas(estoqueSelecionado.getCodigo());
		
	}
	public LazyEstoqueDataModel getLazyEstoques() {
		return lazyEstoques;
	}
}
