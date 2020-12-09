package Pojos.Promocion.Modelo;

import Pojos.Preparacion.Modelo.Preparacion;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocionDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPromocion() throws SQLException {
        ArrayList<Promocion> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocion;");
            rs = ps.executeQuery();

            while(rs.next()){
                Promocion promocion = new Promocion();
                promocion.setIdPromocion(rs.getInt(1));
                promocion.setDescripcion(rs.getString(2));
                promocion.setFechaInicio(rs.getDate(3));
                promocion.setFechaFin(rs.getDate(3));
                promocion.setStatus(rs.getInt(4));
                promocion.setPrecio(rs.getDouble(5));
                list.add(promocion);
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

    public Promocion getPromocionById(int id) throws SQLException{
        Promocion promocion = new Promocion();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocion WHERE idPromocion = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                promocion.setIdPromocion(rs.getInt(1));
                promocion.setDescripcion(rs.getString(2));
                promocion.setFechaInicio(rs.getDate(3));
                promocion.setFechaFin(rs.getDate(3));
                promocion.setStatus(rs.getInt(4));
                promocion.setPrecio(rs.getDouble(5));
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

        return promocion;
    }

    public Promocion createPromocion(Promocion promocion) throws SQLException{
        boolean flag = false;
        Promocion promocionInsert = new Promocion();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO promocion (`descripcion`,`fechaInicio`,`fechaFin`,`status`,`precio`) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,promocion.getDescripcion());
            ps.setDate(2,promocion.getFechaInicio());
            ps.setDate(4,promocion.getFechaFin());
            ps.setInt(5,promocion.getStatus());
            ps.setDouble(6,promocion.getPrecio());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        promocionInsert = promocion;
                        promocionInsert.setIdPromocion(idRecovery);
                    }else{
                        throw new SQLException("FAIL PROMOCION NOT CREATED");
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

        return promocionInsert;
    }
    public boolean updatePromocion(Promocion promocion) throws SQLException{
        boolean flag = false;
        con = null;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE promocion SET `descripcion` = ?,`fechaInicio` = ?,`fechaFin` = ?, `status` = ?, `precio` = ?  WHERE idPromocion = ?;");
            ps.setString(1,promocion.getDescripcion());
            ps.setDate(2,promocion.getFechaInicio());
            ps.setDate(3,promocion.getFechaFin());
            ps.setInt(4,promocion.getStatus());
            ps.setDouble(5,promocion.getPrecio());

            flag = (ps.executeUpdate() == 1);
            con.commit();
        }catch(Exception e){
            System.err.println("ERROR update" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }

    public boolean deletePromocion(int id) throws  SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps  = con.prepareStatement("DELETE FROM promocion WHERE idPromocion = ?");
            ps.setInt(1,id);
            flag = ps.executeUpdate() == 1;
            con.commit();
        }catch(Exception e){
            System.err.println("ERROR delete" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }

        return flag;
    }
}
