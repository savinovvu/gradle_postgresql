package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.model.Picture;
import ru.inbox.savinov_vu.repository.SavedPictureRepository;

@org.springframework.stereotype.Service
public class ServiceImpl implements PictureService {
    @Autowired
    SavedPictureRepository repository;

    @Override
    public boolean save(Picture picture) {

            return false;
        }

    @Override
    public String read() throws JsonProcessingException {
        return null;
    }

    @Override
    public String read(int id) throws JsonProcessingException {
        return String.valueOf(repository.findOne(id));
    }

    @Override
    public boolean create(Picture savedFile) {
        return false;
    }

    @Override
    public boolean delete(Picture savedFile) {
        return false;
    }

    @Override
    public Picture getOnId(int id) {
        return null;
    }

}




