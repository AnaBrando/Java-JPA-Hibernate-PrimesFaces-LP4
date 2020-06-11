package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.MarcaDAO;
import br.edu.ifms.modelo.Marca;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMarcaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	MarcaDAO marcaDAO;
	
	private List<Marca> marcas = new ArrayList<Marca>();
	
	private Marca marcaSelecionado;
	
	public List<Marca> getMarcas(){
		return marcas;
	}
	
	public Marca getMarcaSelecionado() {
		return marcaSelecionado;
	}

	public void setMarcaSelecionado(Marca marcaSelecionado) {
		this.marcaSelecionado = marcaSelecionado;
	}

	public void excluir() {
		try {
			marcaDAO.excluir(marcaSelecionado);
			this.marcas.remove(marcaSelecionado);
			FacesUtil.addSuccessMessage("Registro excluído com sucesso!" );
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void inicializar() {
		marcas = marcaDAO.buscarTodos();
	}

}
