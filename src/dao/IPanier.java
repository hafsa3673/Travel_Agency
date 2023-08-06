package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Panier;


public interface IPanier {
		public void addPanier(Panier p);
		public List<Panier> ListPanier(String idUtilisateur);
		public void deletePanier(int id_p,int id_c) ;
		public void deletePanierC(int id_p) ;
		public int CountPanier(int id_u);
		public  List<Panier> PanierParIdClient(int id);
		public List<Panier> ListPanierID(String id_v,String id_client);
		
		

	}

