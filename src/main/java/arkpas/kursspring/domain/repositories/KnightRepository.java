package arkpas.kursspring.domain.repositories;

import java.util.Collection;
import arkpas.kursspring.domain.Knight;


public interface KnightRepository {

	public void createKnight (String name, int age);

	public void createKnight (Knight knight);
	
	public Collection<Knight> getKnights ();
	
	public Knight getKnight (int id);
	
	public void deleteKnight (int id);

	public void updateKnight (int id, Knight knight);
	
	public void postConstruct ();
 
}
