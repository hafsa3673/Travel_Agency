package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Client;

	public class ClientImpl implements IClient{
		static Connection connection;

		
		public ClientImpl(Connection connection) {
			// TODO Auto-generated constructor stub
			ClientImpl.connection = connection;
		}

		public ClientImpl() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void addClient(Client c) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("insert into client values (NULL,0,?,?,?,?)");
				st.setString(1, c.getNom());
				st.setString(2, c.getEmail());
				st.setString(3, c.getPassword());
				st.setString(4, c.getTel());
				
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		public boolean register(Client clt) {
			boolean set = false;
			Connection conn=DBconnect.getConnection();
			try{
			PreparedStatement st=conn.prepareStatement("insert into client(nom,email,password,tel) values (?,?,?,?)");
			st.setString(1, clt.getNom());
			st.setString(2, clt.getEmail());
			st.setString(3, clt.getPassword());
			st.setString(4, clt.getTel());
			System.out.println("je suis dans register");
			st.executeUpdate();
			set=true;
			} catch (SQLException e) {
			e.printStackTrace();
			}
			 return set;

			}

		
		public List<Client> ListeClient() {
			List<Client> listeClt = new ArrayList<Client>();
			try {
				String query2 = "SELECT * FROM client ";
				PreparedStatement pt2 = connection.prepareStatement(query2);
				ResultSet rs = pt2.executeQuery();

				while (rs.next()) {
					Client p = new Client();
					p.setId_client(rs.getInt("id_client"));
					p.setNom(rs.getString("nom"));
					p.setEmail(rs.getString("email"));
					p.setPassword(rs.getString("password"));
					p.setTel(rs.getString("tel"));
					

					listeClt.add(p);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listeClt;

		}
		
		public static void updateClient(Client c, int id) {

			try {
				String query3 = "update Client set nom = ?,email=?,tel=?  where id_client = ?";
				PreparedStatement pt3 = connection.prepareStatement(query3);

				pt3.setString(1, c.getNom());
				pt3.setString(2, c.getEmail());
				pt3.setString(3, c.getTel());
				pt3.setInt(4, id);
				pt3.executeUpdate();
				System.out.println("c fait");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public Client getClient(String id_c) {
			Client c=new Client();
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("select * from Client where id_client=?");
				st.setString(1, id_c);
				ResultSet rs=st.executeQuery();
				if(rs.next()){ 
					c.setId_client(rs.getInt("id_client"));
					c.setId_commande(rs.getInt("id_commande"));
					c.setNom(rs.getString("nom"));
					c.setEmail(rs.getString("email"));
					c.setPassword(rs.getString("password"));
					c.setTel(rs.getString("tel"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return c;
		}

		@Override
		public boolean login(String nom, String mdp) {
			Connection conn=DBconnect.getConnection();
			Client c=new Client();
			boolean b=false;
			try {
				PreparedStatement st=conn.prepareStatement("select Password from Client where nom=?");
				st.setString(1, nom);
			ResultSet rs=st.executeQuery();
			
			if(rs.next() && rs.getString("Password").equals(mdp)){
				b=true;
			}
			
		}catch (SQLException e) {
		}
			return b;
		}

		@Override
		public Client getClient(String nom, String mdp) {
			Connection conn=DBconnect.getConnection();
			Client c=new Client();
			try {
				PreparedStatement st=conn.prepareStatement("select * from Client where nom=? and Password=?");
				st.setString(1, nom);
				st.setString(2, mdp);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					c.setId_client(rs.getInt("Id_Client"));
					c.setId_commande(rs.getInt("Id_Commande"));
					c.setNom(nom);
					c.setEmail(rs.getString("Email"));
					c.setPassword(mdp);
					c.setTel(rs.getString("Tel"));
				}
				st.close();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return c;
	}
		public  String getEmail(int id_client) {
			Connection conn=DBconnect.getConnection();
			String c = null;
			try {
				String query = "SELECT email FROM client where id_client=? ";
				PreparedStatement p = conn.prepareStatement(query);
				p.setInt(1, id_client);
				ResultSet s = p.executeQuery();
				if (s.next()) {
					c = s.getString("email");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return c;
		}

		
		public void deleteClient(int id) {
			try {
				String query4 = "DELETE FROM client  WHERE id_client = ?";
				PreparedStatement ps = connection.prepareStatement(query4);
				ps.setInt(1, id);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

