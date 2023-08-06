package dao;
import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import entities.Commande;

	public class CommandeImpl implements ICommande{

		@Override
		public void addCommande(Commande c) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("insert into commande(nom_cl,id_Client,id_Voyage,destination,type,date,duree) values (?,?,?,?,?,?,? )");
				
				st.setString(1,  c.getNom_cl() );
				st.setInt(2, c.getId_client());
				st.setInt(3, c.getId_Voyage());
				st.setString(4,  c.getDestination() );
				st.setString(5,  c.getType() );
				st.setString(6,  c.getDate() );
				st.setInt(7,  c.getDuree() );
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void deleteCommande(int id_c) {
			Connection conn=DBconnect.getConnection();
			try {
				PreparedStatement st=conn.prepareStatement("delete from commande where id_commande=?");
				st.setString(1,String.valueOf(id_c));
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public List<Commande> ListCommande() {
			List<Commande> cmd = new ArrayList();
			Connection conn=DBconnect.getConnection();
			
			try {
				PreparedStatement st=conn.prepareStatement("select * from commande");
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					Commande c=new Commande();;
					c.setId_commande(rs.getInt("id_commande"));
					c.setId_client(rs.getInt("id_client"));
					c.setId_Voyage(rs.getInt("id_Voyage"));
					c.setDestination(rs.getString("destination"));
					c.setNom_cl(rs.getString("nom_cl"));
					c.setType(rs.getString("type"));
					c.setDate(rs.getString("date"));
					c.setDuree(rs.getInt("duree"));
					cmd.add(c);
				}
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cmd;
		}
		
}
