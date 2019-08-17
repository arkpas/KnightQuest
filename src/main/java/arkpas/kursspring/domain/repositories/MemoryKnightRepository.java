package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Knight;
import arkpas.kursspring.utils.Ids;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Profile("dev")
public class MemoryKnightRepository implements KnightRepository {

	private Map<Integer,Knight> knights = new HashMap<>();

	public MemoryKnightRepository () {

	}
	@Override
	public void createKnight (String name, int age) {
		createKnight(new Knight(name,age));
	}

	@Override
	public void createKnight (Knight knight) {
		knight.setId(Ids.getNextId(knights.keySet()));
		knights.put(knight.getId(), knight);
	}
	
	@Override
	public Collection<Knight> getKnights () {
		return knights.values();
	}

	
	@Override
	public Knight getKnight (int id) {
		return knights.get(id);
	}
	
	@Override
	public void deleteKnight (int id) {
		knights.remove(id);
	}

	@Override
	public void updateKnight(int id, Knight knight) {
		knights.replace(id, knight);
	}

	@PostConstruct
	@Override
	public void postConstruct () {
		createKnight("Lancelot", 29);
		createKnight("Percival", 25);
	}

	@Override
	public String toString () {
		return getKnights().stream().map(Knight::toString).collect(Collectors.joining(", "));
	}
}
