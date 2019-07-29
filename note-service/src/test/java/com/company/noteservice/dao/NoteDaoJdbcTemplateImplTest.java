package com.company.noteservice.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcTemplateImplTest {

    @Autowired
    protected NoteDao dao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addNote() {
    }

    @Test
    public void getNote() {
    }

    @Test
    public void getAllNotes() {
    }

    @Test
    public void updateNote() {
    }

    @Test
    public void deleteNote() {
    }

    @Test
    public void getNotesByBook() {
    }
}