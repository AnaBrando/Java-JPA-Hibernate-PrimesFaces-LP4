package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void salvar (Categoria categoria) {
		em.merge(categoria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> buscarTodos(){
		return em.createQuery("from Categoria").getResultList();
	}
	
	@Transactional
	public void excluir(Categoria categoria) throws NegocioException{
		Categoria categoriaTemp = em.find(Categoria.class, categoria.getCodigo());
		
		em.remove(categoriaTemp);
		em.flush();
	}
	
	public Categoria buscarPeloCodigo(Long codigo) {
		return em.find(Categoria.class, codigo);
	}
}
