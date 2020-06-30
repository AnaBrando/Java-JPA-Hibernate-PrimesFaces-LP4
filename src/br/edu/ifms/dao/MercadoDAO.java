package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifms.modelo.Estoque;
import br.edu.ifms.modelo.Mercado;
import br.edu.ifms.modelo.Venda;

public class MercadoDAO implements Serializable {

	
	
private static final long serialVersionUID = 1L;

	
	@Inject
	private EntityManager manager;
	
	public void salvar(Mercado venda){
		manager.persist(venda);
	}
	
	public List<Mercado> buscarPorDataEstoque(Date dataEntrega,Estoque estoque){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mercado> criteriaQuery = builder.createQuery(Mercado.class);
		Root<Mercado> a = criteriaQuery.from(Mercado.class);
		criteriaQuery.select(a);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(dataEntrega != null) {
			ParameterExpression<Date> dE = builder.parameter(Date.class,"DataEntrega");
			ParameterExpression<Date> dataSaida = builder.parameter(Date.class,"dataSaida");
			predicates.add(builder.between(a.<Date>get("dataEntrega"), dE,dataSaida));
		}
		if(estoque != null) {
			ParameterExpression<Estoque> estoqueExpression = builder.parameter(Estoque.class,"estoque");
			predicates.add(builder.equal(a.get("estoque").get("mercado"), estoqueExpression));
			
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
			
			TypedQuery<Mercado> query = manager.createQuery(criteriaQuery);
			
		if(dataEntrega != null) {
				Calendar dataEntregaInicial  = Calendar.getInstance();
				dataEntregaInicial.setTime(dataEntrega);
				dataEntregaInicial.set(Calendar.HOUR_OF_DAY, 0);
				dataEntregaInicial.set(Calendar.MINUTE, 0);
				dataEntregaInicial.set(Calendar.SECOND, 0);
				
				Calendar dataEntregaFinal  = Calendar.getInstance();
				dataEntregaFinal.setTime(dataEntrega);
				dataEntregaFinal.set(Calendar.HOUR_OF_DAY, 0);
				dataEntregaFinal.set(Calendar.MINUTE, 0);
				dataEntregaFinal.set(Calendar.SECOND, 0);
		
				query.setParameter("dataEntregaInicial", dataEntregaInicial.getTime());
				query.setParameter("dataEntregaFinal", dataEntregaFinal.getTime());
			}
			
		if(estoque != null) {
				query.setParameter("estoque", estoque);
	
		}
		return query.getResultList();
	}
		return null;
	
	}

}
