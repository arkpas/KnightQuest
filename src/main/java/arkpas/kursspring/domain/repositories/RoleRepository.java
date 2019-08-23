package arkpas.kursspring.domain.repositories;

import arkpas.kursspring.domain.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void createRole (String username, String authority) {
            Role role = new Role(username, authority);
            entityManager.persist(role);
    }
}
