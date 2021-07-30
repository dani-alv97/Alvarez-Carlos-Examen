package ec.edu.ups.rest;

import java.io.IOException;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Restaurante;

@Path("/restaurante/")
public class RestauranteRest {

	@EJB
	private RestauranteFacade ejbRestaurante;
	
	@POST
    @Path("/registrarestaurante")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response post(@FormParam("nombre") String nombre, @FormParam("direccion") String direccion, @FormParam("telefono") String telefono, @FormParam("aforomax") int aforoMax) throws IOException {		
    	Restaurante res = new Restaurante(0, nombre, direccion, telefono, aforoMax);
    	ejbRestaurante.create(res);
    	Jsonb json = JsonbBuilder.create();
    	return Response.status(200).entity(json.toJson(res)).build();    	
    }
}
