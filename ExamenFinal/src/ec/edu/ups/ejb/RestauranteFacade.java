package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ups.entidades.Restaurante;

@Stateless
public class RestauranteFacade extends AbstractFacade<Restaurante>{

	@PersistenceContext(unitName = "ExamenFinal")
	private EntityManager em;

	public RestauranteFacade() {
        super(Restaurante.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Restaurante buscarRestauranteNombre(String nombre) {
    	Restaurante restaurante = null;
    	String consulta = "Select res from Restaurante res Where res.nombre=:nombre";
    	try {
    		restaurante = (Restaurante) em.createQuery(consulta).setParameter("nombre", nombre).getSingleResult();
    	}catch(Exception e) {
    		System.out.println(">>>Warning (RestauranteFacade:buscarRestauranteNombre: )"+e.getMessage());
    	}
    	return restaurante;
    }
}
