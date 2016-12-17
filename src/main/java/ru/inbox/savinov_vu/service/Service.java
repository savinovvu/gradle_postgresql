package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.inbox.savinov_vu.model.Picture;

/**
 * Created by skorpion on 27.09.16.
 */
public interface Service {
    public boolean save(Picture savedFile);

    public String read() throws JsonProcessingException;

    public boolean create(Picture savedFile);

    public boolean delete(Picture savedFile);

    public Picture getOnId(int id);
}
