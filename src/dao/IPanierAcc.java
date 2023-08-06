package dao;

import java.util.List;

import entities.Panier;
import entities.PanierAcc;

public interface IPanierAcc {
	public void addPanierAcc(PanierAcc p);
	public List<PanierAcc> ListPanierAcc(String idUtilisateur);
	public void deletePanierAcc(int id_p,int id_c) ;
	public void deletePanierAccC(int id_p);
	public List<PanierAcc> ListPanierAccID(String id_v,String id_client);
	public List<PanierAcc> PanierAccParIdClient(int id);
	public int CountPanierAcc(int id_u);
	
	
}
