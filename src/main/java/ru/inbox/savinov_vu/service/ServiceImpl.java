package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.entity.Human;
import ru.inbox.savinov_vu.to.DTO;

public class ServiceImpl implements Service {
    @Autowired
    DTO dto;

    public String read() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto.readAll());

    }

    @Override
    public boolean update(Human human) {
        dto.update(human);
        return true;
    }

    @Override
    public boolean create(Human human) {
        dto.create(human);
        return false;
    }

    @Override
    public boolean delete(Human human) {
        dto.delete(human);
        return true;
    }


    @Override
    public Human getOnId(int id) {
        return dto.getOnId(id);
    }
}


