package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.MarcaDAO;
import br.edu.ifms.modelo.Marca;
import br.edu.ifms.util.cdi.CDIServiceLocator;

@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter {

	private MarcaDAO marcaDAO;
	
	public MarcaConverter() {
		this.marcaDAO = CDIServiceLocator.getBean(MarcaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Marca retorno = null;
		
		if(arg2 != null) {
			retorno = this.marcaDAO.buscarPeloCodigo(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 != null) {
			Long codigo = ((Marca) arg2).getCodigo();
			String retorno = (codigo == null ? null :codigo.toString());
			return retorno;
		}
		return "";
	}
	

}
