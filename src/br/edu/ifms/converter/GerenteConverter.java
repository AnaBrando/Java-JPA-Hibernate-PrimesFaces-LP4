package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.GerenteDAO;
import br.edu.ifms.modelo.Gerente;
import br.edu.ifms.util.cdi.CDIServiceLocator;


@FacesConverter(forClass=Gerente.class)
public class GerenteConverter implements Converter {
	
	private GerenteDAO gerenteDAO;
	
	public GerenteConverter() {
		this.gerenteDAO = CDIServiceLocator.getBean(GerenteDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Gerente retorno = null;
		if(arg2 != null) {
			retorno = this.gerenteDAO.buscarPeloCodigo(new Long(arg2));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 != null ) {
			Long codigo = ((Gerente)arg2).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		return "";
	}
}
