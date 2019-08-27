package arkpas.kursspring.domain.repositories;

import java.util.Collection;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import arkpas.kursspring.domain.Knight;


@Repository
@Profile("prod")
public class DatabaseKnightRepository implements KnightRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional
	public void createKnight(Knight knight) {
		entityManager.persist(knight);
	}

	@Override
	public List<Knight> getKnights(int playerId) {
		return entityManager.createQuery("SELECT k FROM Knight AS k WHERE player_id = :playerId", Knight.class)
				.setParameter("playerId", playerId)
				.getResultList();
	}

	@Override
	public Knight getKnight(int id) {
		return entityManager.find(Knight.class, id);
	}

	@Override
	@Transactional
	public void deleteKnight(Knight knight) {
		entityManager.remove(knight);
	}

	@Override
	@Transactional
	public void updateKnight(Knight knight) {
		entityManager.merge(knight);
	}

}
