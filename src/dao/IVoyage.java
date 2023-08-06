package dao;
import java.util.List;
import entities.Voyage;

	public interface IVoyage {
		public Voyage getVoyage(String id_c);
		public boolean saveVoyage(Voyage v);
		public void deleteVoyage(int id_c);
		public List<Voyage> ListVoyage();
		public List<Voyage> ListParDest(String Dest);
		List<Voyage> ListParType(String type);
		public List<Voyage> RechercheAvancee(String destination, String type,
			String dureeMin,String dureeMax, String budge);
		List<Voyage> ListPanier(String id);
		public  Voyage getDestContParIdV(int id);
		public  Voyage getVoyageParId(int id);
		public List<Voyage> VoyageParIdVoyage(int id);
		public List<Voyage> ListPanierVoyageID(String id_v);
		public List<Voyage> RechercheAvancee(String destination,String date,double prix);
		public  Voyage updateVoyage(Voyage h,int id );
	}

