package es.cic.curso25.proy014.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Garaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  

    private String direccion;  

    private Long capacidadMaxima;  

    private String telefono;  

    private String propietario;  

    @OneToMany(mappedBy = "garaje",
                cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},
                orphanRemoval = true,
                fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Coche> coches = new ArrayList<>();  

    public Garaje() {
        // Constructor vacío requerido por JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    public boolean estaLleno() {
        return coches.size() >= capacidadMaxima;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Garaje other = (Garaje) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Garaje [id=" + id + ", direccion=" + direccion + ", capacidadMaxima=" + capacidadMaxima + ", telefono="
                + telefono + ", propietario=" + propietario + ", coches.size=" + (coches != null ? coches.size() : 0) + "]";
                // Si coches NO es nulo, devuelve el tamaño de la lista coches, si no, devuelve 0
    }

    

    
}