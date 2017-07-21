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
		return "{\"message\": \"cd sucessfully added\"}";
	}

	@Override
	public String updateCD(Long id, String cd) {
		CD updatedCD = util.getObjectForJSON(cd, CD.class);
		CD CDInDB = findCD(id);
		updatedCD.setId(id);
		if (CDInDB != null) {
			CDInDB = updatedCD;
			manager.merge(CDInDB);
		}
		return "{\"message\": \"cd sucessfully updated\"}";
	}

	@Override
	public String deleteCD(Long id) {
		CD CDInDB = findCD(id);
		if (CDInDB != null) {
			manager.remove(CDInDB);
		}
		return "{\"message\": \"cd sucessfully deleted\"}";
	}

	private CD findCD(Long id) {
		return manager.find(CD.class, id);
	}

	@Override
	public String deleteAllCDs() {
		Query query=manager.createQuery("Delete FROM CD");
		query.executeUpdate();
		return "{\"message\": \"all cds sucessfully deleted\"}";
	}

	@Override
	public String getCD(Long id) {
		Query query = manager.createQuery("Select m FROM CD m where m.id=:id").setParameter("id",id);
		Collection<CD> cds = (Collection<CD>) query.getResultList();
		return util.getJSONForObject(cds);
	}
}