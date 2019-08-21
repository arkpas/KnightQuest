package arkpas.kursspring.domain.repositories;

import java.util.Collection;
import java.util.List;

import arkpas.kursspring.domain.Knight;


public interface KnightRepository {

	public void createKnight (String name, int age);

	public void createKnight (Knight knight);
	
	public List<Knight> getKnights ();
	
	public Knight getKnight (int id);
	
	public void deleteKnight (Knight knight);

	public void updateKnight (Knight knight);

 
}
