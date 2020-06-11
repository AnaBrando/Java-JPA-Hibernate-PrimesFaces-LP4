package br.edu.ifms.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifms.dao.EstoqueDAO;
import br.edu.ifms.modelo.Estoque;

public class LazyEstoqueDataModel extends LazyDataModel<Estoque> implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private EstoqueDAO estoqueDAO;
	
	public LazyEstoqueDataModel(EstoqueDAO estoqueDAO) {
		this.estoqueDAO = estoqueDAO;
	}
	
	@Override
	public List<Estoque> load(int first,int pageSize,String sortField,SortOrder sortOrder,Map<String,String> filters){
		List<Estoque> estoques = this.estoqueDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.estoqueDAO.encontrarQuantidadeDeCarros().intValue());
		
		return estoques;
	}

}
