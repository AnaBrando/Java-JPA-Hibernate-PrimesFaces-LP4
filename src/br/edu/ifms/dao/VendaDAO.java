package br.edu.ifms.dao;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.edu.ifms.modelo.Venda;

public class VendaDAO implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Venda venda) {
		manager.persist(venda);
	}
}
	

