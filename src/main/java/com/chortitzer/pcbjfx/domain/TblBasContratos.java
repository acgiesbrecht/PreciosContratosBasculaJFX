 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */


@Entity
@Table(name = "tbl_bas_contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBasContratos.findAll", query = "SELECT t FROM TblBasContratos t")
    ,
    @NamedQuery(name = "TblBasContratos.findById", query = "SELECT t FROM TblBasContratos t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TblBasContratos.findByFecha", query = "SELECT t FROM TblBasContratos t WHERE t.fecha = :fecha")
    ,
    @NamedQuery(name = "TblBasContratos.findByPrecioGsPorKg", query = "SELECT t FROM TblBasContratos t WHERE t.precioGsPorKg = :precioGsPorKg")})
public class TblBasContratos implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private final IntegerProperty id = new SimpleIntegerProperty();

    private final ObjectProperty<LocalDateTime> fecha = new SimpleObjectProperty<>();

    private final IntegerProperty precioGsPorKg = new SimpleIntegerProperty();

    private final ObjectProperty<Tblempresa> idEmpresa = new SimpleObjectProperty<>();

    private final ObjectProperty<Tblproductos> idProducto = new SimpleObjectProperty<>();

    public TblBasContratos() {
    }

    /*
    public TblBasContratos(Integer id) {
        this.id = id;
    }

    public TblBasContratos(Integer id, LocalDateTime fecha, int precioGsPorKg) {
        this.id = id;
        this.fecha = fecha;
        this.precioGsPorKg = precioGsPorKg;
    }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id.get();
    }
    
    public void setId(Integer id) {
        this.id.set(id);
    }

    @Basic(optional = false)
    @Column(name = "fecha")
    public LocalDateTime getFecha() {
        return fecha.get();
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha.set(fecha);
    }

    @Basic(optional = false)
    @Column(name = "precio_gs_por_kg")
    public int getPrecioGsPorKg() {
        return precioGsPorKg.get();
    }

    public void setPrecioGsPorKg(int precioGsPorKg) {
        this.precioGsPorKg.set(precioGsPorKg);
    }

    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Tblempresa getIdEmpresa() {
        return idEmpresa.get();
    }

    public void setIdEmpresa(Tblempresa idEmpresa) {
        this.idEmpresa.set(idEmpresa);
    }

    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Tblproductos getIdProducto() {
        return idProducto.get();
    }

    public void setIdProducto(Tblproductos idProducto) {
        this.idProducto.set(idProducto);
    }

    /*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBasContratos)) {
            return false;
        }
        TblBasContratos other = (TblBasContratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
     */
    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasContratos[ id=" + id + " ]";
    }

}
