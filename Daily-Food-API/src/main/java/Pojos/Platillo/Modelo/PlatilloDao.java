package Pojos.Platillo.Modelo;

import Pojos.tools.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatilloDao {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getPlatillo() throws SQLException {
        ArrayList<Platillo> list = new ArrayList();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM platillo;");
            rs = ps.executeQuery();

            while(rs.next()){
                Platillo platillo = new Platillo();
                TipoPlatilloDao tipoPlatilloDao = new TipoPlatilloDao();
                platillo.setIdPlatillo(rs.getInt(1));
                platillo.setNombrePlatillo(rs.getString(2));
                platillo.setTiempoPreparacion(rs.getInt(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setIdTipoPlatillo(tipoPlatilloDao.getTipoPlatilloById(rs.getInt(3)));
                list.add(platillo);
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

    public Platillo getPlatilloById(int id) throws SQLException{
        Platillo platillo = new Platillo();

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM platillo WHERE idPlatillo = ?;");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                TipoPlatilloDao tipoPlatillo = new TipoPlatilloDao();
                platillo.setIdPlatillo(rs.getInt(1));
                platillo.setNombrePlatillo(rs.getString(2));
                platillo.setTiempoPreparacion(rs.getInt(3));
                platillo.setDescripcion(rs.getString(4));
                platillo.setIdTipoPlatillo(tipoPlatillo.getTipoPlatilloById(rs.getInt(5)));
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

        return platillo;
    }

    public Platillo createIngrediente(Platillo platillo) throws SQLException{
        boolean flag = false;
        Platillo platilloInsert = new Platillo();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO platillo (`nombrePlatillo`,`tiempoPreparacion`, `descripcion`, `idTipoPlarillo`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,platillo.getNombrePlatillo());
            ps.setInt(2,platillo.getTiempoPreparacion());
            ps.setString(3, platillo.getDescripcion());
            ps.setInt(4,platillo.getIdTipoPlatillo().getIdTipoPlatillo());

            flag = (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet generateKeys = ps.getGeneratedKeys()){
                    if(generateKeys.next()){
                        int idRecovery = generateKeys.getInt(1);
                        platilloInsert = platillo;
                        platilloInsert.setIdPlatillo(idRecovery);
                    }else{
                        throw new SQLException("FAIL INGREDIENTE NOT CREATED");
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

        return platilloInsert;
    }
