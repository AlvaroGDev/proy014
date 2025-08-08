package es.cic.curso25.proy014.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



@Entity
public class Coche {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num_plaza")
    private Long numPlaza;

    private String matricula;
    private String modelo;
    private String color;

    @ManyToOne
    @JoinColumn(name = "garaje_id")
    @JsonBackReference
    private Garaje garaje;

    @OneToMany(mappedBy = "coche", 
    cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},
                fetch = FetchType.EAGER)  // Relación inversa en Multa
    private List<Multa> multas;  // Lista de multas asociadas a este coche
    
    public Coche() {}

    public Coche(String matricula, String modelo, String color, Garaje garaje) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.garaje = garaje;
    }

    // Getters and setters

    public Long getId() {
        return numPlaza;
    }
    
    public void setId(Long id) {
        this.numPlaza = id;
    }

    public Long getNumPlaza() {
        return numPlaza;
    }

    public void setNumPlaza(Long numPlaza) {
        this.numPlaza = numPlaza;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Garaje getGaraje() {
        return garaje;
    }

    public void setGaraje(Garaje garaje) {
        this.garaje = garaje;
    }

    public List<Multa> getMultas() {
        return multas;
    }

    public void setMultas(List<Multa> multas) {
        this.multas = multas;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numPlaza == null) ? 0 : numPlaza.hashCode());
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
        Coche other = (Coche) obj;
        if (numPlaza == null) {
            if (other.numPlaza != null)
                return false;
        } else if (!numPlaza.equals(other.numPlaza))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coche [numPlaza=" + numPlaza + ", matricula=" + matricula + ", modelo=" + modelo + ", color=" + color
                + ", garajeId=" + (garaje != null ? garaje.getId() : null) + "]";
    }

}
