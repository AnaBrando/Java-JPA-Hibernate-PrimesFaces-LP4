package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class GerenteDAO implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
	
	public Gerente buscarPeloCodigo(Long codigo) {
		return manager.find(Gerente.class, codigo);
	}
	
	public void salvar(Gerente marca) {
		manager.merge(marca);
	}
	
	@SuppressWarnings("unchecked")
	public List<Gerente> buscarTodos(){
		return manager.createQuery("from Gerente").getResultList();
	}
	
	@Transactional
	public void excluir(Gerente gerente) throws NegocioException{
		gerente = buscarPeloCodigo(gerente.getCodigo());
		try {
			manager.remove(gerente);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Este Gerente não pode ser excluído.");
		}
	}
}
