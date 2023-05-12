package com.bde.flix.service;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.model.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contRepo;

    public List<Content> getAllContent()
    {
        return contRepo.findAll();
    }

    public List<Content> getContentByTitle(String infix)
    {
        List<Content> lower = contRepo.findByTitleContaining(infix.toLowerCase());
        List<Content> capital = contRepo.findByTitleContaining(StringUtils.capitalize(infix));
        capital.forEach(content ->
        {
            if(!lower.contains(content)) lower.add(content);
        });
        return lower;
    }

    public List<Content> getContentWithTags(String infix)
    {
        return contRepo.findByGenreTagContaining(infix);
    }

    public List<Content> getContentContainingPart(String infix)
    {
        List<Content> lower = contRepo.findByTitleContainingOrDescriptionContaining(infix.toLowerCase(), infix.toLowerCase());
        List<Content> capital = contRepo.findByTitleContainingOrDescriptionContaining(StringUtils.capitalize(infix),
                StringUtils.capitalize(infix));
        capital.forEach(content ->
        {
            if(!lower.contains(content)) lower.add(content);
        });
        return lower;
    }
}
