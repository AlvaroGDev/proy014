    package es.cic.curso25.proy014.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "razon_multa")
    private String razonMulta; 
    private double importe; 
    private String fecha; 

    @ManyToOne
    @JsonIgnore
    private Vehiculo vehiculo; // Relación con el coche

    // Constructores
    public Multa() {
    }

    public Multa(String razonMulta, double importe, String fecha, Vehiculo vehiculo) {
        this.razonMulta = razonMulta;
        this.importe = importe;
        this.fecha = fecha;
        this.vehiculo = vehiculo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonMulta() {
        return razonMulta;
    }

    public void setRazonMulta(String razonMulta) {
        this.razonMulta = razonMulta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setCoche(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}