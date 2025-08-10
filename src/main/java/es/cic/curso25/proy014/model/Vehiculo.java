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
public class Vehiculo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    private String matricula;
    private String modelo;
    private String color;

    @ManyToOne
    @JoinColumn(name = "plaza_id")
    @JsonBackReference
    private Plaza plaza;

    @OneToMany(mappedBy = "vehiculo", 
    cascade = {CascadeType.REMOVE, CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},
                fetch = FetchType.EAGER)  // Relación inversa en Multa
    private List<Multa> multas;  // Lista de multas asociadas a este coche
    
    public Vehiculo() {}

    public Vehiculo(String matricula, String modelo, String color, Plaza plaza) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
        this.plaza = plaza;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }

    public void setGaraje(Plaza plaza) {
        this.plaza = plaza;
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
        Vehiculo other = (Vehiculo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coche [numPlaza=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", color=" + color
                + ", garajeId=" + (plaza != null ? plaza.getId() : null) + "]";
                // Si garaje NO es nulo, devuelve su id, en caso contrario, devuelve nulo
    }

}
