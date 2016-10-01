package ru.inbox.savinov_vu.service;

import ru.inbox.savinov_vu.model.Human;
import ru.inbox.savinov_vu.to.DTO;
import ru.inbox.savinov_vu.to.DTOImpl;

import java.util.List;

public class ServiceImpl implements Service {
    DTO dto = new DTOImpl();

    public List<Human> read() {
        return dto.readAll();
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
}


