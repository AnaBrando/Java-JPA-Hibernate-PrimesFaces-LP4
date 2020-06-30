package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;


import br.edu.ifms.dao.GerenteDAO;

import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroGerenteService implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private GerenteDAO gerenteDAO;
	
	@Transactional
	public void salvar(Gerente gerente) throws NegocioException{
		if(gerente.getNome() == null || gerente.getNome().trim().equals("")) {
			throw new NegocioException("O nome da categoria é obrigatório!");
		}
		this.gerenteDAO.salvar(gerente);
	}
}
