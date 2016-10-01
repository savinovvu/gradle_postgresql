package ru.inbox.savinov_vu.to;

import org.hibernate.Session;
import ru.inbox.savinov_vu.model.Human;
import ru.inbox.savinov_vu.util.HibernateUtil;
import java.util.List;

public class DTOImpl implements DTO {



    public  void create (Human human) {
        Session session = HibernateUtil.getSingletonSession();
        session.beginTransaction();
        session.save(human);
        session.getTransaction().commit();
    }

    public List<Human> readAll() {
        Session session = HibernateUtil.getSingletonSession();
        session.beginTransaction();
       List list = session.createQuery("from Human").list();
        session.getTransaction().commit();
        return list;
    }


    public  void update(Human human) {

        Session session = HibernateUtil.getSingletonSession();
        session.beginTransaction();
        session.update(human);
        session.getTransaction().commit();

    }

    public  void delete (Human human) {
        Human user = human;
        Session session = HibernateUtil.getSingletonSession();
        session.beginTransaction();
        session.delete(human.getName());
        session.getTransaction().commit();
    }




}

