package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.inbox.savinov_vu.entity.SavedFile;

import javax.servlet.http.Part;

/**
 * Created by skorpion on 27.09.16.
 */
public interface Service {
    public boolean saveFile(Part part);

    public String read() throws JsonProcessingException;

    public boolean create(SavedFile savedFile);

    public boolean delete(SavedFile savedFile);

    public SavedFile getOnId(int id);
}
