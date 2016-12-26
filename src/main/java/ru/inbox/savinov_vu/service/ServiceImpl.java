package ru.inbox.savinov_vu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.inbox.savinov_vu.model.Picture;
import ru.inbox.savinov_vu.repository.SavedPictureRepository;
import ru.inbox.savinov_vu.util.Downloader;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class ServiceImpl implements PictureService {
    @Autowired
    private SavedPictureRepository repository;


    @Override
    public String read() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(repository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "mistake in read method Service";
    }


    @Override
    public boolean put(String url) {

        String path = String.format("src/main/resources/public/filesJpg/%s.jpg", LocalDateTime.now());
        Downloader.downloadFiles(url, path);

        repository.saveAndFlush(new Picture(url, path));


        return true;
    }

}

    /*
    html page parsing. save for next projects

    @Override
    public boolean put(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            Elements metaElements = document.select("meta");
            String pictureURL = "";
            int like = 0;
            for (Element element : metaElements) {
                String elementString = String.valueOf(element);
                if (elementString.contains("<meta property=\"og:image\"")) {
                    pictureURL = element.attr("content");
                }

                if (elementString.contains("name=\"description\"")) {
                    like = Integer.valueOf(elementString.replaceAll("\\D", ""));

                }

            }
            String path = String.format("src/main/resources/public/filesJpg/%s.jpg", LocalDateTime.now());
            Downloader.downloadFiles(pictureURL);

            repository.saveAndFlush(new Picture(url, path, like));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/





