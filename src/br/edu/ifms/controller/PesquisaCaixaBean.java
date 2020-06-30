package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.CaixaDAO;
import br.edu.ifms.modelo.Caixa;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCaixaBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	CaixaDAO caixaDAO;
	
	private List<Caixa> caixas = new ArrayList<Caixa>();
	
	private Caixa caixaSelecionado;
	
	public List<Caixa> getCaixas(){
		return caixas;
	}
	
	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa caixaSelecionado) {
		this.caixaSelecionado = caixaSelecionado;
	}

	public void excluir() {
		try {
			caixaDAO.excluir(caixaSelecionado);
			this.caixas.remove(caixaSelecionado);
			FacesUtil.addSuccessMessage("Registro excluído com sucesso!" );
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void inicializar() {
		caixas = caixaDAO.buscarTodos();
	}
}