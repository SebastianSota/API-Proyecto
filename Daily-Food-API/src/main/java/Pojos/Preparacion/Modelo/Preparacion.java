package Pojos.Preparacion.Modelo;

import Pojos.Platillo.Modelo.Platillo;

public class Preparacion {

    private int idPreparacion;
    private String descripcion;
    private Platillo idPlatillo;

    public int getIdPreparacion() {
        return idPreparacion;
    }

    public void setIdPreparacion(int idPreparacion) {
        this.idPreparacion = idPreparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }
}
