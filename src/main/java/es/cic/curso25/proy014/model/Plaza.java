package es.cic.curso25.proy014.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "plaza")
public class Plaza {

    public enum Sector {
        A_1, A_2, B_1, B_2, C_1, C_2
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

       // Ponemos que por defecto seran 150 plazas
    private Long maximoPlazas;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @OneToMany(mappedBy = "plaza", cascade = { CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE }, orphanRemoval = true)
    @JsonManagedReference
    private List<Vehiculo> vehiculos = new ArrayList<>(5);

    @Transient 
    private final Long MAX_VEHICULOS_POR_PLAZA = 5L;

    public Plaza() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // No hay setMaximoPlazas para que no lo puedan modificar

    public Long getMaximoPlazas() {
        return maximoPlazas;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> coches) {
        this.vehiculos = coches;
    }

    public boolean haySitio() {
        return this.vehiculos.size() < maximoPlazas; 
        // Si la lista de vehiculos es menor a la capacidad maxima, devuelve true (hay sitio)
    }

    public boolean agregarVehiculo (Vehiculo vehiculo) throws SecurityException {

        if (!haySitio()) // Si no hay sitio, lanza una excepción
            throw new SecurityException("Error: no puedes añadir más coches, el garaje está lleno");

        if(this.getVehiculos().size() >= MAX_VEHICULOS_POR_PLAZA)     // Si la lista de vehiculos es mayor o igual a 5, lanza una excepción
            throw new SecurityException("Error: no puedes añadir más coches a la plaza, esta llena");

        vehiculos.add(vehiculo);
        vehiculo.setPlaza(this);
        return true;

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
        Plaza other = (Plaza) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
