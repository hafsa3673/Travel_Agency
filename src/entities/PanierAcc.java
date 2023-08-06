package entities;

import java.sql.Date;

public class PanierAcc {
	private int id_panier_acc;
	private int id_Voyage_acc;
	private int id_client;
	private Date date;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId_panier_acc() {
		return id_panier_acc;
	}
	public void setId_panier_acc(int id_panier_acc) {
		this.id_panier_acc = id_panier_acc;
	}
	public int getId_Voyage_acc() {
		return id_Voyage_acc;
	}
	public void setId_Voyage_acc(int id_Voyage_acc) {
		this.id_Voyage_acc = id_Voyage_acc;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	public PanierAcc(int id_panier_acc, int id_Voyage_acc, int id_client, Date date) {
		super();
		this.id_panier_acc = id_panier_acc;
		this.id_Voyage_acc = id_Voyage_acc;
		this.id_client = id_client;
		this.date = date;
	}
	public PanierAcc(int id_Voyage_acc, int id_client, Date date) {
		super();
		this.id_Voyage_acc = id_Voyage_acc;
		this.id_client = id_client;
		this.date = date;
	}
	
	public PanierAcc() {}
	@Override
	public String toString() {
		return "PanierAcc [id_panier_acc=" + id_panier_acc + ", id_Voyage_acc=" + id_Voyage_acc + ", id_client="
				+ id_client + ", date=" + date + "]";
	}
	
	
}
