package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.ProdutoDAO;
import br.edu.ifms.modelo.Produto;
import br.edu.ifms.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter{
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoConverter() {
		this.produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Produto retorno = null;
		
		if(value != null) {
			retorno = this.produtoDAO.buscarPeloCodigo(new Long(value));
		}
		
		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext contex, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Produto) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return "";
	}

}

