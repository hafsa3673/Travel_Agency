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
import java.util.concurrent.ForkJoinPool;

import entities.Hotel;
import entities.Voyage;




public class HotelImpl implements IHotel {
	static Connection connection;

	 public HotelImpl(Connection connection) {
		HotelImpl.connection = connection;
	}	
		
	public HotelImpl() {
		
	}

	public boolean saveHotel(Hotel h){
		boolean set= false;
		try {
	
		String query1 = "insert into hotel(nom_hotel,prix_hotel,etoile_hotel,ville,image_hotel) values(?,?,?,?,?);";
		PreparedStatement pt1 = (PreparedStatement) connection.prepareStatement(query1);
		pt1.setString(1, h.getNom_hotel());
		pt1.setDouble(2 , h.getPrix_hotel());
		pt1.setInt(3, h.getEtoile_hotel());
		pt1.setString(4, h.getVille());
		pt1.setBlob(5, h.getImage_hotel());
		pt1.executeUpdate();
		set = true;
		System.out.println("c fait ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return set ;
	}
	
	
	public List<Hotel> ListPanierHotelID(String id_h) {
		List<Hotel> cmd = new ArrayList<Hotel>();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from hotel where  id_hotel = ?");
			st.setInt(1, Integer.parseInt(id_h));
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Hotel v=new Hotel();;
				v.setId_hotel(rs.getInt("id_hotel"));
				v.setNom_hotel(rs.getString("nom_hotel"));		
				v.setPrix_hotel(rs.getDouble("prix_hotel"));
				v.setEtoile_hotel(rs.getInt("etoile_hotel"));
				v.setVille(rs.getString("ville"));		
				InputStream inputStream = rs.getBinaryStream("image_hotel");
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
	
	
	public  Hotel updateHotel(Hotel h,int id )
	{
		try {
			String query3 = "update hotel set nom_hotel =? ,prix_hotel =?,etoile_hotel =?  where id_hotel =?";
			PreparedStatement pt1 = connection.prepareStatement(query3);
			
			pt1.setString(1, h.getNom_hotel());
			pt1.setDouble(2 , h.getPrix_hotel());
			pt1.setInt(3, h.getEtoile_hotel());
			pt1.setInt(4,id);
			pt1.executeUpdate();
			System.out.println("c fait");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	
	 public List<Hotel> RechercheAvanceeParhotel(String nom,String ville,double prix){
		 List<Hotel>  vo = new ArrayList<Hotel>();
		 Connection conn=DBconnect.getConnection();
		 try {
		 String query2 = "SELECT * from hotel WHERE nom_hotel=? or ville = ? or prix_hotel =?  ";
		 PreparedStatement ps = conn.prepareStatement(query2);

		 ps.setString(1, nom);
		 ps.setString(2, ville);
		 ps.setDouble(3, prix);
		 ResultSet rs = ps.executeQuery();


		 while(rs.next()) {
		 Hotel vv= new Hotel();
		 vv.setId_hotel(rs.getInt("id_hotel"));
		 vv.setNom_hotel(rs.getString("nom_hotel"));
		 vv.setPrix_hotel(rs.getDouble("prix_hotel"));
		 vv.setEtoile_hotel(rs.getInt("etoile_hotel"));
		 vv.setNbr_chambre(rs.getInt("nbr_chambre"));
		 vv.setInclusivite_hotel(rs.getString("inclusivite_hotel"));
		    vv.setVille(rs.getString("ville"));
		 InputStream inputStream = rs.getBinaryStream("image_hotel");
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

	
	 public List<Hotel> ListesHotels(){
		 List<Hotel> ListesHotels = new ArrayList<Hotel>();
		 Connection conn = DBconnect.getConnection();
		 PreparedStatement st;
		 try {
		 st = conn.prepareStatement("select * from hotel");

		 ResultSet r = st.executeQuery();

		 while(r.next()) {
		 Hotel p = new Hotel();
		 p.setId_hotel(r.getInt("id_hotel"));
		 p.setNom_hotel(r.getString("nom_hotel"));
		 p.setPrix_hotel(r.getDouble("prix_hotel"));
		 p.setEtoile_hotel(r.getInt("etoile_hotel"));
		 p.setVille(r.getString("ville"));
		 InputStream inputStream = r.getBinaryStream("image_hotel");
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
		               


		               p.setBase64Image(base64Image);
		 ListesHotels.add(p);
		 System.out.println("c fait ");
		 }
		 }catch(Exception e) {
		 e.printStackTrace();
		 }
		 return ListesHotels;


		 }

	 public  Hotel getNomHParIdH(int id) {
			Hotel c = new Hotel();
			try {
				String query2 = "SELECT nom_hotel from hotel WHERE id_hotel=? ";
				PreparedStatement ps = connection.prepareStatement(query2);

				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					c.setId_hotel(id);

					c.setNom_hotel(rs.getString("nom_hotel"));

				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return c;
		}
	 
	 
	 public  List<Hotel> HotelParIdClt(int id){
			List<Hotel> listeHot = new ArrayList<Hotel>();
			try {
		String query2 = "SELECT * FROM hotel WHERE id_clt = ?;";
		PreparedStatement pt2 =connection.prepareStatement(query2);
		pt2.setInt(1,id);
		ResultSet r = pt2.executeQuery();
		
		while(r.next()) {
			Hotel p = new Hotel();
			
			p.setNom_hotel(r.getString("nom_hotel"));
			p.setPrix_hotel(r.getDouble("prix_hotel"));
			p.setEtoile_hotel(r.getInt("etoile_hotel"));
			
			listeHot.add(p);
			System.out.println("c fait ");
						}
		
		
				}catch(Exception e) {
		e.printStackTrace();
		}
			return listeHot;
	}
	 
	 public  Hotel getHotelParId(int id) {
		 Connection conn = DBconnect.getConnection();
	    	Hotel c = new Hotel();
			try {
				String query2 = "SELECT * from hotel WHERE id_hotel=? ";
				PreparedStatement ps =conn.prepareStatement(query2);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					c.setId_hotel(id);
					c.setNom_hotel(rs.getString("nom_hotel"));	
					c.setPrix_hotel(rs.getDouble("prix_hotel"));	
					c.setEtoile_hotel(rs.getInt("etoile_hotel"));	
					c.setPrix_hotel(rs.getDouble("prix_hotel"));
					InputStream inputStream = rs.getBinaryStream("image_hotel");
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
			               
			        c.setBase64Image(base64Image);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return c;
	    }
	 
	 public  List<Hotel> getHotelParDEST_CONT(String ville) {
		 Connection conn = DBconnect.getConnection();
		 List<Hotel> listeHot = new ArrayList<Hotel>();
			try {
		String query2 = "SELECT * FROM hotel WHERE ville = ?;";
		PreparedStatement pt2 =conn.prepareStatement(query2);
		pt2.setString(1,ville);
		ResultSet r = pt2.executeQuery();
		
		while(r.next()) {
			Hotel p = new Hotel();
			p.setId_hotel(r.getInt("id_hotel"));
			p.setNom_hotel(r.getString("nom_hotel"));
			p.setPrix_hotel(r.getDouble("prix_hotel"));
			p.setEtoile_hotel(r.getInt("etoile_hotel"));
			InputStream inputStream = r.getBinaryStream("image_hotel");
			System.out.println(inputStream);
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
	               
					
					
	                p.setBase64Image(base64Image);
			listeHot.add(p);
			System.out.println("c fait ");
						}
		
		
				}catch(Exception e) {
		e.printStackTrace();
		}
			return listeHot;
	    }
	 
	 
	public  void deleteHotel(int id) {
		try {
			String query4 ="DELETE FROM hotel WHERE id_hotel = ?"; 
			PreparedStatement ps= connection.prepareStatement(query4);
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
			System.out.println("c fait");
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
	
}
	
	public String getNHotelParId(int id) {
		String n = null;
		Connection conn=DBconnect.getConnection();
		try {
			String query2 = "SELECT nom_hotel from Hotel WHERE id_hotel=? ";
			PreparedStatement ps = conn.prepareStatement(query2);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				n=rs.getString("nom_hotel");

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return n;
	}
	}
	

