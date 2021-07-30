package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ups.entidades.Reserva;

@Stateless
public class ReservaFacade extends AbstractFacade<Reserva>{

	@PersistenceContext(unitName = "ExamenFinal")
	private EntityManager em;

	public ReservaFacade() {
        super(Reserva.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Reserva> reservaCliente(String cedula){
    	List<Reserva> reserva = new ArrayList<Reserva>();
    	String consulta = "Select res From Reserva res where res.cliente.cedula='"+cedula+"' order by res.fecha";
    	try {
			reserva = em.createQuery(consulta).getResultList();
		} catch (Exception e) {
    		System.out.println(">>>Warning (ReservaFacade:reservaFiltrada: )"+e.getMessage());
		}
    	return reserva;
    }
    
    public List<Reserva> reservaRestaurante(String nombre){
    	List<Reserva> reserva = new ArrayList<Reserva>();
    	String consulta = "Select res From Reserva res where res.restaurante.nombre='"+nombre+"' order by res.fecha";
    	try {
			reserva = em.createQuery(consulta).getResultList();
		} catch (Exception e) {
    		System.out.println(">>>Warning (ReservaFacade:reservaFiltrada: )"+e.getMessage());
		}
    	return reserva;
    }
}
