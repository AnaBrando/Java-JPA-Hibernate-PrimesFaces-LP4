package br.edu.ifms.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.dao.EstoqueDAO;
import br.edu.ifms.dao.GerenteDAO;
import br.edu.ifms.dao.MercadoDAO;
import br.edu.ifms.dao.VendaDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.modelo.Mercado;
import br.edu.ifms.modelo.Venda;
import br.edu.ifms.service.CadastroMercadoVendaService;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped	
public class NovoMercadoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Mercado mercado;
	 
	private List<Estoque> estoques;
	
	private GerenteDAO gerenteDAO;
	
	@Inject
	private CadastroMercadoVendaService cadastroMercadfoService;
	
	@Inject
	private VendaDAO vendaDAO;
	@Inject
	private MercadoDAO mercadoDAO;
	
	@Inject
	private EstoqueDAO estoqueDAO;
	
	private List<Gerente> gerentes;
	
	public void salvar() {
		try {
			this.cadastroMercadfoService.salvar(mercado);
			FacesUtil.addSuccessMessage("Venda registrada com sucesso");
		}catch(Exception ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.setEstoques(this.estoqueDAO.buscarTodos());
		this.gerentes = gerenteDAO.buscarTodos();
	}
	
	public void limpar() {
		this.mercado = new Mercado();
		this.mercado.setVenda(new Venda());
	}
	
	public Mercado getMercado() {
		return mercado;
	}
	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}
	public List<Gerente> getGerentes() {
		return gerentes;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}
}
	

