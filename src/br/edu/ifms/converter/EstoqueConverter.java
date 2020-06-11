package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.EstoqueDAO;
import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Estoque.class)
public class EstoqueConverter implements Converter{
	
	private EstoqueDAO estoqueDAO;
	
	public EstoqueConverter() {
		this.estoqueDAO = CDIServiceLocator.getBean(EstoqueDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estoque retorno = null;
		
		if(value != null) {
			retorno = this.estoqueDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Estoque) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		return "";
	}
	
}
