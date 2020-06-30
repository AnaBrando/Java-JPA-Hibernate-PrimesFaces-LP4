package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.GerenteDAO;
import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaGerenteBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	GerenteDAO gerenteDAO;
	
	private List<Gerente> gerentes = new ArrayList<Gerente>();
	
	private Gerente gerenteSelecionado;
	
	public List<Gerente> getGerentes(){
		return gerentes;
	}
	
	public Gerente getGerenteSelecionado() {
		return gerenteSelecionado;
	}

	public void setGerenteSelecionado(Gerente gerenteSelecionado) {
		this.gerenteSelecionado = gerenteSelecionado;
	}

	public void excluir() {
		try {
			gerenteDAO.excluir(gerenteSelecionado);
			this.gerentes.remove(gerenteSelecionado);
			FacesUtil.addSuccessMessage("Registro excluído com sucesso!" );
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void inicializar() {
		gerentes = gerenteDAO.buscarTodos();
	}
}
