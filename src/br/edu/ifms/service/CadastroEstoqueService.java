package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.EstoqueDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroEstoqueService  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO estoqueDAO;
	
	@Transactional
	public void salvar(Estoque estoque) throws NegocioException{
		if(estoque.getDescricao() == null || estoque.getDescricao().trim().equals("")) {
			throw new NegocioException("O nome do estoque é obrigatório!");
		}
		this.estoqueDAO.salvar(estoque);
	}

}