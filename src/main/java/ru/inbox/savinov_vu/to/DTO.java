package ru.inbox.savinov_vu.to;

import ru.inbox.savinov_vu.entity.Human;

import java.util.List;

/**
 * Created by skorpion on 27.09.16.
 */
public interface DTO {
    public void create(Human human);
    public List<Human> readAll();
    public void update(Human human);
    public void delete(Human human);

    public Human getOnId(int id);
}

