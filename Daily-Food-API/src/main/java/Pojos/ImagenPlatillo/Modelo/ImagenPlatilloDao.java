package Pojos.ImagenPlatillo.Modelo;

import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagenPlatilloDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getImagenPlatillo() throws SQLException {
        ArrayList<ImagenPlatillo> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM imagenPlatillo;");
            rs = ps.executeQuery();

            while(rs.next()){
                ImagenPlatillo imagenPlatillo = new ImagenPlatillo();
                PlatilloDao imagenPlatilloDao = new PlatilloDao();
                imagenPlatillo.setIdImagenPlatillo(rs.getInt(1));
                imagenPlatillo.setIdPlatillo(imagenPlatilloDao.getIdPlatilloById(rs.getInt(2)));
                imagenPlatillo.setImg(rs.getBlob(3));
                list.add(imagenPlatillo);
            }
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return list;
    }

    public ImagenPlatillo getimagenPlatilloById(int id) throws SQLException{
        ImagenPlatillo imagenPlatillo = new ImagenPlatillo();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM imagenPlatillo WHERE idImagenPlatillo = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                PlatilloDao imagenPlatilloDao = new PlatilloDao();
                imagenPlatillo.setIdImagenPlatillo(rs.getInt(1));
                imagenPlatillo.setIdPlatillo(imagenPlatilloDao.getIdPlatilloById(rs.getInt(2)));
                imagenPlatillo.setImg(rs.getBlob(3));
            }
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return imagenPlatillo;
    }

    public ImagenPlatillo createImagenPlatillo(ImagenPlatillo imagenPlatillo) throws SQLException{
        boolean flag = false;
        ImagenPlatillo imagenPlatilloInsert = new ImagenPlatillo();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO imagenPlatillo (`idPlatillo`,`img`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setBlob(1,imagenPlatillo.getImg());
            ps.setInt(2,imagenPlatillo.getIdPlatillo().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        imagenPlatilloInsert = imagenPlatillo;
                        imagenPlatilloInsert.setIdImagenPlatillo(idRecovery);
                    }else{
                        throw new SQLException("FAIL IMAGEN NOT CREATED");
                    }
                }
            }
        }catch(Exception e){
            System.err.println("ERROR created" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return imagenPlatilloInsert;
    }

    public boolean updateImagenPlatillo(ImagenPlatillo imagenPlatillo){
        boolean created = false;
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE imagenPlatillo SET `idPlatillo`= ?, `img` = ? WHERE idImagenPlatillo = ?;",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,imagenPlatillo.getIdPlatillo().getIdPlatillo());
            ps.setBlob(2,imagenPlatillo.getImg());

            created = ps.executeUpdate() == 1;
            if (created){
                con.commit();
            }else{
                con.rollback();
            }
        }catch(Exception e){
            System.err.println("ERROR created" + e.getMessage());
        }
        return created;
    }

}
