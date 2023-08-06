package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadFile {
	public void addFile(String name, String email,String tele, byte[] bytes) throws FileNotFoundException {

			
	System.out.println("Add File function");
			Connection conn = DBconnect.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement("insert into profile(nameP,emailP,teleP,fileP) values(?,?,?,?)");
			
			    int success =0;
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, tele);
				ps.setBytes(4, bytes);
				success = ps.executeUpdate();
				if(success == 1) {
					System.out.println("File is stored");
					ps.close();
				}
				
				
				
			} catch (SQLException e1 ) {
				
				e1.printStackTrace();
			}		
		

			

		
		}
		
		public byte[] getFile(int id) {
			
			Connection conn = DBconnect.getConnection();
			 ResultSet rset=null;
			 byte[] bytes=null;
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("Select fileP from profile where idP=?");
				ps.setInt(1, id);
		        rset = ps.executeQuery();
		        if (rset.next()) {
		        	bytes =  rset.getBytes("fileP");
		        	
		        	}
		        else return null;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	        
			return bytes;  
		}

	}


