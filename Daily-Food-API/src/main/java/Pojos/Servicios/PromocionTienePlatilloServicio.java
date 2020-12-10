package Pojos.Servicios;

import Pojos.Promocion.Modelo.Promocion;
import Pojos.Promocion.Modelo.PromocionDao;
import Pojos.PromocionTienePlatillo.Modelo.PromocionTienePlatillo;
import Pojos.PromocionTienePlatillo.Modelo.PromocionTienePlatilloDao;
import Pojos.Response.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/daily")
public class PromocionTienePlatilloServicio {
    @GET
    @Path("/promocion.tiene.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPromocionTienePlatillo() throws SQLException {
        MyResponse response = new MyResponse();
        List list = (new PromocionTienePlatilloDao().getPromocionTienePlatillo());

        if (list.size() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PROMOCION_TIENE_PLATILLO OK");
            response.setData(list);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PROMOCION_TIENE_PLATILLO");
            response.setData(null);
        }
        return response;
    }

    @GET
    @Path("/promocion.tiene.platillo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse getPromocionTienePlatilloById(@PathParam("id") int id) throws SQLException {
        MyResponse response = new MyResponse();

        PromocionTienePlatillo promocionTienePlatillo = (new PromocionTienePlatilloDao().getPromocionTienePlatilloById(id));
        if (promocionTienePlatillo.getIdPlatillo().getIdPlatillo() > 0 && promocionTienePlatillo.getIdPromocion().getIdPromocion() > 0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("READ PROMOCION_TIENE_PLATILLO BY ID OK");
            response.setData(promocionTienePlatillo);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("ERROR READ PROMOCION_TIENE_PLATILLO BY ID");
            response.setData(null);
        }

        return response;
    }

    @POST
    @Path("/promocion.tiene.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse createPromocionTienePlatillo(PromocionTienePlatillo promocionTienePlatillo) throws SQLException {
        MyResponse response = new MyResponse();

        PromocionTienePlatillo promocionTienePlatilloInsert = (new PromocionTienePlatilloDao().createPromocionTienePlatillo(promocionTienePlatillo));
        if (promocionTienePlatilloInsert.getIdPromocion().getIdPromocion() > 0 && promocionTienePlatillo.getIdPlatillo().getIdPlatillo()>0) {
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("PROMOCION_TIENE_PLATILLO CREATED");
            response.setData(promocionTienePlatilloInsert);
        } else {
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("PROMOCION_TIENE_PLATILLO NOT CREATED");
            response.setData(null);
        }

        return response;
    }
    @PUT
    @Path("/promocion.tiene.platillo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MyResponse updatePromocionTienePlatillo(PromocionTienePlatillo promocionTienePlatillo) throws SQLException{
        MyResponse response = new MyResponse();

        boolean flag  = (new PromocionTienePlatilloDao().updatePromocionTienePlatillo(promocionTienePlatillo));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Promocion_tiene_platillo update");
            response.setData(promocionTienePlatillo);
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Promocion_tiene_platillo update");
            response.setData(null);
        }

        return response;
    }

    @DELETE
    @Path("/promocion.tiene.platillo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse deletePromocionTienePromocion(@PathParam("id") int id, int id2) throws SQLException{
        MyResponse response = new MyResponse();
        boolean flag = (new PromocionTienePlatilloDao().deletePromocionTienePlatillo(id, id2));
        if(flag){
            response.setCode(200);
            response.setStatus("success");
            response.setMessage("Promocion_Tiene_Platillo Delete");
        }else{
            response.setCode(400);
            response.setStatus("error");
            response.setMessage("Error Promocion_Tiene_Platillo Delete");
        }

        return response;
    }
}
