package mx.edu.utez.platillo.modelo;

public class Platillo {

    private int idPlatillo;
    private String nombrePlatillo;
    private int tiempoPreparacion;
    private String descripcion;
    private int idTipoPlatillo;

    public Platillo() {
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoPlatillo() {
        return idTipoPlatillo;
    }

    public void setIdTipoPlatillo(int idTipoPlatillo) {
        this.idTipoPlatillo = idTipoPlatillo;
    }
}
