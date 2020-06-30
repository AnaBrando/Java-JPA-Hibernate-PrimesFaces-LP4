package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.enums.Setor;
import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.service.CadastroGerenteService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroGerenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Gerente gerente;
	
	@Inject
	private CadastroGerenteService cadastroGerenteService;
	
	private List<Setor> setores;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.setores = Arrays.asList(Setor.values());
		
	}
	
	public void salvar() {
		try {
			this.cadastroGerenteService.salvar(gerente);
			FacesUtil.addSuccessMessage("marca salvo com sucesso!");
		}catch(NegocioException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void limpar () {
		this.gerente = new Gerente();
	}
	
	
	
}
