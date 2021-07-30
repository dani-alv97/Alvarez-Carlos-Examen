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
import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.entidades.Cliente;

@Path("/cliente/")
public class ClienteRest {

	@EJB
	private ClienteFacade ejbCliente;
	
	@POST
    @Path("/registrarcliente")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public Response post(@FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("cedula") String cedula, 
			@FormParam("direccion") String direccion, @FormParam("telefono") String telefono, @FormParam("correo") String correo) throws IOException {		
    	Cliente cli = new Cliente(0, nombre, apellido, cedula, direccion, telefono, correo);
    	ejbCliente.create(cli);
    	Jsonb json = JsonbBuilder.create();
    	return Response.status(200).entity(json.toJson(cli)).build();    	
    }
}
