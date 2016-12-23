/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx;

import com.chortitzer.pcbjfx.domain.TblBasContratos;
import com.chortitzer.pcbjfx.domain.TblBasPrecios;
import com.chortitzer.pcbjfx.domain.Tblempresa;
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
    private DaoBase<TblBasContratos> daoTblBasContratos = new DaoBase<>(TblBasContratos.class);
    private DaoBase<Tblproductos> daoTblproductoss = new DaoBase<>(Tblproductos.class);
    private DaoBase<Tblempresa> daoTblempresa = new DaoBase<>(Tblempresa.class);

    @FXML
    private TableControl<TblBasPrecios> preciosTable;
    @FXML
    private TableControl<TblBasContratos> contratosTable;

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

        initContratos();

    }

    private void initContratos() {
        contratosTable.setRecordClass(TblBasContratos.class);
        contratosTable.setController(cntlTblBasContratos);
        LocalDateTimeColumn<TblBasContratos> cFecha = new LocalDateTimeColumn<>("fecha");
        cFecha.setText("Fecha de Inicio de Vigencia");
        cFecha.setDateFormat(DateTimeFormatter.ISO_DATE);
        cFecha.setMinWidth(200);

        TypeAheadColumn<TblBasContratos, Tblempresa> cEmpresa = new TypeAheadColumn<>("idEmpresa");
        cEmpresa.setText("Empresa");
        cEmpresa.setMinWidth(200);
        List<Tblempresa> lEmpresa = daoTblempresa.getList();
        lEmpresa.forEach((p) -> {
            cEmpresa.addItem(p.getNombre(), p);
        });

        TypeAheadColumn<TblBasContratos, Tblproductos> cProducto = new TypeAheadColumn<>("idProducto");
        cProducto.setText("Producto");
        cProducto.setMinWidth(200);
        List<Tblproductos> lProd = daoTblproductoss.getList();
        lProd.forEach((p) -> {
            cProducto.addItem(p.getDescripcion(), p);
        });
        NumberColumn<TblBasContratos, Integer> cPrecio = new NumberColumn<>("precioGsPorKg", Integer.class);
        cPrecio.setText("Precio PYG/Kg");
        contratosTable.addColumn(cFecha, cEmpresa, cProducto, cPrecio);
        contratosTable.reload();
        cFecha.setSortType(TableColumn.SortType.DESCENDING);
        contratosTable.refresh();
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

        @Override
        public void delete(List<TblBasPrecios> records) {
            daoTblBasPrecios.delete(records);
        }
    };

    private TableController<TblBasContratos> cntlTblBasContratos = new TableController<TblBasContratos>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return daoTblBasContratos.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public List<TblBasContratos> insert(List<TblBasContratos> newRecords) {
            return daoTblBasContratos.insert(newRecords);
        }

        @Override
        public List<TblBasContratos> update(List<TblBasContratos> records) {
            return daoTblBasContratos.update(records);
        }

        @Override
        public void delete(List<TblBasContratos> records) {
            daoTblBasContratos.delete(records);
        }
    };

}
