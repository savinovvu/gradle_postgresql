package ru.inbox.savinov_vu.service;

import ru.inbox.savinov_vu.model.Human;

import java.util.List;

/**
 * Created by skorpion on 27.09.16.
 */
public interface Service {
    public List<Human> read();
    public boolean update(Human human);
    public boolean create(Human human);
    public boolean delete(Human human);
}
