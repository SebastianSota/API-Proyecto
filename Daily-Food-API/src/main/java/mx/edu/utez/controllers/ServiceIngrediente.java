package mx.edu.utez.controllers;

import mx.edu.utez.ingrediente.model.Ingrediente;
import mx.edu.utez.ingrediente.model.IngredienteDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Path("/daily")
public class ServiceIngrediente {

    @GET
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    public List readIngrediente() throws SQLException {
        IngredienteDao ingredienteDao = new IngredienteDao();
        List<Ingrediente> list = (ingredienteDao.getIngrediente());

        return list;


    }

    @GET
    @Path("/ingredientes/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingrediente readIngredienteById(@PathParam("code") int code) throws SQLException{
        Ingrediente ingrediente = (new IngredienteDao().getIngredienteById(code));

        return ingrediente;
    }

    @POST
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Ingrediente createIngrediente(Ingrediente ingrediente) throws SQLException{
        Ingrediente ingredienteInsert = (new IngredienteDao().createIngrediente(ingrediente));

        return ingredienteInsert;
    }



}
