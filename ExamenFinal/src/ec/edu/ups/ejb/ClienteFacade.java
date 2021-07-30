package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.edu.ups.entidades.Cliente;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

	@PersistenceContext(unitName = "ExamenFinal")
	private EntityManager em;

	public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Cliente buscarPorCedula (String cedula) {
    	Cliente cliente = null;
    	String consulta = "Select cli From Cliente cli Where cli.cedula=:cedula";
    	try {
    		cliente = (Cliente) em.createQuery(consulta).setParameter("cedula", cedula).getSingleResult();
    	}catch(Exception e) {
    		System.out.println(">>>Warning (ClienteFacade:buscarPorCedula: )"+e.getMessage());
    	}
    	return cliente ;
    }
}
