package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.CategoriaDAO;
import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Transactional
	public void salvar(Categoria categoria) throws NegocioException{
		if(categoria.getNome() == null || categoria.getNome().trim().equals("")) {
			throw new NegocioException("O nome da categoria é obrigatório!");
		}
		this.categoriaDAO.salvar(categoria);
	}

}
