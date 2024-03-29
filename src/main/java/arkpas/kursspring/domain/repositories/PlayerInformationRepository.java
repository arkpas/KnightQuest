package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.PlayerInformation;
import arkpas.kursspring.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PlayerInformationRepository {

    @PersistenceContext
    EntityManager entityManager;

    private PasswordEncoder encoder;
    private RoleRepository roleRepository;

    @Autowired
    public PlayerInformationRepository(PasswordEncoder encoder, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createPlayer (String username, String password) {
        password = encoder.encode(password);
        PlayerInformation playerInformation = new PlayerInformation(username, password);
        entityManager.persist(playerInformation);
        roleRepository.createRole(username, "USER");
    }


    public PlayerInformation getPlayer (int id) {
       return entityManager.find(PlayerInformation.class, id);
    }

    public PlayerInformation getPlayer (String name) {
        PlayerInformation playerInformation = null;
        List<PlayerInformation> results = entityManager.createQuery("SELECT player FROM PlayerInformation AS player WHERE username= :username", PlayerInformation.class).setParameter("username", name).getResultList();
        if (!results.isEmpty())
            playerInformation = results.get(0);
        return playerInformation;
    }

    @Transactional
    public void updatePlayer (PlayerInformation playerInformation) {
        entityManager.merge(playerInformation);
    }

    @Transactional
    public void deletePlayer (PlayerInformation playerInformation) {
        entityManager.remove(playerInformation);
    }



}
