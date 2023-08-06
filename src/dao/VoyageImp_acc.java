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
import entities.Voyage;
import entities.Voyage_acc;


public class VoyageImp_acc implements IVoyage_acc {
	
			static Connection connection;

			
			public VoyageImp_acc(Connection connection) {
				// TODO Auto-generated constructor stub
				VoyageImp_acc.connection = connection;
			}

			public VoyageImp_acc() {
				// TODO Auto-generated constructor stub
			}
			public boolean saveVoyage_acc(Voyage_acc v) {
		
				boolean set= false;
				try {
			
				String query1 = "insert into Voyage_acc(destination_acc,continent_acc,type_acc,date_acc,duree_acc,image_acc,theme_acc,hebergement_acc,prix_acc,activite,genre,guide) values (?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement st = (PreparedStatement) connection.prepareStatement(query1);
				st.setString(1, v.getDestination_acc());
				st.setString(2, v.getContinent_acc());
				st.setString(3, v.getType_acc());
				st.setString(4, v.getDate_acc());
				st.setInt(5, v.getDuree_acc());
				st.setBlob(6, v.getImage_acc());
				st.setString(7, v.getTheme_acc());
				st.setString(8, v.getHebergement_acc());
				st.setDouble(9, v.getPrix_acc());
				st.setString(10, v.getActivite());
				st.setString(11, v.getGenre());
				st.setString(12, v.getGuide());
				st.executeUpdate();
				set = true;
				System.out.println("c fait ");
				}catch(Exception e) {
					e.printStackTrace();
				}
				return set;
			}
			
			public  Voyage_acc updateVoyageAcc(Voyage_acc h,int id )
			{
				try {
					String query3 = "update voyage_acc set destination_acc = ? , continent_acc = ? ,type_acc = ? ,date_acc = ? ,duree_acc = ? ,hebergement_acc =?, prix_acc  = ?,activite = ? ,genre = ? ,guide = ?    where id_Voyage_acc =?";
					PreparedStatement pt1 = connection.prepareStatement(query3);
					
					pt1.setString(1, h.getDestination_acc());
					pt1.setString(2 , h.getContinent_acc());
					pt1.setString(3, h.getType_acc());
					pt1.setString(4, h.getDate_acc());
					pt1.setInt(5, h.getDuree_acc());
					pt1.setString(6, h.getHebergement_acc());
					pt1.setDouble(7, h.getPrix_acc());
					pt1.setString(8, h.getActivite());
					pt1.setString(9, h.getGenre());
					pt1.setString(10, h.getGuide());
					pt1.setInt(11,id);
					pt1.executeUpdate();
					System.out.println("c fait");
				}catch(Exception e) {
					e.printStackTrace();
				}
				return h;
			}
			
			

