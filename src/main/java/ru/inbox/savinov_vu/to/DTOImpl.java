package ru.inbox.savinov_vu.to;

import ru.inbox.savinov_vu.entity.SavedFile;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class DTOImpl implements DTO {

    public EntityManager em = Persistence.createEntityManagerFactory("FILES").createEntityManager();


    public void create(SavedFile savedFile) {
        em.getTransaction().begin();
        em.merge(savedFile);
        em.getTransaction().commit();
    }

    public List<SavedFile> readAll() {
        TypedQuery<SavedFile> namedQuery = em.createNamedQuery("Files.getAll", SavedFile.class);
        return namedQuery.getResultList().stream().sorted(SavedFile::compareTo).collect(Collectors.toList());
    }


    public void update(SavedFile savedFile) {
        em.getTransaction().begin();
        em.merge(savedFile);
        em.getTransaction().commit();


    }

    @Override
    public SavedFile getOnId(int id) {
        return em.find(SavedFile.class, id);
    }

    public void delete(SavedFile savedFile) {
        em.getTransaction().begin();
        em.remove(savedFile);
        em.getTransaction().commit();
    }


}

