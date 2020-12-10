package Pojos.Servicios;

import Pojos.Preparacion.Modelo.Preparacion;
import Pojos.Preparacion.Modelo.PreparacionDao;
import Pojos.Promocion.Modelo.Promocion;
import Pojos.Promocion.Modelo.PromocionDao;
import Pojos.Response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class PromocionServicio {
    @GET
    @Path("/promocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPromocion() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PromocionDao().getPromocion());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PROMOCION OK");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PROMOCION");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/promocion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPromocionById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        Promocion promocion = (new PromocionDao().getPromocionById(id));
        if (promocion.getIdPromocion() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PROMOCION BY ID OK");
            response.setData(promocion);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PROMOCION BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/promocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPromocion(Promocion promocion) throws SQLException {
        MyResponse response = new MyResponse();

        Promocion promocionInsert = (new PromocionDao().createPromocion(promocion));
        if (promocionInsert.getIdPromocion() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PROMOCION CREATED");
            response.setData(promocionInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PROMOCION NOT CREATED");
            response.setData(null);
        }

        return response;
    }
    @PUT
    @Path("/promocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePromocion(Promocion promocion) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag  = (new PromocionDao().updatePromocion(promocion));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Promocion update");
            response.setData(promocion);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Promocion update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/promocion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePromocion(@PathParam("id") int id) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new PromocionDao().deletePromocion(id));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Promocion Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Promocion Delete");
        }

        return response;
    }
}
