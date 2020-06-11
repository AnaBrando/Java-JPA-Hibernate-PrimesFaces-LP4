package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.ProdutoDAO;
import br.edu.ifms.modelo.Produto;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProdutoDAO produtoDAO;
	
	@Transactional
	public void salvar(Produto produto) throws NegocioException {
		if(produto.getNome() == null || 
				produto.getNome().trim().equals("")) {
					throw new NegocioException("O nome do produto é obrigatório");
		}
		
		if(produto.getCategoria() == null) {
			throw new NegocioException("A categoria é obrigatória");
		}
		
		this.produtoDAO.salvar(produto);
	}
}
