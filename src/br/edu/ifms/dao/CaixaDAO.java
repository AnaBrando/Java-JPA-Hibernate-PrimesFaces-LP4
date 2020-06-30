package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifms.modelo.Caixa;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class CaixaDAO implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;	
	
	public Caixa buscarPeloCodigo(Long codigo) {
		return manager.find(Caixa.class, codigo);
	}
	
	public void salvar(Caixa marca) {
		manager.merge(marca);
	}
	
	@SuppressWarnings("unchecked")
	public List<Caixa> buscarTodos(){
		return manager.createQuery("from Caixa").getResultList();
	}
	
	@Transactional
	public void excluir(Caixa caixa) throws NegocioException{
		caixa = buscarPeloCodigo(caixa.getCodigo());
		try {
			manager.remove(caixa);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Este Caixa não pode ser excluído.");
		}
	}
}

