package br.edu.ifms.controller;

import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.enums.Setor;
import br.edu.ifms.modelo.Caixa;
import br.edu.ifms.service.CadastroCaixaService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Caixa caixa;
	
	@Inject
	private CadastroCaixaService cadastroCaixaService;
	
	private List<Setor> setores;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.setores = Arrays.asList(Setor.values());
		
	}
	
	public void salvar() {
		try {
			this.cadastroCaixaService.salvar(caixa);
			FacesUtil.addSuccessMessage("Caixa salvo com sucesso!");
		}catch(NegocioException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
		this.limpar();
	}
	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void limpar () {
		this.caixa = new Caixa();
	}
	
	
	
}
