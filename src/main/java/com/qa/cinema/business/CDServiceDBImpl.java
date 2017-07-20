package com.qa.cinema.business;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.cinema.persistence.CD;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class CDServiceDBImpl implements CDService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllCDs() {
		Query query = manager.createQuery("Select m FROM CD m");
		Collection<CD> cd = (Collection<CD>) query.getResultList();
		return util.getJSONForObject(cd);
	}

	@Override
	public String createCD(String cd) {
		CD aCD = util.getObjectForJSON(cd, CD.class);
		manager.persist(aCD);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Override
	public String updateCD(Long id, String cd) {
		CD updatedMovie = util.getObjectForJSON(cd, CD.class);
		CD CDInDB = findCD(id);
		if (CDInDB != null) {
			CDInDB = updatedMovie;
			manager.merge(CDInDB);
		}
		return "{\"message\": \"movie sucessfully updated\"}";
	}

	@Override
	public String deleteCD(Long id) {
		CD CDInDB = findCD(id);
		if (CDInDB != null) {
			manager.remove(CDInDB);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	private CD findCD(Long id) {
		return manager.find(CD.class, id);
	}

}
