/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tblempresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblempresa.findAll", query = "SELECT t FROM Tblempresa t")
    ,
    @NamedQuery(name = "Tblempresa.findById", query = "SELECT t FROM Tblempresa t WHERE t.id = :id")
    ,
    @NamedQuery(name = "Tblempresa.findByNombre", query = "SELECT t FROM Tblempresa t WHERE t.nombre = :nombre")
    ,
    @NamedQuery(name = "Tblempresa.findByDireccion", query = "SELECT t FROM Tblempresa t WHERE t.direccion = :direccion")
    ,
    @NamedQuery(name = "Tblempresa.findByTelefono", query = "SELECT t FROM Tblempresa t WHERE t.telefono = :telefono")
    ,
    @NamedQuery(name = "Tblempresa.findByCuentacorriente", query = "SELECT t FROM Tblempresa t WHERE t.cuentacorriente = :cuentacorriente")
    ,
    @NamedQuery(name = "Tblempresa.findByRuc", query = "SELECT t FROM Tblempresa t WHERE t.ruc = :ruc")})
public class Tblempresa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String cuentacorriente;

    private String ruc;

    private Collection<TblBasContratos> tblBasContratosCollection;

    public Tblempresa() {
    }

    /*
    public Tblempresa(Integer id) {
        this.id = id;
    }

    public Tblempresa(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "cuentacorriente")
    public String getCuentacorriente() {
        return cuentacorriente;
    }

    public void setCuentacorriente(String cuentacorriente) {
        this.cuentacorriente = cuentacorriente;
    }

    @Column(name = "ruc")
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    public Collection<TblBasContratos> getTblBasContratosCollection() {
        return tblBasContratosCollection;
    }

    public void setTblBasContratosCollection(Collection<TblBasContratos> tblBasContratosCollection) {
        this.tblBasContratosCollection = tblBasContratosCollection;
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
        if (!(object instanceof Tblempresa)) {
            return false;
        }
        Tblempresa other = (Tblempresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
*/
    @Override
    public String toString() {
        return nombre;
    }

}
