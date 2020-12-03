package mx.edu.utez.areaingrediente.model;

import mx.edu.utez.tools.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaIngredienteDao {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public List getAreaIngrediente() throws SQLException{
        ArrayList<AreaIngrediente> list = new ArrayList();

        try{
            //Con hace referencia a la conexión
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM areaingrediente ORDER BY idAreaIngrediente");
            rs =ps.executeQuery();

            while(rs.next()){
                AreaIngrediente areaIngrediente = new AreaIngrediente();
                areaIngrediente.setId(rs.getInt(1));
                areaIngrediente.setNombreArea(rs.getString(2));
                list.add(areaIngrediente);
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }
        return list;
    }


    public AreaIngrediente getAreaIngredienteById(int id) throws SQLException{
        AreaIngrediente areaIngrediente =  new AreaIngrediente();;

        try{
            con = ConnectionDB.getConnection();
            ps = con.prepareStatement("SELECT * FROM areaingrediente WHERE idAreaIngrediente = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                areaIngrediente.setId(rs.getInt(1));
                areaIngrediente.setNombreArea(rs.getString(2));
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }


        return areaIngrediente;
    }


    public AreaIngrediente createAreaIngrediente(AreaIngrediente areaIngrediente) throws SQLException{
        boolean flag = false;
        AreaIngrediente areaIngredienteInsert = new AreaIngrediente();
        con = null;
        try{
            con = ConnectionDB.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO  areaingrediente(`nombreArea`) VALUES (?);", Statement.RETURN_GENERATED_KEYS); //Solo sirve cuando el un entero
            ps.setString(1,areaIngrediente.getNombreArea());

            flag =  (ps.executeUpdate() == 1);

            if(flag){
                con.commit();

                try(ResultSet  generetadKeys = ps.getGeneratedKeys()){
                    if(generetadKeys.next()){
                        int idRecobery = generetadKeys.getInt(1);
                        areaIngredienteInsert = areaIngrediente;
                        areaIngredienteInsert.setId(idRecobery);

                    }else{
                        throw new SQLException("FAIL Area Ingrediente NOT CREATED");
                    }
                }
            }

        }catch(Exception e){
            System.err.println("Error" + e.getMessage());
            con.rollback();
        }finally{
            if(con!=null) con.close();
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
        }


        return areaIngredienteInsert;
    }

}
