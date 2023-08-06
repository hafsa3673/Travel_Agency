package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Panier;
import entities.PanierAcc;

public class PanierAccImpl implements IPanierAcc{
		static Connection connection;
		
		public PanierAccImpl(Connection connection) {
			// TODO Auto-generated constructor stub
			PanierAccImpl.connection = connection;
		}

		public PanierAccImpl() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void addPanierAcc(PanierAcc p) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("insert into panierAcc(id_Voyage_acc,id_client,date_res) values (?,?,?)");
				st.setInt(1, p.getId_Voyage_acc());
				st.setInt(2, p.getId_client());
				st.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		

		@Override
		public List<PanierAcc> ListPanierAcc(String idUtilisateur) {
			List<PanierAcc> cmd = new ArrayList<PanierAcc>();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from panierAcc where id_client = ?");
				st.setInt(1, Integer.parseInt(idUtilisateur));
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					PanierAcc c=new PanierAcc();;
					c.setId_panier_acc(rs.getInt("id_panier_acc"));
					c.setId_client(rs.getInt("id_client"));
					c.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					c.setDate(rs.getDate("date_res"));
					
					cmd.add(c);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}

		@Override
		public void deletePanierAcc(int id_p,int id_c) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("delete from panierAcc where id_panier_acc=? and id_client=?");
				st.setInt(1,id_p);
				st.setInt(2,id_c);
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public void deletePanierAccC(int id_p) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("delete from panierAcc where id_Panier_acc=?");
				st.setInt(1,id_p);
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public List<PanierAcc> ListPanierAccID(String id_v,String id_client) {
			List<PanierAcc> cmd = new ArrayList<PanierAcc>();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from panierAcc where id_client = ? and id_Voyage_acc = ?");
				st.setInt(1, Integer.parseInt(id_v));
				st.setInt(2, Integer.parseInt(id_client));
				st.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					PanierAcc c=new PanierAcc();;
					c.setId_panier_acc(rs.getInt("id_panier_acc"));
					c.setId_client(rs.getInt("id_client"));
					c.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					
					cmd.add(c);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}
		
		public List<PanierAcc> PanierAccParIdClient(int id) {
			List<PanierAcc> listePanier = new ArrayList<PanierAcc>();
			try {
				String query2 = "SELECT id_Voyage_acc,id_panier_acc,date_res from panierAcc WHERE id_client = ? ";
				PreparedStatement pt2 = connection.prepareStatement(query2);
				pt2.setInt(1, id);
				ResultSet rs = pt2.executeQuery();

				while (rs.next()) {
					PanierAcc p = new PanierAcc();
					p.setId_client(id);

					p.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					p.setId_panier_acc(rs.getInt("id_panier_acc"));
					p.setDate(rs.getDate("date_res"));

					listePanier.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listePanier;

		}
		
		@Override
		public int CountPanierAcc(int id_u) {
			ResultSet rs;
			int NbrAcc =0;
			int Nbr =0;
			Connection conn=DBconnect.getConnection();
			try {
				Statement st=conn.createStatement();
				rs=st.executeQuery("SELECT COUNT(*) AS total FROM `panierAcc` where id_client = "+id_u);
				 while(rs.next()){
					 NbrAcc = rs.getInt("total");
				 }
					
		        
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				Statement st=conn.createStatement();
				rs=st.executeQuery("SELECT COUNT(*) AS total FROM `panier` where id_client = "+id_u);
				 while(rs.next()){
					 Nbr = rs.getInt("total");
				 }
					
		        
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return NbrAcc+Nbr;
		}

		

	

	}

