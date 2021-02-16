package com.jsimmons.lrnr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jsimmons.lrnr.entities.Notebook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class NotebookServiceIntegrationTest {

    @Autowired
    private NotebookService notebookService;

    @Test
    public void testAddNotebook(){
        // Create notebook
        Notebook aNotebook = new Notebook();
        aNotebook.setNotebookName("BrandonNotebook");

        // test adding the notebook to datastore
        Notebook newNotebook = notebookService.saveNotebook(aNotebook);

        // verify notebook was saved correctly
        assertNotNull(newNotebook);
        assertNotNull(newNotebook.getId());
        assertEquals("BrandonNotebook", newNotebook.getNotebookName());
    }
    
}
