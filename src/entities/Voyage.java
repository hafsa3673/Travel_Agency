package entities;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;

	public class Voyage implements Serializable{
		private int id_Voyage;
		private String destination;
		private String continent;
		private String type;
		private String date;
		private int duree;
		
		private InputStream image;
		private String base64Image;
		private String theme;
		private String hebergement;
	    
	    private double prix;

		public int getId_Voyage() {
			return id_Voyage;
		}

		public void setId_Voyage(int id_Voyage) {
			this.id_Voyage = id_Voyage;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getContinent() {
			return continent;
		}

		public void setContinent(String continent) {
			this.continent = continent;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getDuree() {
			return duree;
		}

		public void setDuree(int duree) {
			this.duree = duree;
		}

		public InputStream getImage() {
			return image;
		}

		public void setImage(InputStream image) {
			this.image = image;
		}

		public String getBase64Image() {
			return base64Image;
		}

		public void setBase64Image(String base64Image) {
			this.base64Image = base64Image;
		}

		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

		public String getHebergement() {
			return hebergement;
		}

		public void setHebergement(String hebergement) {
			this.hebergement = hebergement;
		}

		public double getPrix() {
			return prix;
		}

		public void setPrix(double prix) {
			this.prix = prix;
		}

		public Voyage(int id_Voyage, String destination, String continent, String type, String date, int duree,
				InputStream image, String base64Image, String theme, String hebergement, double prix) {
			super();
			this.id_Voyage = id_Voyage;
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.image = image;
			this.base64Image = base64Image;
			this.theme = theme;
			this.hebergement = hebergement;
			this.prix = prix;
		}

		public Voyage(String destination, String continent, String type, String date, int duree, String theme,
				double prix) {
			super();
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.theme = theme;
			this.prix = prix;
		}

		public Voyage(String destination, String continent, String type, String date, int duree, InputStream image,
				String base64Image, String theme, String hebergement, double prix) {
			super();
			this.destination = destination;
			this.continent = continent;
		this.type = type;
			this.date = date;
			this.duree = duree;
			this.image = image;
			this.base64Image = base64Image;
			this.theme = theme;
			this.hebergement = hebergement;
			this.prix = prix;
		}

		public Voyage(String destination, String continent, String type, String date, int duree, InputStream image,
				String theme, String hebergement, double prix) {
			super();
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.image = image;
			this.theme = theme;
			this.hebergement = hebergement;
			this.prix = prix;
		}

		
		public Voyage() {}

		

		public Voyage(String destination, String continent, String type, String date, int duree, InputStream image,
				String theme, double prix) {
			super();
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.image = image;
			this.theme = theme;
			this.prix = prix;
		}

		

		public Voyage(String destination, String continent, String type, String date, int duree, double prix) {
			super();
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.prix = prix;
		}

		

		

		public Voyage(int id_Voyage, String destination, String continent, String type, String date, int duree,
				double prix) {
			super();
			this.id_Voyage = id_Voyage;
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.date = date;
			this.duree = duree;
			this.prix = prix;
		}

		

		public Voyage(int id_Voyage, String destination, String continent, String type, int duree, double prix) {
			super();
			this.id_Voyage = id_Voyage;
			this.destination = destination;
			this.continent = continent;
			this.type = type;
			this.duree = duree;
			this.prix = prix;
		}

		@Override
		public String toString() {
			return "Voyage [destination=" + destination + ", continent=" + continent + ", type=" + type + ", date="
					+ date + ", duree=" + duree + ", image=" + image + ", theme=" + theme + ", hebergement="
					+ hebergement + ", prix=" + prix + "]";
		}
		
	}

