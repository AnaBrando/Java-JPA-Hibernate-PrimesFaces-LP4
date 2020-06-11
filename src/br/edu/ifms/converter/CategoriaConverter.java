package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.CategoriaDAO;
import br.edu.ifms.modelo.Categoria;
import br.edu.ifms.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter implements Converter {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaConverter() {
		this.categoriaDAO = CDIServiceLocator.getBean(CategoriaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if(value != null) {
			retorno = this.categoriaDAO.buscarPeloCodigo(new Long(value));
		}
		return retorno;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Long codigo = ((Categoria) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		return "";
	}
		
}
