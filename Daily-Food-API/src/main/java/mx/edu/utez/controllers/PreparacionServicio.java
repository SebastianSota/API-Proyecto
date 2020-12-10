package mx.edu.utez.controllers;

import mx.edu.utez.preparacion.Modelo.Preparacion;
import mx.edu.utez.preparacion.Modelo.PreparacionDao;
import mx.edu.utez.Response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class PreparacionServicio {
    @GET
    @Path("/preparacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPreparacion() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PreparacionDao().getPreparacion());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PREPARACION OK");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PREPARACION");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/preparacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPreparacionById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Preparacion preparacion = (new PreparacionDao().getPreparacionById(id));
        if (preparacion.getIdPreparacion() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PREPARACION BY ID OK");
            response.setData(preparacion);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PREPARACION BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/preparacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPreparacion(Preparacion preparacion) throws SQLException {
        MyResponse response = new MyResponse();

        Preparacion preparacionInsert = (new PreparacionDao().createPreparacion(preparacion));
        if (preparacionInsert.getIdPreparacion() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PREPARACION CREATED");
            response.setData(preparacionInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PREPARACION NOT CREATED");
            response.setData(null);
        }

        return response;
    }
    @PUT
    @Path("/preparacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePreparacion(Preparacion preparacion) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag  = (new PreparacionDao().updatePreparacion(preparacion));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Preparacion update");
            response.setData(preparacion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Preparacion update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/preparacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePreparacion(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new PreparacionDao().deletePreparacion(id));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Preparacion Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Preparacion Delete");
        }

        return response;
    }
}
