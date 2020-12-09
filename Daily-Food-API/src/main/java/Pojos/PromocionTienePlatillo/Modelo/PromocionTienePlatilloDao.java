package Pojos.PromocionTienePlatillo.Modelo;

import Pojos.Promocion.Modelo.Promocion;
import Pojos.Promocion.Modelo.PromocionDao;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PromocionTienePlatilloDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPromocionTienePlatillo() throws SQLException {
        ArrayList<PromocionTienePlatillo> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocionTienePlatilloMenu;");
            rs = ps.executeQuery();

            while(rs.next()){
                PromocionTienePlatillo promocionPlatillo = new PromocionTienePlatillo();
                PlatilloDao promocionPlatilloDao = new PlatilloDao();
                PromocionDao promocionDao = new PromocionDao();
                promocionPlatillo.setIdPromocion(promocionDao.getPromocionById(rs.getInt(1)));
                promocionPlatillo.setIdPlatillo(promocionPlatilloDao.getIdPlatilloById(rs.getInt(2)));
                list.add(promocionPlatillo);
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

    public PromocionTienePlatillo getPromocionTienePlatilloById(int id) throws SQLException{
        PromocionTienePlatillo promocionPlatillo = new PromocionTienePlatillo();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocionTienePlatilloMenu WHERE idPlatillo = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                PlatilloDao promocionPlatilloDao = new PlatilloDao();
                PromocionDao promocionDao = new PromocionDao();
                promocionPlatillo.setIdPromocion(promocionDao.getPromocionById(rs.getInt(1)));
                promocionPlatillo.setIdPlatillo(promocionPlatilloDao.getIdPlatilloById(rs.getInt(2)));
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

        return promocionPlatillo;
    }

    public PromocionTienePlatillo createPromocionTienePlatillo(PromocionTienePlatillo promocionTienePlatillo) throws SQLException{
        boolean flag = false;
        PromocionTienePlatillo promocionTienePlatilloInsert = new PromocionTienePlatillo();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO promocionTienePlatilloMenu (`idPromocion`,`idPlatillo`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, promocionTienePlatillo.getIdPromocion().getIdPromocion());
            ps.setInt(2, promocionTienePlatillo.getIdPlatillo().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        promocionTienePlatilloInsert = promocionTienePlatillo;
                        promocionTienePlatilloInsert.setIdPlatillo(idRecovery);
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

        return promocionTienePlatilloInsert;
    }
    public boolean updatePromocionTienePlatillo(PromocionTienePlatillo promocionTienePlatillo) throws SQLException{
        boolean flag = false;
        con = null;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE promocionTienePlatillo SET `idPromocion` = ?,`idPlatillo` = ? WHERE idPromocion = ?;");
            ps.setInt(1,promocionTienePlatillo.getIdPromocion().getIdPromocion());
            ps.setInt(2,promocionTienePlatillo.getIdPlatillo().getIdPlatillo());
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

    public boolean deletePromocionTienePlatillo(int id, int id2) throws  SQLException {
        boolean flag = false;

        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps  = con.prepareStatement("DELETE FROM promocionTienePlatillo WHERE idPromocion = ? AND idPlatillo = ?");
            ps.setInt(1,id);
            ps.setInt(2,id2);
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
