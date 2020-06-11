package br.edu.ifms.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.service.CadastroCategoriaService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;
	
	private Categoria categoria;
	
	public void salvar() {
		try {
			
			this.cadastroCategoriaService.salvar(categoria);
			FacesUtil.addSuccessMessage("categoria cadastrada com sucesso!");
			this.limpar();
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.categoria = new Categoria();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
