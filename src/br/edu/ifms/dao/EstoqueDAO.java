package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class EstoqueDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Estoque buscarPeloCodigo(Long codigo) {
		return manager.find(Estoque.class, codigo);
	}
	
	public void salvar(Estoque estoque) {
		manager.merge(estoque);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estoque> buscarTodos(){
		return manager.createQuery("from Estoque").getResultList();
	}
	
	@Transactional
	public void excluir(Estoque estoque) throws NegocioException{
		estoque = buscarPeloCodigo(estoque.getCodigo());
		try {
			manager.remove(estoque);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Este estoque não pode ser excluído.");
		}
	}
	
	public Estoque buscarEstoqueComMarcas(Long codigo) {
		try {
			return manager.createNamedQuery("Estoque.buscarEstoqueComMarcas",Estoque.class)
					.setParameter("codigo", codigo)
					.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Estoque> buscarComPaginacao(int first, int pageSize){
		return manager.createNamedQuery("Estoque.buscarTodos")
				.setFirstResult(first).setMaxResults(pageSize).getResultList();
				
	}
	
	public Long encontrarQuantidadeDeCarros() {
		return manager.createQuery("select count(e) from Estoque e",Long.class).getSingleResult();
	}
}
