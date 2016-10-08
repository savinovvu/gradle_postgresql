package ru.inbox.savinov_vu.to;

import ru.inbox.savinov_vu.entity.Human;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DTOImpl implements DTO {

    public EntityManager em = Persistence.createEntityManagerFactory("HUMAN").createEntityManager();


    public void create(Human human) {
        em.getTransaction().begin();
        em.merge(human);
        em.getTransaction().commit();
    }

    public List<Human> readAll() {
        TypedQuery<Human> namedQuery = em.createNamedQuery("Human.getAll", Human.class);
        return namedQuery.getResultList();
    }


    public void update(Human human) {
        em.getTransaction().begin();
        em.merge(human);
        em.getTransaction().commit();

    }

    @Override
    public Human getOnId(int id) {
        return em.find(Human.class, id);
    }

    public void delete(Human human) {
        em.getTransaction().begin();
        em.remove(human);
        em.getTransaction().commit();
    }




}

