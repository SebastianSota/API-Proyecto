package Pojos.Precio.Modelo;

import Pojos.ImagenPlatillo.Modelo.ImagenPlatillo;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrecioDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPrecio() throws SQLException {
        ArrayList<Precio> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM precio;");
            rs = ps.executeQuery();

            while(rs.next()){
                Precio precio = new Precio();
                PlatilloDao precioDao = new PlatilloDao();
                precio.setIdPrecio(rs.getInt(1));
                precio.setPrecio(rs.getDouble(2));
                precio.setIdPlatillo(precioDao.getIdPlatilloById(rs.getInt(2)));
                list.add(precio);
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

    public Precio getPrecioById(int id) throws SQLException{
        Precio precio = new Precio();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM precio WHERE idPrecio = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                PlatilloDao imagenPlatilloDao = new PlatilloDao();
                precio.setIdPrecio(rs.getInt(1));
                precio.setPrecio(rs.getDouble(2));
                precio.setIdPlatillo(imagenPlatilloDao.getIdPlatilloById(rs.getInt(3)));
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

        return precio;
    }

    public Precio createPrecio(Precio precio) throws SQLException{
        boolean flag = false;
        Precio precioInsert = new Precio();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO precio (`precio`,`idPlatillo`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,precio.getPrecio());
            ps.setInt(2,precio.getIdPlatillo().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        precioInsert = precio;
                        precioInsert.setIdPrecio(idRecovery);
                    }else{
                        throw new SQLException("FAIL PRECIO NOT CREATED");
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

        return precioInsert;
    }
    public boolean updatePrecio(Precio precio){
        boolean created = false;
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE precio SET `precio`= ?, `idPlatillo` = ? WHERE idPrecio = ?;",Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,precio.getPrecio());
            ps.setInt(2,precio.getIdPlatillo().getIdPlatillo());

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
