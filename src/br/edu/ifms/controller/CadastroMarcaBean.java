package br.edu.ifms.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.modelo.Marca;
import br.edu.ifms.service.CadastroMarcaService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Marca marca;
	
	@Inject
	private CadastroMarcaService cadastroMarcaService;
	
	public CadastroMarcaBean() {
		this.limpar();
	}
	public void salvar() {
		try {
			this.cadastroMarcaService.salvar(marca);
			FacesUtil.addSuccessMessage("marca salvo com sucesso!");
		}catch(NegocioException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public void limpar() {
		this.marca= new Marca();
	}
	
	
}
