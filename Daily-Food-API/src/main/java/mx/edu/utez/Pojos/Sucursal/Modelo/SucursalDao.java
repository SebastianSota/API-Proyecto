package Pojos.Sucursal.Modelo;

import Pojos.Preparacion.Modelo.Preparacion;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getSucursal() throws SQLException {
        ArrayList<Sucursal> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursal;");
            rs = ps.executeQuery();

            while(rs.next()){
                Sucursal sucursal = new Sucursal();
                DireccionDao direccionDao = new DireccionDao();
                sucursal.setIdSucursal(rs.getInt(1));
                sucursal.setNombreSucursal(rs.getString(2));
                sucursal.setIdDireccion(direccionDao.getIdDireccionById(rs.getInt(3)));
                list.add(sucursal);
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

    public Sucursal getSucursalById(int id) throws SQLException{
        Sucursal sucursal = new Sucursal();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM sucursal WHERE idSucursal = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                DireccionDao direccionDao = new DireccionDao();
                sucursal.setIdSucursal(rs.getInt(1));
                sucursal.setNombreSucursal(rs.getString(2));
                sucursal.setIdDireccion(direccionDao.getIdDireccionById(rs.getInt(3)));
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

        return sucursal;
    }

    public Sucursal createSucursal(Sucursal sucursal) throws SQLException{
        boolean flag = false;
        Sucursal sucursalInsert = new Sucursal();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO sucursal (`nombreSucursal`,`idDireccion`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,sucursal.getNombreSucursal());
            ps.setInt(2,sucursal.getIdDireccion().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        sucursalInsert = sucursal;
                        sucursalInsert.setIdSucursal(idRecovery);
                    }else{
                        throw new SQLException("FAIL PREPARACION NOT CREATED");
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

        return sucursalInsert;
    }
    public boolean updateSucursal(Sucursal sucursal){
        boolean created = false;
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE sucursal SET `nombreSucursal`= ?, `idDireccion` = ? WHERE idSucursal = ?;",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,sucursal.getNombreSucursal());
            ps.setInt(2,sucursal.getIdDireccion().getIdDireccion());

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
