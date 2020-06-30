package br.edu.ifms.service;

import java.io.Serializable;
import javax.inject.Inject;
import br.edu.ifms.dao.CaixaDAO;
import br.edu.ifms.modelo.Caixa;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroCaixaService implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private CaixaDAO caixaDAO;
	
	@Transactional
	public void salvar(Caixa caixa) throws NegocioException{
		if(caixa.getNome() == null || caixa.getNome().trim().equals("")) {
			throw new NegocioException("O nome da caixa é obrigatório!");
		}
		this.caixaDAO.salvar(caixa);
	}
}