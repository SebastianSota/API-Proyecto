package Pojos.Servicios;

import Pojos.Precio.Modelo.Precio;
import Pojos.Precio.Modelo.PrecioDao;
import Pojos.Response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class PrecioServicio {

    @GET
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPrecio() throws SQLException{
        MyResponse response = new MyResponse();
        List list = (new PrecioDao().getPrecio());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PEDIDOS OK");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PEDIDOS");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/precio/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPrecioById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Precio precio = (new PrecioDao().getPrecioById(id));
        if (precio.getIdPrecio() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PRECIO BY ID OK");
            response.setData(precio);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PRECIO BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPrecio(Precio precio) throws SQLException {
        MyResponse response = new MyResponse();

        Precio precioInsert = (new PrecioDao().createPrecio(precio));
        if (precioInsert.getIdPrecio() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PRECIO CREATED");
            response.setData(precioInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PRECIO NOT CREATED");
            response.setData(null);
        }

        return response;
    }
    @PUT
    @Path("/precio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePrecio(Precio precio) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag  = (new PrecioDao().updatePrecio(precio));

        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Precio update");
            response.setData(precio);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Precio update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/precio/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePrecio(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new PrecioDao().deletePrecio(id));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Precio Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Precio Delete");
        }

        return response;
    }


}