			public void deleteVoyage_acc_acc(int id_v) {
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				try{
					st = conn.prepareStatement("delete from voyage_acc where id_Voyage_acc = ?");
					st.setString(1, String.valueOf(id_v));
					st.executeUpdate();
					st.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			}

			public List<Voyage_acc> ListVoyage_acc() {
				System.out.println("methode");
				List<Voyage_acc> reise= new ArrayList<Voyage_acc>();
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				try{
				st = conn.prepareStatement("select * from voyage_acc");
				ResultSet rs = st.executeQuery();
				while(rs.next()){
					Voyage_acc v = new Voyage_acc();
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setContinent_acc(rs.getString("continent_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
					System.out.println("dd"+inputStream);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
		              int bytesRead = -1;
		              System.out.println("dh");
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
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					v.setActivite(rs.getString("activite"));
					v.setGenre(rs.getString("genre"));
					v.setGuide(rs.getString("guide"));
					reise.add(v);
				}
				st.close();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				return reise;
			}
			
			
			public List<Voyage_acc> ListPanierVoyage_accID(String id_v) {
				List<Voyage_acc> cmd = new ArrayList();
				Connection conn=DBconnect.getConnection();
				
				try {
					PreparedStatement st=conn.prepareStatement("select * from Voyage_acc where  id_Voyage_acc = ?");
					st.setInt(1, Integer.parseInt(id_v));
					ResultSet rs=st.executeQuery();
					while(rs.next()){
						Voyage_acc v=new Voyage_acc();;
						v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
						v.setDestination_acc(rs.getString("destination_acc"));
						v.setContinent_acc(rs.getString("continent_acc"));
						v.setType_acc(rs.getString("type_acc"));
						v.setDate_acc(rs.getString("date_acc"));
						v.setDuree_acc(rs.getInt("duree_acc"));
						v.setPrix_acc(rs.getDouble("prix"));
						
						v.setTheme_acc(rs.getString("theme_acc"));
						v.setActivite(rs.getString("activite"));
						v.setGenre(rs.getString("genre"));
						v.setGuide(rs.getString("guide"));
						InputStream inputStream = rs.getBinaryStream("image_acc");
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
				               
				        v.setBase64Image(base64image);
						cmd.add(v);
					}
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return cmd;
			}
			public  Voyage_acc getDestContParIdVAcc(int id) {
				Voyage_acc c = new Voyage_acc();
				try {
					String query2 = "SELECT destination_acc,continent_acc from Voyage_acc WHERE id_Voyage_acc=? ";
					PreparedStatement ps = connection.prepareStatement(query2);

					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						c.setId_Voyage_acc(id);

						c.setDestination_acc(rs.getString("destination_acc"));
						c.setContinent_acc(rs.getString("continent_acc"));


					}
				} catch (Exception e) {

					e.printStackTrace();
				}

				return c;
			}

			public List<Voyage_acc> Voyage_accParIdVoyage_acc(int id) {
				List<Voyage_acc> listeVoyage_acc = new ArrayList<Voyage_acc>();
				try {
					String query2 = "SELECT * from Voyage_acc WHERE id_Voyage_acc = ? ";
					PreparedStatement pt2 = connection.prepareStatement(query2);
					pt2.setInt(1, id);
					ResultSet rs = pt2.executeQuery();

					while (rs.next()) {
						Voyage_acc p = new Voyage_acc();
						p.setId_Voyage_acc(id);

						p.setDestination_acc(rs.getString("destination_acc"));
						p.setContinent_acc(rs.getString("continent_acc"));
						p.setType_acc(rs.getString("type_acc"));
						p.setDate_acc(rs.getString("date_acc"));
						p.setDuree_acc(rs.getInt("duree_acc"));
						p.setType_acc(rs.getString("type_acc"));
						p.setTheme_acc(rs.getString("theme_acc"));
						p.setPrix_acc(rs.getDouble("Prix_acc"));
						p.setActivite(rs.getString("activite"));
						p.setGenre(rs.getString("genre"));
						p.setGuide(rs.getString("guide"));
						InputStream inputStream = rs.getBinaryStream("image_acc");
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
				               
				        p.setBase64Image(base64image);
						
						listeVoyage_acc.add(p);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return listeVoyage_acc;
			}
			
			public List<Voyage_acc> ListPanier_acc(String id) {
				List<Voyage_acc> reise= new ArrayList();
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				try{
				st = conn.prepareStatement("SELECT * from panier where id_Voyage_acc = (SELECT id_Voyage_acc from panier where id_client = ?)");
				st.setString(1, id);
				ResultSet rs = st.executeQuery();
				while(rs.next()){
					Voyage_acc v = new Voyage_acc();
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
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
			               
			        v.setBase64Image(base64image);
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					reise.add(v);
				}
				st.close();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return reise;
			}
			
			

			public List<Voyage_acc> ListParDestAcc(String Dest) {

				List<Voyage_acc> reise= new ArrayList();
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				Voyage_acc v = new Voyage_acc();
				try{
				st = conn.prepareStatement("select * from Voyage_acc where destination_acc like ?");
				st.setString(1,'%' + Dest + '%');
				ResultSet rs = st.executeQuery();
				while(rs.next()){
					
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
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
			               
			        v.setBase64Image(base64image);
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					reise.add(v);
				}
				st.close();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return reise;
			}

			public List<Voyage_acc> ListPartype_acc(String type_acc) {

				List<Voyage_acc> reise= new ArrayList();
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				try{
				st = conn.prepareStatement("select * from Voyage_acc where type_acc like ?");
				st.setString(1, '%' + type_acc + '%'); 
				ResultSet rs = st.executeQuery();
				while(rs.next()){
					Voyage_acc v = new Voyage_acc();
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
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
			               
			        v.setBase64Image(base64image);
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					reise.add(v);
				}
				st.close();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return reise;
			}

			public List<Voyage_acc> RechercheAvanceeACC(String destination_acc, String type_acc,
					String duree_accMin,String duree_accMax, String budge) {

				List<Voyage_acc> reise= new ArrayList();
				Connection conn = DBconnect.getConnection();
				PreparedStatement st;
				Voyage_acc v = new Voyage_acc();
				try{
				st = conn.prepareStatement("select * from Voyage_acc where destination_acc like ? && type_acc like ? && duree_acc >= ? && duree_acc <= ? && prix >= ? ");
				st.setString(1, "%"+destination_acc+"%");
				st.setString(2, "%"+type_acc+"%");
				st.setInt(3, Integer.parseInt(duree_accMin));
				st.setInt(4, Integer.parseInt(duree_accMax));
				st.setDouble(5, Double.valueOf(budge));
				ResultSet rs = st.executeQuery();
				while(rs.next()){
					
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
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
			               
			        v.setBase64Image(base64image);
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					reise.add(v);
				}
				st.close();
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return reise;
			}

			public Voyage_acc getVoyage_acc(String id_c) {
				Voyage_acc v=new Voyage_acc();
				Connection conn=DBconnect.getConnection();
				try {
					PreparedStatement st=conn.prepareStatement("select * from voyage_acc where id_Voyage_acc=?");
					st.setString(1,  id_c);
					ResultSet rs=st.executeQuery();
					if(rs.next()){
						v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
						v.setDestination_acc(rs.getString("destination_acc"));
						v.setContinent_acc(rs.getString("continent_acc"));
						v.setType_acc(rs.getString("type_acc"));
						v.setDate_acc(rs.getString("date_acc"));
						v.setDuree_acc(rs.getInt("duree_acc"));
						v.setPrix_acc(rs.getDouble("prix_acc"));
						InputStream inputStream = rs.getBinaryStream("image_acc");
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
				               
				        v.setBase64Image(base64image);
						v.setTheme_acc(rs.getString("theme_acc"));
						v.setHebergement_acc(rs.getString("hebergement_acc"));
						v.setActivite(rs.getString("activite"));
						v.setGenre(rs.getString("genre"));
						v.setGuide(rs.getString("guide"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return v;
			}

			public List<Voyage_acc> getVoyageAccParAct(String actv) {
				List<Voyage_acc>  vo = new ArrayList<Voyage_acc>();
				Connection conn=DBconnect.getConnection();
				try {
				String query2 = "SELECT * from Voyage_acc WHERE activite=? ";
				PreparedStatement ps = conn.prepareStatement(query2);

				ps.setString(1, actv);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
				Voyage_acc v = new Voyage_acc();
				v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
				v.setDestination_acc(rs.getString("destination_acc"));
				v.setContinent_acc(rs.getString("continent_acc"));
				v.setType_acc(rs.getString("type_acc"));
				v.setDate_acc(rs.getString("date_acc"));
				v.setDuree_acc(rs.getInt("duree_acc"));
				v.setPrix_acc(rs.getDouble("prix_acc"));
				v.setTheme_acc(rs.getString("theme_acc"));
				v.setHebergement_acc(rs.getString("hebergement_acc"));
				v.setActivite(rs.getString("activite"));
				v.setGenre(rs.getString("genre"));
				v.setGuide(rs.getString("guide"));

				InputStream inputStream = rs.getBinaryStream("image_acc");
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
				
				 v.setBase64Image(base64image);
				vo.add(v);
				}
				} catch (Exception e) {

				e.printStackTrace();
				}
				System.out.println(vo);
				return vo;
				}

				
		public  Voyage_acc getVoyage_accParId(int id) {
			Voyage_acc v = new Voyage_acc();
			Connection conn=DBconnect.getConnection();
			try {
				String query2 = "SELECT * from Voyage_acc WHERE id_Voyage_acc=? ";
				PreparedStatement ps = conn.prepareStatement(query2);

				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					v.setId_Voyage_acc(rs.getInt("id_Voyage_acc"));
					v.setDestination_acc(rs.getString("destination_acc"));
					v.setContinent_acc(rs.getString("continent_acc"));
					v.setType_acc(rs.getString("type_acc"));
					v.setDate_acc(rs.getString("date_acc"));
					v.setDuree_acc(rs.getInt("duree_acc"));
					v.setPrix_acc(rs.getDouble("prix_acc"));
					InputStream inputStream = rs.getBinaryStream("image_acc");
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
			               
			        v.setBase64Image(base64image);
					v.setTheme_acc(rs.getString("theme_acc"));
					v.setHebergement_acc(rs.getString("hebergement_acc"));
					v.setActivite(rs.getString("activite"));
					v.setGenre(rs.getString("genre"));
					v.setGuide(rs.getString("guide"));


				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return v;
		}
		
		
		}

