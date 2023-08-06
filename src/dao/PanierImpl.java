package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Panier;

public class PanierImpl implements IPanier{
		static Connection connection;
		
		public PanierImpl(Connection connection) {
			// TODO Auto-generated constructor stub
			PanierImpl.connection = connection;
		}

		public PanierImpl() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void addPanier(Panier p) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("insert into panier(id_Voyage,id_client,id_hotel,date_res) values (?,?,?,?)");
				st.setInt(1, p.getId_Voyage());
				st.setInt(2, p.getId_client());
				st.setInt(3, p.getId_hotel());
				st.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				
				
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		

		@Override
		public List<Panier> ListPanier(String idUtilisateur) {
			List<Panier> cmd = new ArrayList<Panier>();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from panier where id_client = ?");
				st.setInt(1, Integer.parseInt(idUtilisateur));
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					Panier c=new Panier();;
					c.setId_panier(rs.getInt("id_panier"));
					c.setId_client(rs.getInt("id_client"));
					c.setId_Voyage(rs.getInt("id_Voyage"));
					c.setId_hotel(rs.getInt("id_hotel"));
					c.setDate(rs.getDate("date_res"));
					System.out.println(rs.getDate("date_res"));
					cmd.add(c);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}

		@Override
		public void deletePanier(int id_p,int id_c) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("delete from panier where id_Panier=? and id_client=?");
				st.setInt(1,id_p);
				st.setInt(2,id_c);
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public void deletePanierC(int id_p) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("delete from panier where id_Panier=?");
				st.setInt(1,id_p);
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public List<Panier> ListPanierID(String id_v,String id_client) {
			List<Panier> cmd = new ArrayList();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from panier where id_client = ? ans id_Voyage = ?");
				st.setInt(1, Integer.parseInt(id_v));
				st.setInt(2, Integer.parseInt(id_client));
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					Panier c=new Panier();;
					c.setId_panier(rs.getInt("id_panier"));
					c.setId_client(rs.getInt("id_client"));
					c.setId_Voyage(rs.getInt("id_Voyage"));
					c.setId_hotel(rs.getInt("id_hotel"));
					c.setDate(rs.getDate("date_res"));
					cmd.add(c);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}
		
		public List<Panier> PanierParIdClient(int id) {
			List<Panier> listePanier = new ArrayList<Panier>();
			try {
				String query2 = "SELECT id_Voyage,id_hotel,id_panier,date_res from panier WHERE id_client = ? ";
				PreparedStatement pt2 = connection.prepareStatement(query2);
				pt2.setInt(1, id);
				ResultSet rs = pt2.executeQuery();

				while (rs.next()) {
					Panier p = new Panier();
					p.setId_client(id);

					p.setId_Voyage(rs.getInt("id_Voyage"));
					p.setId_hotel(rs.getInt("id_hotel"));
					p.setId_panier(rs.getInt("id_panier"));
					p.setDate(rs.getDate("date_res"));
					listePanier.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listePanier;

		}
		
		@Override
		public int CountPanier(int id_u) {
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
		
		public  Date getDateRes(int id_p) {
			Date c = null;
			try {
				String query = "SELECT date_res FROM panier where id_p =? ";
				PreparedStatement p = connection.prepareStatement(query);
				p.setInt(1, id_p);
				ResultSet s = p.executeQuery();
				if (s.next()) {
					c = s.getDate("date_res");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return c;
		}

	}

