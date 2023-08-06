	package entities;

import java.io.InputStream;
import java.io.Serializable;

public class Voyage_acc implements Serializable {
	private int id_Voyage_acc;
	private String destination_acc;
	private String continent_acc;
	private String type_acc;
	private String date_acc;
	private int duree_acc;
	
	private InputStream image_acc;
	 private String base64Image;
	private String theme_acc;
	private String hebergement_acc;
    private double prix_acc;

    private String activite;
    private String genre;
    private String guide;
    
    public Voyage_acc () {}

	public int getId_Voyage_acc() {
		return id_Voyage_acc;
	}

	public void setId_Voyage_acc(int id_Voyage_acc) {
		this.id_Voyage_acc = id_Voyage_acc;
	}

	public String getDestination_acc() {
		return destination_acc;
	}

	public void setDestination_acc(String destination_acc) {
		this.destination_acc = destination_acc;
	}

	public String getContinent_acc() {
		return continent_acc;
	}

	public void setContinent_acc(String continent_acc) {
		this.continent_acc = continent_acc;
	}

	public String getType_acc() {
		return type_acc;
	}

	public void setType_acc(String type_acc) {
		this.type_acc = type_acc;
	}

	public String getDate_acc() {
		return date_acc;
	}

	public void setDate_acc(String date_acc) {
		this.date_acc = date_acc;
	}

	public int getDuree_acc() {
		return duree_acc;
	}

	public void setDuree_acc(int duree_acc) {
		this.duree_acc = duree_acc;
	}

	public InputStream getImage_acc() {
		return image_acc;
	}

	public void setImage_acc(InputStream image_acc) {
		this.image_acc = image_acc;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getTheme_acc() {
		return theme_acc;
	}

	public void setTheme_acc(String theme_acc) {
		this.theme_acc = theme_acc;
	}

	public String getHebergement_acc() {
		return hebergement_acc;
	}

	public void setHebergement_acc(String hebergement_acc) {
		this.hebergement_acc = hebergement_acc;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public double getPrix_acc() {
		return prix_acc;
	}

	public void setPrix_acc(double prix_acc) {
		this.prix_acc = prix_acc;
	}

	public Voyage_acc(int id_Voyage_acc, String destination_acc, String continent_acc, String type_acc, String date_acc,
			int duree_acc, InputStream image_acc, String base64Image, String theme_acc, String hebergement_acc,
			String activite, String genre, String guide, double prix_acc) {
		super();
		this.id_Voyage_acc = id_Voyage_acc;
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.image_acc = image_acc;
		this.base64Image = base64Image;
		this.theme_acc = theme_acc;
		this.hebergement_acc = hebergement_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
		this.prix_acc = prix_acc;
	}

	public Voyage_acc(String destination_acc, String continent_acc, String type_acc, String date_acc, int duree_acc,
			InputStream image_acc, String base64Image, String theme_acc, String hebergement_acc, String activite,
			String genre, String guide, double prix_acc) {
		super();
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.image_acc = image_acc;
		this.base64Image = base64Image;
		this.theme_acc = theme_acc;
		this.hebergement_acc = hebergement_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
		this.prix_acc = prix_acc;
	}

	public Voyage_acc(String destination_acc, String continent_acc, String type_acc, String date_acc, int duree_acc,
			InputStream image_acc, String theme_acc, String hebergement_acc, String activite, String genre,
			String guide, double prix_acc) {
		super();
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.image_acc = image_acc;
		this.theme_acc = theme_acc;
		this.hebergement_acc = hebergement_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
		this.prix_acc = prix_acc;
	}

	

	public Voyage_acc(String destination_acc, String continent_acc, String type_acc, String date_acc, int duree_acc,
			InputStream image_acc, String theme_acc, String hebergement_acc, double prix_acc, String activite,
			String genre, String guide) {
		super();
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.image_acc = image_acc;
		this.theme_acc = theme_acc;
		this.hebergement_acc = hebergement_acc;
		this.prix_acc = prix_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
	}

	@Override
	public String toString() {
		return "Voyage_acc [id_Voyage_acc=" + id_Voyage_acc + ", destination_acc=" + destination_acc
				+ ", continent_acc=" + continent_acc + ", type_acc=" + type_acc + ", date_acc=" + date_acc
				+ ", duree_acc=" + duree_acc + ", image_acc=" + image_acc + ", base64Image=" + base64Image
				+ ", theme_acc=" + theme_acc + ", hebergement_acc=" + hebergement_acc + ", activite=" + activite
				+ ", genre=" + genre + ", guide=" + guide + ", prix_acc=" + prix_acc + "]";
	}

	public Voyage_acc(int id_Voyage_acc, String destination_acc, String continent_acc, String type_acc, String date_acc,
			int duree_acc, String theme_acc, String hebergement_acc, double prix_acc, String activite, String genre,
			String guide) {
		super();
		this.id_Voyage_acc = id_Voyage_acc;
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.theme_acc = theme_acc;
		this.hebergement_acc = hebergement_acc;
		this.prix_acc = prix_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
	}

	

	public Voyage_acc(int id_Voyage_acc, String destination_acc, String continent_acc, String type_acc, String date_acc,
			int duree_acc, String hebergement_acc, double prix_acc, String activite, String genre, String guide) {
		super();
		this.id_Voyage_acc = id_Voyage_acc;
		this.destination_acc = destination_acc;
		this.continent_acc = continent_acc;
		this.type_acc = type_acc;
		this.date_acc = date_acc;
		this.duree_acc = duree_acc;
		this.hebergement_acc = hebergement_acc;
		this.prix_acc = prix_acc;
		this.activite = activite;
		this.genre = genre;
		this.guide = guide;
	}
	
	
    
}

