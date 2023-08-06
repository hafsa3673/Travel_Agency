package dao;


	import java.util.List;

	import entities.Commande;

	public interface ICommande {
		public void addCommande(Commande c);
		public void deleteCommande(int id_c);
		public List<Commande> ListCommande();
	}

