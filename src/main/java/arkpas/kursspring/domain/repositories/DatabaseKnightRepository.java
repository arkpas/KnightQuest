package arkpas.kursspring.domain.repositories;

import java.util.Collection;


import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import arkpas.kursspring.domain.Knight;


@Repository
@Profile("prod")
public class DatabaseKnightRepository implements KnightRepository {


	@Override
	public void createKnight(String name, int age) {

	}

	@Override
	public void createKnight(Knight knight) {

	}

	@Override
	public Collection<Knight> getKnights() {
		return null;
	}

	@Override
	public Knight getKnight(int id) {
		return null;
	}

	@Override
	public void deleteKnight(int id) {

	}

	@Override
	public void updateKnight(int id, Knight knight) {

	}

	@Override
	public void postConstruct() {

	}
}
