package mx.edu.utez.promocionTienePlatillo.Modelo;

import mx.edu.utez.platillo.modelo.Platillo;
import mx.edu.utez.promocion.Modelo.Promocion;

public class PromocionTienePlatillo {

    private Promocion idPromocion;
    private Platillo idPlatillo;

    public Promocion getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Promocion idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }
}
