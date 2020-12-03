package Pojos.Preparacion.Modelo;

import Pojos.Precio.Modelo.Precio;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparacionDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPreparacion() throws SQLException {
        ArrayList<Preparacion> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM preparacion;");
            rs = ps.executeQuery();

            while(rs.next()){
                Preparacion preparacion = new Preparacion();
                PlatilloDao precioDao = new PlatilloDao();
                preparacion.setIdPreparacion(rs.getInt(1));
                preparacion.setDescripcion(rs.getString(2));
                preparacion.setIdPlatillo(precioDao.getIdPlatilloById(rs.getInt(2)));
                list.add(preparacion);
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

    public Preparacion getPreparacionById(int id) throws SQLException{
        Preparacion preparacion = new Preparacion();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM preparacion WHERE idPreparacion = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                PlatilloDao precioDao = new PlatilloDao();
                preparacion.setIdPreparacion(rs.getInt(1));
                preparacion.setDescripcion(rs.getString(2));
                preparacion.setIdPlatillo(precioDao.getIdPlatilloById(rs.getInt(3)));
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

        return preparacion;
    }

    public Preparacion createPreparacion(Preparacion preparacion) throws SQLException{
        boolean flag = false;
        Preparacion preparacionInsert = new Preparacion();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO preparacion (`descripcion`,`idPlatillo`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,preparacion.getDescripcion());
            ps.setInt(2,preparacion.getIdPlatillo().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        preparacionInsert = preparacion;
                        preparacionInsert.setIdPreparacion(idRecovery);
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

        return preparacionInsert;
    }
    public boolean updatePreparacion(Preparacion preparacion){
        boolean created = false;
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE preparacion SET `descripcion`= ?, `idPlatillo` = ? WHERE idPreparacion = ?;",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,preparacion.getDescripcion());
            ps.setInt(2,preparacion.getIdPlatillo().getIdPlatillo());

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
