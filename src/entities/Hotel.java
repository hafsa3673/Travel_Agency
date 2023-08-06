package entities;

import java.io.InputStream;

public class Hotel {
	private int id_hotel;
    private String nom_hotel;
    private double prix_hotel;
    private int etoile_hotel;
    private int nbr_chambre;
    private int nbr_nuit;
    private String inclusivite_hotel;
    private int id_clt;
    private String ville;
    private InputStream image_hotel;
    private String base64Image;
    public Hotel(){}

	
	
	public Hotel(String nom_hotel, double prix_hotel, int etoile_hotel, String ville, InputStream image_hotel,
			String base64Image) {
		super();
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.ville = ville;
		this.image_hotel = image_hotel;
		this.base64Image = base64Image;
	}



	public Hotel(int id_hotel, String nom_hotel, double prix_hotel, int etoile_hotel, int nbr_chambre, int nbr_nuit,
			String inclusivite_hotel, int id_clt, String ville, InputStream image_hotel) {
		super();
		this.id_hotel = id_hotel;
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.nbr_chambre = nbr_chambre;
		this.nbr_nuit = nbr_nuit;
		this.inclusivite_hotel = inclusivite_hotel;
		this.id_clt = id_clt;
		this.ville = ville;
		this.image_hotel = image_hotel;
	}




	public Hotel(String nom_hotel, double prix_hotel, int etoile_hotel, String ville, InputStream image_hotel) {
		super();
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.ville = ville;
		this.image_hotel = image_hotel;
	}



	
	public Hotel(int id_hotel, String nom_hotel, double prix_hotel, int etoile_hotel, String inclusivite_hotel,
			String ville, InputStream image_hotel) {
		super();
		this.id_hotel = id_hotel;
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.inclusivite_hotel = inclusivite_hotel;
		this.ville = ville;
		this.image_hotel = image_hotel;
	}



	

	public Hotel( String nom_hotel, double prix_hotel, int etoile_hotel, int nbr_chambre, int nbr_nuit,
			String inclusivite_hotel, int id_clt,String ville) {
		super();
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.nbr_chambre = nbr_chambre;
		this.nbr_nuit = nbr_nuit;
		this.inclusivite_hotel = inclusivite_hotel;
		this.id_clt = id_clt;
		this.ville= ville;
	}

	public Hotel(int id_hotel, String nom_hotel, double prix_hotel, int etoile_hotel, String ville) {
		super();
		this.id_hotel = id_hotel;
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.ville = ville;
	}

	public Hotel( String nom_hotel, double prix_hotel, int etoile_hotel, int nbr_chambre, int nbr_nuit,
			String inclusivite_hotel,String ville) {
		super();
		this.nom_hotel = nom_hotel;
		this.prix_hotel = prix_hotel;
		this.etoile_hotel = etoile_hotel;
		this.nbr_chambre = nbr_chambre;
		this.nbr_nuit = nbr_nuit;
		this.inclusivite_hotel = inclusivite_hotel;
		this.ville = ville;
	}


	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getNom_hotel() {
		return nom_hotel;
	}

	public void setNom_hotel(String nom_hotel) {
		this.nom_hotel = nom_hotel;
	}

	public double getPrix_hotel() {
		return prix_hotel;
	}

	public void setPrix_hotel(double prix_hotel) {
		this.prix_hotel = prix_hotel;
	}

	public int getEtoile_hotel() {
		return etoile_hotel;
	}

	public void setEtoile_hotel(int etoile_hotel) {
		this.etoile_hotel = etoile_hotel;
	}

	public int getNbr_chambre() {
		return nbr_chambre;
	}

	public void setNbr_chambre(int nbr_chambre) {
		this.nbr_chambre = nbr_chambre;
	}

	public int getNbr_nuit() {
		return nbr_nuit;
	}

	public void setNbr_nuit(int nbr_nuit) {
		this.nbr_nuit = nbr_nuit;
	}

	public String getInclusivite_hotel() {
		return inclusivite_hotel;
	}

	public void setInclusivite_hotel(String inclusivite_hotel) {
		this.inclusivite_hotel = inclusivite_hotel;
	}

	public int getId_clt() {
		return id_clt;
	}

	public void setId_clt(int id_clt) {
		this.id_clt = id_clt;
	}



	public String getBase64Image() {
		return base64Image;
	}



	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}



	public InputStream getImage_hotel() {
		return image_hotel;
	}



	public void setImage_hotel(InputStream image_hotel) {
		this.image_hotel = image_hotel;
	}



	@Override
	public String toString() {
		return "Hotel [id_hotel=" + id_hotel + ", nom_hotel=" + nom_hotel + ", prix_hotel=" + prix_hotel
				+ ", etoile_hotel=" + etoile_hotel + ", nbr_chambre=" + nbr_chambre + ", nbr_nuit=" + nbr_nuit
				+ ", inclusivite_hotel=" + inclusivite_hotel + ", id_clt=" + id_clt + ", ville=" + ville
				+ ", image_hotel=" + image_hotel + "]";
	}

	
	
}
