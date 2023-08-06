package dao;

import java.util.List;

import entities.Voyage_acc;

public interface IVoyage_acc {
	public  boolean saveVoyage_acc(Voyage_acc v);
	public void deleteVoyage_acc_acc(int id_v);
	public List<Voyage_acc> ListVoyage_acc();
	public List<Voyage_acc> ListPanierVoyage_accID(String id_v);
	public  Voyage_acc getDestContParIdVAcc(int id);
	public List<Voyage_acc> Voyage_accParIdVoyage_acc(int id);
	public List<Voyage_acc> ListPanier_acc(String id);
	public List<Voyage_acc> ListParDestAcc(String Dest);
	public List<Voyage_acc> ListPartype_acc(String type_acc);
	public List<Voyage_acc> RechercheAvanceeACC(String destination_acc, String type_acc,
			String duree_accMin,String duree_accMax, String budge);
	public Voyage_acc getVoyage_acc(String id_c);
	public  Voyage_acc getVoyage_accParId(int id);
	public List<Voyage_acc> getVoyageAccParAct(String actv);
	public  Voyage_acc updateVoyageAcc(Voyage_acc h,int id );
	
	
}
