/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class FXMLDocumentController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger(FXMLDocumentController.class);
    
    @FXML
    private TableView preciosTable;

    EntityManager em = Persistence.createEntityManagerFactory("pcb_PU").createEntityManager();    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preciosTable.setItems(FXCollections.observableArrayList(em.createQuery("select t from TblBasPrecios t").getResultList()));
    }

   
}
