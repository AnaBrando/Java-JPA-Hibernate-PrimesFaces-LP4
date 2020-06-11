package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifms.modelo.Produto;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Produto buscarPeloCodigo(Long codigo) {
		return manager.find(Produto.class, codigo);
	}
	
	public void salvar(Produto produto) {
		manager.merge(produto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarTodos(){
		return manager.createQuery("from Produto").getResultList();
	}
	
	@Transactional
	public void excluir(Produto produto) throws NegocioException{
		produto = buscarPeloCodigo(produto.getCodigo());
		try {
			manager.remove(produto);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Este produto não pode ser excluído.");
		}
	}

}
