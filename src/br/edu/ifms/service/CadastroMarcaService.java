package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.MarcaDAO;
import br.edu.ifms.modelo.Marca;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroMarcaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MarcaDAO marcaDAO;
	
	@Transactional
	public void salvar(Marca marca) throws NegocioException{
		if(marca.getNome() == null || marca.getNome().trim().equals("")) {
			throw new NegocioException("O nome da marca é obrigatório!");
		}
		this.marcaDAO.salvar(marca);
	}

}