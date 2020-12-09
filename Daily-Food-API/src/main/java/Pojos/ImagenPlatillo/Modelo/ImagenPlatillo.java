package Pojos.ImagenPlatillo.Modelo;

import Pojos.Platillo.Modelo.Platillo;

import java.sql.Blob;

public class ImagenPlatillo {

    private int idImagenPlatillo;
    private Platillo idPlatillo;
    private Blob img;

    public int getIdImagenPlatillo() {
        return idImagenPlatillo;
    }

    public void setIdImagenPlatillo(int idImagenPlatillo) {
        this.idImagenPlatillo = idImagenPlatillo;
    }

    public Platillo getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Platillo idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
