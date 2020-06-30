package br.edu.ifms.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifms.dao.CaixaDAO;
import br.edu.ifms.modelo.Caixa;
import br.edu.ifms.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Caixa.class)
public class CaixaConverter implements Converter {
		
		private CaixaDAO caixaDAO;
		
		public CaixaConverter() {
			this.caixaDAO = CDIServiceLocator.getBean(CaixaDAO.class);
		}
		
		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			Caixa retorno = null;
			
			if(value != null) {
				retorno = this.caixaDAO.buscarPeloCodigo(new Long(value));
			}
			return retorno;
		}
		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			if(value != null) {
				Long codigo = ((Caixa) value).getCodigo();
				String retorno = (codigo == null ? null : codigo.toString());
				
				return retorno;
			}
			return "";
		}
}
