package mx.edu.utez.controllers;

import mx.edu.utez.areaingrediente.model.AreaIngrediente;
import mx.edu.utez.areaingrediente.model.AreaIngredienteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class ServiceAreaIngrediente {

    @GET
    @Path("/areas.ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    public List readAreaIngrediente() throws SQLException {
        AreaIngredienteDao areaIngredienteDao =  new AreaIngredienteDao();
        List list = areaIngredienteDao.getAreaIngrediente();

        return list;
    }

    @POST
    @Path("/areas.ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AreaIngrediente createAreaIngrediente(AreaIngrediente areaIngrediente) throws SQLException{
        AreaIngrediente areaIngredienteInsert = (new AreaIngredienteDao().createAreaIngrediente(areaIngrediente));

        return areaIngredienteInsert;
    }


}
