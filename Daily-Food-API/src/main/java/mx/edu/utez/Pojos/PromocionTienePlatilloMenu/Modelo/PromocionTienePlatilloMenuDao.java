package Pojos.PromocionTienePlatilloMenu.Modelo;

import Pojos.Preparacion.Modelo.Preparacion;
import Pojos.Promocion.Modelo.PromocionDao;
import Pojos.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocionTienePlatilloMenuDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPromocionTienePlatilloMenu() throws SQLException {
        ArrayList<PromocionTienePlatilloMenu> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM promocionTienePlatilloMenu;");
            rs = ps.executeQuery();

            while(rs.next()){
                PromocionTienePlatilloMenu promocionPlatillo = new PromocionTienePlatilloMenu();
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

    public PromocionTienePlatilloMenu getPromocionTienePlatilloMenuById(int id) throws SQLException{
        PromocionTienePlatilloMenu promocionPlatillo = new PromocionTienePlatilloMenu();

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

    public PromocionTienePlatilloMenu createPromocionTienePlatilloMenu(PromocionTienePlatilloMenu promocionTienePlatilloMenu) throws SQLException{
        boolean flag = false;
        PromocionTienePlatilloMenu promocionTienePlatilloMenuInsert = new PromocionTienePlatilloMenu();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO promocionTienePlatilloMenu (`idPromocion`,`idPlatillo`) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,promocionTienePlatilloMenu.getIdPromocion().getIdPromocion());
            ps.setInt(2,promocionTienePlatilloMenu.getIdPlatillo().getIdPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        promocionTienePlatilloMenuInsert = promocionTienePlatilloMenu;
                        promocionTienePlatilloMenuInsert.setIdPlatillo(idRecovery);
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

        return promocionTienePlatilloMenuInsert;
    }
    public boolean updatePromocionTienePlatilloMenu(PromocionTienePlatilloMenu promocionTienePlatilloMenu){
        boolean created = false;
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("UPDATE promocionTienePlatilloMenu SET `idPromocion`= ?, `idPromocion` = ? ;",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,promocionTienePlatilloMenu.getIdPromocion().getIdPromocion());
            ps.setInt(2,promocionTienePlatilloMenu.getIdPlatillo().getIdPlatillo());

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
