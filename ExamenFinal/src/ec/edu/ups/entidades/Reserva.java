package ec.edu.ups.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Restaurante restaurante;
	private int numeroPersonas;
	private String fecha;
	private String hora;
	
	public Reserva(int id, Cliente cliente, Restaurante restaurante, int numeroPersonas, String fecha, String hora) {
		this.id = id;
		this.cliente = cliente;
		this.restaurante = restaurante;
		this.numeroPersonas = numeroPersonas;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		    return true;
		if (obj == null)
		    return false;
		if (getClass() != obj.getClass())
		    return false;
		Reserva other = (Reserva) obj;
		if (id != other.id)
		    return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", cliente=" + cliente + ", restaurante=" + restaurante + ", numeroPersonas="
				+ numeroPersonas + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
	
}
