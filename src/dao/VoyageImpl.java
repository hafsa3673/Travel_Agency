package dao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import entities.Hotel;
import entities.Panier;
import entities.Voyage;

	
	public class VoyageImpl implements IVoyage{
		static Connection connection;

		
		public VoyageImpl(Connection connection) {
			// TODO Auto-generated constructor stub
			VoyageImpl.connection = connection;
		}

		public VoyageImpl() {
			// TODO Auto-generated constructor stub
		}
		

		public boolean saveVoyage(Voyage v) {
			boolean set= false;
			try {
		
			String query1 = "insert into voyage(destination,continent,type,date,duree,image,theme,hebergement,prix) values (?,?,?,?,?,?,?,?,?);";
			PreparedStatement st = (PreparedStatement) connection.prepareStatement(query1);
			st.setString(1, v.getDestination());
			st.setString(2, v.getContinent());
			st.setString(3, v.getType());
			st.setString(4, v.getDate());
			st.setInt(5, v.getDuree());
			st.setBlob(6, v.getImage());
			st.setString(7, v.getTheme());
			st.setString(8, v.getHebergement());
			st.setDouble(9, v.getPrix());
			st.executeUpdate();
			set = true;
			System.out.println("c fait ");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return set ;
		}
		@Override
		public void deleteVoyage(int id_v) {
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			try{
				st = conn.prepareStatement("delete from voyage where id_voyage = ?");
				st.setString(1, String.valueOf(id_v));
				st.executeUpdate();
				st.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public  Voyage updateVoyage(Voyage h,int id )
		{
			try {
				String query3 = "update voyage set destination = ? , continent= ? ,type = ? ,duree = ? ,prix = ?   where id_Voyage =?";
				PreparedStatement pt1 = connection.prepareStatement(query3);
				
				pt1.setString(1, h.getDestination());
				pt1.setString(2 , h.getContinent());
				pt1.setString(3, h.getType());
				pt1.setInt(4, h.getDuree());
				pt1.setDouble(5, h.getPrix());
				pt1.setInt(6,id);
				pt1.executeUpdate();
				System.out.println("c fait");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return h;
		}
		
		
		public List<Voyage> RechercheAvancee(String destination,String date,double prix) {
			List<Voyage>  vo = new ArrayList<Voyage>();
			Connection conn=DBconnect.getConnection();
			try {
			String query2 = "SELECT * from voyage WHERE destination=? or date=? or prix=? ";
			PreparedStatement ps = conn.prepareStatement(query2);

			ps.setString(1, destination);
			ps.setString(2, date);
			ps.setDouble(3, prix);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
			Voyage vv= new Voyage();
			vv.setId_Voyage(rs.getInt("id_Voyage"));
			System.out.println(rs.getInt("id_Voyage"));
			vv.setDestination(rs.getString("destination"));
			System.out.println("la destination"+rs.getString("destination"));
			vv.setContinent(rs.getString("continent"));
			vv.setType(rs.getString("type"));
			vv.setDate(rs.getString("date"));
			vv.setDuree(rs.getInt("duree"));
			vv.setPrix(rs.getDouble("prix"));
			vv.setTheme(rs.getString("theme"));
			vv.setHebergement(rs.getString("hebergement"));
			InputStream inputStream = rs.getBinaryStream("image");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			             int bytesRead = -1;
			              
			             try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			   outputStream.write(buffer, 0, bytesRead);                  
			}



			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			byte[] imageBytes = outputStream.toByteArray();
			               String base64image = Base64.getEncoder().encodeToString(imageBytes);
			                
			                
			               try {
			inputStream.close();
			outputStream.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			vv.setBase64Image(base64image);
			vo.add(vv);
			}
			} catch (Exception e) {

			e.printStackTrace();
			}

			return vo;
			}
		
		

		public List<Voyage> ListVoyage() {
			List<Voyage> reise= new ArrayList<Voyage>();
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			try{
			st = conn.prepareStatement("select * from voyage");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
			Voyage v = new Voyage();
			v.setId_Voyage(rs.getInt("id_Voyage"));
			v.setDestination(rs.getString("destination"));
			v.setContinent(rs.getString("continent"));
			v.setType(rs.getString("type"));
			v.setDate(rs.getString("date"));
			v.setDuree(rs.getInt("duree"));
			v.setPrix(rs.getDouble("prix"));
			InputStream inputStream = rs.getBinaryStream("image");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			             int bytesRead = -1;
			             try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			   outputStream.write(buffer, 0, bytesRead);                  
			}



			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			byte[] imageBytes = outputStream.toByteArray();
			               String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			                
			                
			               try {
			inputStream.close();
			outputStream.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			        System.out.println("="+inputStream);      
			       v.setBase64Image(base64Image);
			v.setTheme(rs.getString("theme"));
			v.setHebergement(rs.getString("hebergement"));
			reise.add(v);
			}
			st.close();

			}catch (SQLException e) {
			e.printStackTrace();
			}
			return reise;
			}

		
		public List<Voyage> ListPanierVoyageID(String id_v) {
			List<Voyage> cmd = new ArrayList();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from voyage where  id_Voyage = ?");
				st.setInt(1, Integer.parseInt(id_v));
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					Voyage v=new Voyage();;
					v.setId_Voyage(rs.getInt("id_Voyage"));
					v.setDestination(rs.getString("destination"));
					v.setContinent(rs.getString("continent"));
					v.setType(rs.getString("type"));
					v.setDate(rs.getString("date"));
					v.setDuree(rs.getInt("duree"));
					v.setPrix(rs.getDouble("prix"));
					
					v.setTheme(rs.getString("theme"));
					InputStream inputStream = rs.getBinaryStream("image");
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
		              int bytesRead = -1;
		               
		              try {
							while ((bytesRead = inputStream.read(buffer)) != -1) {
							    outputStream.write(buffer, 0, bytesRead);                  
							}
							
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							byte[] imageBytes = outputStream.toByteArray();
			                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			                 
			                 
			                try {
								inputStream.close();
								 outputStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			               
			        v.setBase64Image(base64Image);
					cmd.add(v);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}
		
		public  Voyage getDestContParIdV(int id) {
			Voyage c = new Voyage();
			try {
				String query2 = "SELECT destination,continent from voyage WHERE id_Voyage=? ";
				PreparedStatement ps = connection.prepareStatement(query2);

				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					c.setId_Voyage(id);

					c.setDestination(rs.getString("destination"));
					c.setContinent(rs.getString("continent"));


				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return c;
		}

		public List<Voyage> VoyageParIdVoyage(int id) {
			List<Voyage> listeVoyage = new ArrayList<Voyage>();
			try {
				String query2 = "SELECT * from voyage WHERE id_Voyage = ? ";
				PreparedStatement pt2 = connection.prepareStatement(query2);
				pt2.setInt(1, id);
				ResultSet rs = pt2.executeQuery();

				while (rs.next()) {
					Voyage p = new Voyage();
					p.setId_Voyage(id);

					p.setDestination(rs.getString("destination"));
					p.setContinent(rs.getString("continent"));
					p.setType(rs.getString("type"));
					p.setDate(rs.getString("date"));
					p.setDuree(rs.getInt("duree"));
					p.setType(rs.getString("type"));
					p.setTheme(rs.getString("theme"));
					p.setPrix(rs.getDouble("Prix"));
					listeVoyage.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listeVoyage;
		}
		
		@Override
		public List<Voyage> ListPanier(String id) {
			List<Voyage> reise= new ArrayList();
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			try{
			st = conn.prepareStatement("SELECT * from panier where id_Voyage = (SELECT id_Voyage from panier where id_client = ?)");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Voyage v = new Voyage();
				v.setId_Voyage(rs.getInt("id_Voyage"));
				v.setDestination(rs.getString("destination"));
				v.setType(rs.getString("type"));
				v.setDate(rs.getString("date"));
				v.setDuree(rs.getInt("duree"));
				v.setPrix(rs.getDouble("prix"));
				InputStream inputStream = rs.getBinaryStream("image");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
	              int bytesRead = -1;
	               
	              try {
						while ((bytesRead = inputStream.read(buffer)) != -1) {
						    outputStream.write(buffer, 0, bytesRead);                  
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                 
		                 
		                try {
							inputStream.close();
							 outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               
		        v.setBase64Image(base64Image);
				v.setTheme(rs.getString("theme"));
				v.setHebergement(rs.getString("hebergement"));
				reise.add(v);
			}
			st.close();
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return reise;
		}
		
		

		@Override
		public List<Voyage> ListParDest(String Dest) {

			List<Voyage> reise= new ArrayList();
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			Voyage v = new Voyage();
			try{
			st = conn.prepareStatement("select * from voyage where destination like ?");
			st.setString(1,'%' + Dest + '%');
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				
				v.setId_Voyage(rs.getInt("id_Voyage"));
				v.setDestination(rs.getString("destination"));
				v.setType(rs.getString("type"));
				v.setDate(rs.getString("date"));
				v.setDuree(rs.getInt("duree"));
				v.setPrix(rs.getDouble("prix"));
				InputStream inputStream = rs.getBinaryStream("image");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
	              int bytesRead = -1;
	               
	              try {
						while ((bytesRead = inputStream.read(buffer)) != -1) {
						    outputStream.write(buffer, 0, bytesRead);                  
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                 
		                 
		                try {
							inputStream.close();
							 outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               
		        v.setBase64Image(base64Image);
				v.setTheme(rs.getString("theme"));
				v.setHebergement(rs.getString("hebergement"));
				reise.add(v);
			}
			st.close();
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return reise;
		}

		@Override
		public List<Voyage> ListParType(String type) {

			List<Voyage> reise= new ArrayList();
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			try{
			st = conn.prepareStatement("select * from Voyage where type like ?");
			st.setString(1, '%' + type + '%'); 
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Voyage v = new Voyage();
				v.setId_Voyage(rs.getInt("id_Voyage"));
				v.setDestination(rs.getString("destination"));
				v.setType(rs.getString("type"));
				v.setDate(rs.getString("date"));
				v.setDuree(rs.getInt("duree"));
				v.setPrix(rs.getDouble("prix"));
				InputStream inputStream = rs.getBinaryStream("image");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
	              int bytesRead = -1;
	               
	              try {
						while ((bytesRead = inputStream.read(buffer)) != -1) {
						    outputStream.write(buffer, 0, bytesRead);                  
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                 
		                 
		                try {
							inputStream.close();
							 outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               
		        v.setBase64Image(base64Image);
				v.setTheme(rs.getString("theme"));
				v.setHebergement(rs.getString("hebergement"));
				reise.add(v);
			}
			st.close();
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return reise;
		}

		@Override
		public List<Voyage> RechercheAvancee(String destination, String type,
				String dureeMin,String dureeMax, String budge) {

			List<Voyage> reise= new ArrayList();
			Connection conn = DBconnect.getConnection();
			PreparedStatement st;
			Voyage v = new Voyage();
			try{
			st = conn.prepareStatement("select * from voyage where destination like ? && type like ? && duree >= ? && duree <= ? && prix >= ? ");
			st.setString(1, "%"+destination+"%");
			st.setString(2, "%"+type+"%");
			st.setInt(3, Integer.parseInt(dureeMin));
			st.setInt(4, Integer.parseInt(dureeMax));
			st.setDouble(5, Double.valueOf(budge));
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				
				v.setId_Voyage(rs.getInt("id_Voyage"));
				v.setDestination(rs.getString("destination"));
				v.setType(rs.getString("type"));
				v.setDate(rs.getString("date"));
				v.setDuree(rs.getInt("duree"));
				v.setPrix(rs.getDouble("prix"));
				InputStream inputStream = rs.getBinaryStream("image");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
	              int bytesRead = -1;
	               
	              try {
						while ((bytesRead = inputStream.read(buffer)) != -1) {
						    outputStream.write(buffer, 0, bytesRead);                  
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                 
		                 
		                try {
							inputStream.close();
							 outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               
		        v.setBase64Image(base64Image);
				v.setTheme(rs.getString("theme"));
				v.setHebergement(rs.getString("hebergement"));
				reise.add(v);
			}
			st.close();
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return reise;
		}

		@Override
		public Voyage getVoyage(String id_c) {
			Voyage v=new Voyage();
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("select * from Voyage where id_Voyage=?");
				st.setString(1,  id_c);
				ResultSet rs=st.executeQuery();
				if(rs.next()){
					v.setId_Voyage(rs.getInt("id_Voyage"));
					v.setDestination(rs.getString("destination"));
					v.setContinent(rs.getString("continent"));
					v.setType(rs.getString("type"));
					v.setDate(rs.getString("date"));
					v.setDuree(rs.getInt("duree"));
					v.setPrix(rs.getDouble("prix"));
					InputStream inputStream = rs.getBinaryStream("image");
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
		              int bytesRead = -1;
		               
		              try {
							while ((bytesRead = inputStream.read(buffer)) != -1) {
							    outputStream.write(buffer, 0, bytesRead);                  
							}
							
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							byte[] imageBytes = outputStream.toByteArray();
			                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			                 
			                 
			                try {
								inputStream.close();
								 outputStream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			               
			        v.setBase64Image(base64Image);
					v.setTheme(rs.getString("theme"));
					v.setHebergement(rs.getString("hebergement"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return v;
		}

	public  Voyage getVoyageParId(int id) {
		Voyage v = new Voyage();
		Connection conn=DBconnect.getConnection();
		try {
			String query2 = "SELECT * from Voyage WHERE id_Voyage=? ";
			PreparedStatement ps = conn.prepareStatement(query2);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				v.setId_Voyage(rs.getInt("id_Voyage"));
				v.setDestination(rs.getString("destination"));
				v.setContinent(rs.getString("continent"));
				v.setType(rs.getString("type"));
				v.setDate(rs.getString("date"));
				v.setDuree(rs.getInt("duree"));
				v.setPrix(rs.getDouble("prix"));
				InputStream inputStream = rs.getBinaryStream("image");
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
	              int bytesRead = -1;
	               
	              try {
						while ((bytesRead = inputStream.read(buffer)) != -1) {
						    outputStream.write(buffer, 0, bytesRead);                  
						}
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						byte[] imageBytes = outputStream.toByteArray();
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                 
		                 
		                try {
							inputStream.close();
							 outputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		               
		        v.setBase64Image(base64Image);
				v.setTheme(rs.getString("theme"));
				v.setHebergement(rs.getString("hebergement"));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return v;
	}

	
	}