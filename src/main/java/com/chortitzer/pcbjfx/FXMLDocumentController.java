/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx;

import com.chortitzer.pcbjfx.domain.TblBasPrecios;
import com.chortitzer.pcbjfx.domain.Tblproductos;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.LocalDateTimeColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TypeAheadColumn;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author adriang
 */


public class FXMLDocumentController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger(FXMLDocumentController.class);
    private DaoBase<TblBasPrecios> daoTblBasPrecios = new DaoBase<>(TblBasPrecios.class);
    private DaoBase<Tblproductos> daoTblproductoss = new DaoBase<>(Tblproductos.class);
    
    @FXML
    private TableControl preciosTable;

    //EntityManager em = Persistence.createEntityManagerFactory("pcb_PU").createEntityManager();    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //preciosTable.setItems(FXCollections.observableArrayList(em.createQuery("select t from TblBasPrecios t").getResultList()));
        
        preciosTable.setRecordClass(TblBasPrecios.class);
        preciosTable.setController(cntlTblBasPrecios);
        LocalDateTimeColumn<TblBasPrecios> cFecha = new LocalDateTimeColumn<>("fechahoraVigencia");
        cFecha.setText("Fecha de Inicio de Vigencia");
        cFecha.setDateFormat(DateTimeFormatter.ISO_DATE);
        cFecha.setMinWidth(200);
        cFecha.setSortType(TableColumn.SortType.DESCENDING);
        TypeAheadColumn<TblBasPrecios, Tblproductos> cProducto = new TypeAheadColumn<>("idProducto");
        cProducto.setText("Producto");                
        cProducto.setMinWidth(200);        
        List<Tblproductos> lProd = daoTblproductoss.getList();
        lProd.forEach((p) -> {
            cProducto.addItem(p.getDescripcion(), p);
        });
        NumberColumn<TblBasPrecios, Integer> cPrecio = new NumberColumn<>("valorGsPorKg", Integer.class);
        cPrecio.setText("Precio PYG/Kg");
        preciosTable.addColumn(cFecha, cProducto, cPrecio);
        preciosTable.reload();
        
    }
    
    private TableController<TblBasPrecios> cntlTblBasPrecios = new TableController<TblBasPrecios>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return daoTblBasPrecios.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public List<TblBasPrecios> insert(List<TblBasPrecios> newRecords) {
            return daoTblBasPrecios.insert(newRecords);
        }

        @Override
        public List<TblBasPrecios> update(List<TblBasPrecios> records) {
            return daoTblBasPrecios.update(records);
        }

        /*@Override
        public boolean canDelete(TableControl table) {
            *
             * This checking is not perfect. If there are Persons filtered thus not
             * displayed in tblPerson, the delete is not canceled. An error will be displayed
             * along with the stack trace. The better implementation is to count the children
             * from database and ensure the result is zero.
             
            boolean nochildren = tblPerson.getRecords().isEmpty();
            if (!nochildren) {
                MessageDialogBuilder.error().message("Unable to delete TblBasPrecios (code "+
                        TblBasPrecios.getSelectedItem().getCode() +") because"
                        + "\nthere are Persons refer to it!").show(getScene().getWindow());
            }
            return nochildren;
        }*/

        @Override
        public void delete(List<TblBasPrecios> records) {
            daoTblBasPrecios.delete(records);
        }
    };

   
}
