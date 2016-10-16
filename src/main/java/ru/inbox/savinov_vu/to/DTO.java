package ru.inbox.savinov_vu.to;

import ru.inbox.savinov_vu.entity.SavedFile;

import java.util.List;

/**
 * Created by skorpion on 27.09.16.
 */
public interface DTO {
    public void create(SavedFile savedFile);

    public List<SavedFile> readAll();

    public void update(SavedFile savedFile);

    public void delete(SavedFile savedFile);

    public SavedFile getOnId(int id);
}

