package entities;

import java.sql.Date;

public class Panier {
	private int id_panier;
	private int id_Voyage;
	private int id_client;
	private String destination;
	private String image;
	private String type;
	private int id_hotel;
	private int id_Voyage_acc;
	private Date date;
	
	
	public int getId_Voyage_acc() {
		return id_Voyage_acc;
	}
	public void setId_Voyage_acc(int id_Voyage_acc) {
		this.id_Voyage_acc = id_Voyage_acc;
	}
	public int getId_panier() {
		return id_panier;
	}
	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}
	public int getId_Voyage() {
		return id_Voyage;
	}
	public void setId_Voyage(int id_Voyage) {
		this.id_Voyage = id_Voyage;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Panier(int id_panier, int id_Voyage, int id_client, String destination, String image, String type,
			int id_hotel, int id_Voyage_acc, Date date) {
		super();
		this.id_panier = id_panier;
		this.id_Voyage = id_Voyage;
		this.id_client = id_client;
		this.destination = destination;
		this.image = image;
		this.type = type;
		this.id_hotel = id_hotel;
		this.id_Voyage_acc = id_Voyage_acc;
		this.date = date;
	}
	public Panier(int id_Voyage, int id_client, String destination, String image, String type, int id_hotel,
			int id_Voyage_acc, Date date) {
		super();
		this.id_Voyage = id_Voyage;
		this.id_client = id_client;
		this.destination = destination;
		this.image = image;
		this.type = type;
		this.id_hotel = id_hotel;
		this.id_Voyage_acc = id_Voyage_acc;
		this.date = date;
	}
	
	public Panier(int id_Voyage, int id_client, int id_hotel) {
		super();
		this.id_Voyage = id_Voyage;
		this.id_client = id_client;
		this.id_hotel = id_hotel;
	}
	public Panier() {}
	
	public Panier(int id_Voyage, int id_client, int id_hotel, Date date) {
		super();
		this.id_Voyage = id_Voyage;
		this.id_client = id_client;
		this.id_hotel = id_hotel;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Panier [id_panier=" + id_panier + ", id_Voyage=" + id_Voyage + ", id_client=" + id_client
				+ ", destination=" + destination + ", image=" + image + ", type=" + type + ", id_hotel=" + id_hotel
				+ ", id_Voyage_acc=" + id_Voyage_acc + ", date=" + date + "]";
	}
	
	
	
}
