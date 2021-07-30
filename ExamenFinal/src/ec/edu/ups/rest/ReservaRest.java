package ec.edu.ups.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Reserva;

@Path("/reserva")
public class ReservaRest {

	@EJB
	ReservaFacade ejbReserva;
	@EJB
	ClienteFacade ejbCliente;
	@EJB
	RestauranteFacade ejbRestaurante;
	
	@POST
	@Path("/crearreserva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postReserva( @FormParam("numeroCedula") String numeroCedula, @FormParam("nombreRestaurante") String nombreRestaurante, @FormParam("numeroPersonas") int numeroPersonas, @FormParam("fecha") String fecha, @FormParam("hora") String hora) throws IOException {
		Reserva reser = new Reserva(0, ejbCliente.buscarPorCedula(numeroCedula), ejbRestaurante.buscarRestauranteNombre(nombreRestaurante), numeroPersonas, fecha, hora);
		ejbReserva.create(reser);
		Jsonb json = JsonbBuilder.create();
    	return Response.status(200).entity(json.toJson(reser)).build();    	
	}
	
	@GET
	@Path("/listarcliente/{cedula}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarreserva(@PathParam("cedula") String cedula) throws IOException {
		List<Reserva> res = ejbReserva.reservaCliente(cedula);
		Jsonb jsonb = JsonbBuilder.create();
		res = res.stream().sorted((a, b) -> a.getCliente().compareTo(b.getCliente())).collect(Collectors.toList());
		return Response.ok(jsonb.toJson(res)).build();
	}
	
	@GET
	@Path("/listarestaurante/{nombre}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarrestaurante(@PathParam("nombre") String nombre) throws IOException {
		List<Reserva> res = ejbReserva.reservaRestaurante(nombre);
		Jsonb jsonb = JsonbBuilder.create();
		res = res.stream().sorted((a, b) -> a.getRestaurante().compareTo(b.getRestaurante())).collect(Collectors.toList());
		return Response.ok(jsonb.toJson(res)).build();
	}
	
	
}
