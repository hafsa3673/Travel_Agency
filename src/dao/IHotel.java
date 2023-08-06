package dao;

import java.util.List;

import entities.Hotel;

public interface IHotel {
	public  boolean saveHotel(Hotel h);
	public  Hotel updateHotel(Hotel h,int id );
	public List<Hotel> ListesHotels();
	public  List<Hotel> HotelParIdClt(int id);
	public  Hotel getHotelParId(int id) ;
	public List<Hotel> getHotelParDEST_CONT(String ville);
	public  void deleteHotel(int id);
	public String getNHotelParId(int id);
	public List<Hotel> ListPanierHotelID(String id_h);
	public List<Hotel> RechercheAvanceeParhotel(String nom,String ville,double prix);
}
