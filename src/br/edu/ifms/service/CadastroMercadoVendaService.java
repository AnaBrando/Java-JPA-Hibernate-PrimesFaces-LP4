package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.MercadoDAO;
import br.edu.ifms.modelo.Mercado;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroMercadoVendaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MercadoDAO mercadoDAO;
	

	@Transactional
	public void salvar(Mercado mercado) throws NegocioException{
		if(mercado.getVenda() == null ) {
			throw new NegocioException("A venda obrigatório!");
		}
		this.mercadoDAO.salvar(mercado);
	}
}




