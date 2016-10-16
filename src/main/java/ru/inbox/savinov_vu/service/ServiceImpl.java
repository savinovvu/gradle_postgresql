package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.entity.SavedFile;
import ru.inbox.savinov_vu.to.DTO;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceImpl implements Service {
    @Autowired
    DTO dto;

    @Override
    public boolean saveFile(Part part) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YY.MM.dd-hh:mm:ss");
        String fName = formatter.format(LocalDateTime.now()) + "__" + part.getSubmittedFileName();

        Path out = Paths.get("files/" + fName);
        try (final InputStream in = part.getInputStream()) {
            Files.copy(in, out);
            create(new SavedFile(fName, "files/" + fName));
            part.delete();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public String read() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto.readAll());

    }


    @Override
    public boolean create(SavedFile savedFile) {
        dto.create(savedFile);
        return false;
    }

    @Override
    public boolean delete(SavedFile savedFile) {
        savedFile = getOnId(savedFile.getId());
        File file = new File(savedFile.getLoadpath());
        file.delete();
        dto.delete(savedFile);
        return true;
    }

    @Override
    public SavedFile getOnId(int id) {
        return dto.getOnId(id);
    }
}


