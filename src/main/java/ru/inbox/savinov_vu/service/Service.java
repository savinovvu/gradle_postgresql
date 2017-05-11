package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.inbox.savinov_vu.entity.Human;

/**
 * Created by skorpion on 27.09.16.
 */
public interface Service {
    public String read() throws JsonProcessingException;

    public boolean update(Human human);

    public boolean create(Human human);

    public boolean delete(Human human);

    public Human getOnId(int id);
}
