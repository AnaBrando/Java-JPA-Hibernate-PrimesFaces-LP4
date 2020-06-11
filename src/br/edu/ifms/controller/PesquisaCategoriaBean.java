package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.CategoriaDAO;
import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	CategoriaDAO categoriaDAO;
	
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	private Categoria categoriaSelecionado;
	
	public List<Categoria> getCategorias(){
		return categorias;
	}
	
	public void excluir() {
		try {
			categoriaDAO.excluir(categoriaSelecionado);
			this.categorias.remove(categoriaSelecionado);
			FacesUtil.addSuccessMessage("Registro excluído com sucesso!" );
		}catch(NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	
	
	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		categorias = categoriaDAO.buscarTodos();
	}

}
