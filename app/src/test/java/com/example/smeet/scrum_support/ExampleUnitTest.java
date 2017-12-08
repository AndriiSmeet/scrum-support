package com.example.smeet.scrum_support;

import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    StoryDaoImpl storyDao = new StoryDaoImpl();
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getStory() throws Exception {
        List<Story> stories = storyDao.getAll();
        assertNotNull("Stories not found", stories);
        System.out.println("Category list size: " + stories.size());
    }

    @Test
    public void getStoryById() throws Exception {
        Story stories = storyDao.getById(1);
        assertNotNull("Story not found", stories);
        System.out.println("Story id: " + stories.getId());
    }
}