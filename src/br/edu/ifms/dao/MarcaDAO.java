package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.edu.ifms.modelo.Marca;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class MarcaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Marca buscarPeloCodigo(Long codigo) {
		return manager.find(Marca.class, codigo);
	}
	
	public void salvar(Marca marca) {
		manager.merge(marca);
	}
	
	@SuppressWarnings("unchecked")
	public List<Marca> buscarTodos(){
		return manager.createQuery("from Marca").getResultList();
	}
	
	@Transactional
	public void excluir(Marca marca) throws NegocioException{
		marca = buscarPeloCodigo(marca.getCodigo());
		try {
			manager.remove(marca);
			manager.flush();
		} catch(PersistenceException e) {
			throw new NegocioException("Este marca não pode ser excluído.");
		}
	}
}
