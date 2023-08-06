package web;

import java.awt.Desktop.Action;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import dao.AdminImpl;
import dao.ClientImpl;
import dao.CommandeImpl;
import dao.DBconnect;
import dao.HotelImpl;
import dao.IAdmin;
import dao.IClient;
import dao.ICommande;
import dao.IHotel;
import dao.IPanier;
import dao.IPanierAcc;
import dao.IVoyage;
import dao.IVoyage_acc;
import dao.PanierAccImpl;
import dao.PanierImpl;
import dao.VoyageImp_acc;
import dao.VoyageImpl;
import entities.Admin;
import entities.Client;
import entities.Hotel;
import entities.PDFClient;
import entities.Panier;
import entities.PanierAcc;
import entities.Voyage;
import entities.Voyage_acc;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AgenceServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 10MB
maxRequestSize = 1024 * 1024 * 500) // 50MB
@WebServlet("/AgenceServlet")

public class AgenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private IAdmin adminDao;
	private IVoyage voyageDao;
	private IVoyage_acc voyageAccDao;
	private ICommande commandeDao;	
	private IClient clientDao;
	private IPanier panierDao;
	private IPanierAcc panierAccDao;
	private IHotel hotelDao;
	
	HttpSession session;
	String name, subject, email, msg;

    @Override
	public void init() throws ServletException {
		adminDao=new AdminImpl();
		voyageDao=new VoyageImpl();
		voyageAccDao=new VoyageImp_acc();
		commandeDao= new CommandeImpl();
		clientDao= new ClientImpl();
		panierDao=new PanierImpl();
		panierAccDao=new PanierAccImpl();
		hotelDao = new HotelImpl();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		System.out.println("path : " + path);
		
		if(path.equals("/skydash/pages/Client/VoyageClient_acc.php")) {
			   IVoyage_acc cc= new VoyageImp_acc(DBconnect.getConnection());
			Voyage_acc vacc= cc.getVoyage_acc(request.getParameter("id_Voyage_acc"));
			System.out.println(vacc.getImage_acc());
			request.setAttribute("cc", vacc);
			IVoyage_acc voyageDao_acc = new VoyageImp_acc();
			List<Voyage_acc> voyageacc =voyageDao_acc.Voyage_accParIdVoyage_acc(Integer.parseInt(request.getParameter("id_Voyage_acc")));
			System.out.println("la liste est"+voyageacc);
			request.setAttribute("voyageacc", voyageacc );
			request.getRequestDispatcher("/skydash/pages/Client/VoyageClient_acc.jsp").forward(request, response);
			}
//================================================================================================================================================
		if(path.equals("/skydash/pages/VoyageAcc/allVoyageAcc.php")) {
			IVoyage_acc voyageAccDao = new VoyageImp_acc(DBconnect.getConnection());
			List<Voyage_acc> voyagesAcc =  voyageAccDao.ListVoyage_acc();
			request.setAttribute("voyagesAcc", voyagesAcc );
			request.getRequestDispatcher("/skydash/pages/VoyageAcc/allVoyagesAcc.jsp").forward(request, response);
		}
		//==========================================================================================================================================
		if(path.equals("/pacific-main/voyageparactivites.php")) {
			IVoyage_acc voyageAccDao = new VoyageImp_acc();
			List<Voyage_acc> voyages_acc =  voyageAccDao.getVoyageAccParAct(request.getParameter("activite"));
			System.out.println(voyages_acc);
			request.setAttribute("voyages_acc", voyages_acc );
			request.getRequestDispatcher("/pacific-main/voyageparactivites.jsp").forward(request, response);
		}
		//====================================================================================================================================

		if(path.equals("/skydash/pages/addVoyages/allVoyage.php")) {
			IVoyage voyageDao = new VoyageImpl();
			List<Voyage> voyages =  voyageDao.ListVoyage();
			request.setAttribute("voyages", voyages );
			request.getRequestDispatcher("/skydash/pages/addVoyages/allVoyages.jsp").forward(request, response);
		}
		
		if(path.equals("/skydash/pages/addVoyages/deleteVoyages.php")) {
			System.out.println("dkhlat");
			
			try {
				String idVoyage = request.getParameter( "id_Voyage" );
				System.out.println("deleted");
				voyageDao.deleteVoyage(Integer.parseInt(idVoyage));
				System.out.println("deleted");
				//chargement de liste des voyages
				List<Voyage> voyages =  voyageDao.ListVoyage();
				request.setAttribute("voyages", voyages ); 
				request.setAttribute("succes", "votre voyage a été bien supprimé");	
			} catch (Exception e) {
				request.setAttribute("erreur", "Une erreur est survenue lors de la suppression du voyage ");	
			} finally {
				request.getRequestDispatcher("/skydash/pages/addVoyages/allVoyages.jsp").forward(request, response);
			}	
		}
		
		
		if(path.equals("/skydash/pages/addVoyages/editVoyages.php")){
			IVoyage voyageDao = new VoyageImpl(DBconnect.getConnection());
			int id_v= Integer.parseInt(request.getParameter("id_v"));
			System.out.println(id_v);
			Voyage c = voyageDao.getVoyageParId(id_v);
				request.setAttribute("c", c);			
				 request.getRequestDispatcher("/skydash/pages/addVoyages/editVoyages.jsp").forward(request, response);
		}
		
		//================================================================================================================================
		if(path.equals("/skydash/pages/VoyageAcc/editVoyagesAcc.php")){
			IVoyage_acc voyageDao = new VoyageImp_acc(DBconnect.getConnection());
			int id_v= Integer.parseInt(request.getParameter("id_Voyage_acc"));
			System.out.println(id_v);
			Voyage_acc c = voyageDao.getVoyage_accParId(id_v);
				request.setAttribute("c", c);			
				 request.getRequestDispatcher("/skydash/pages/VoyageAcc/editVoyagesAcc.jsp").forward(request, response);
		}
		if(path.equals("/skydash/pages/VoyageAcc/deleteVoyagesAcc.php")) {
			System.out.println("dkhlat");
			
			try {
				String idVoyage_acc = request.getParameter( "id_Voyage_acc" );
				System.out.println(idVoyage_acc);
				System.out.println("deleted");
				voyageAccDao.deleteVoyage_acc_acc(Integer.parseInt(idVoyage_acc));
				System.out.println("deleted");
				//chargement de liste des voyages
				List<Voyage_acc> voyages =  voyageAccDao.ListVoyage_acc();
				request.setAttribute("voyages", voyages ); 
			} catch (Exception e) {
				request.setAttribute("erreur", "Une erreur est survenue lors de la suppression du voyage ");	
			} finally {
				request.getRequestDispatcher("/skydash/pages/VoyageAcc/allVoyageAcc.php").forward(request, response);
			}	
		}
		
		
		//=================================================================================================================================
		if(path.equals("/skydash/pages/Client/allClients.php")) {
			IClient clientDao = new ClientImpl(DBconnect.getConnection());
			List<Client> clt= clientDao.ListeClient(); 
			request.setAttribute("client", clt);
			request.getRequestDispatcher("/skydash/pages/Client/allClients.jsp").forward(request, response);
		}
		
		if(path.equals("/skydash/pages/Client/deleteClient.php")){
			int id_client = Integer.parseInt(request.getParameter("id_client"));
			try {
				clientDao.deleteClient(id_client);
			}catch(Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/skydash/pages/Client/allClients.php");
		}
		//========================================================================================
		//=================================================================================================================================

		if(path.equals("/skydash/pages/Client/PanierClient.php")) {
			  
			 IClient e= new ClientImpl(DBconnect.getConnection());
			 request.setAttribute("e", e);
			 
			IVoyage c= new VoyageImpl(DBconnect.getConnection());
			request.setAttribute("c", c);
			IVoyage_acc cc= new VoyageImp_acc(DBconnect.getConnection());
			request.setAttribute("cc", cc);
			IHotel h= new HotelImpl(DBconnect.getConnection());
			request.setAttribute("h", h);


			int id_client =Integer.parseInt(request.getParameter("id_client"));
			System.out.println(id_client);
			IPanier p = new PanierImpl(DBconnect.getConnection());
			IPanierAcc pp = new PanierAccImpl(DBconnect.getConnection());
			List<Panier> panier= p.PanierParIdClient(id_client); 
			System.out.println("vbn"+panier);
			List<PanierAcc> panierAcc= pp.PanierAccParIdClient(Integer.parseInt(request.getParameter("id_client"))); 

			request.setAttribute("panierAcc", panierAcc);
			request.setAttribute("panier", panier);
			System.out.println("id client est "+request.getParameter("id_client"));
			System.out.println("la liste est"+panierAcc);
			request.getRequestDispatcher("/skydash/pages/Client/PanierClient.jsp").forward(request, response);
			}

		if(path.equals("/skydash/pages/Client/deletePanier.php")){
			int id_panier = Integer.parseInt(request.getParameter("id_panier"));
			int id_client =Integer.parseInt(request.getParameter("id_client"));
			try {
				panierDao.deletePanier(id_panier,id_client);
			}catch(Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/skydash/pages/Client/PanierClient.php");
		}
		//==================================================================================================================================
		if(path.equals("/skydash/pages/Client/VoyageClient.php")) {
			  IClient e= new ClientImpl(DBconnect.getConnection());
			  request.setAttribute("e", e);
			  
			  String id_client = request.getParameter("id_client");
			  String id_voyage = request.getParameter("id_Voyage");
			
				
			IVoyage p = new VoyageImpl(DBconnect.getConnection());
			Voyage voyage= p.getVoyage(request.getParameter("id_Voyage")); 
			request.setAttribute("v", voyage);
			
			IVoyage voyageDao = new VoyageImpl();
			List<Voyage> voyages =  voyageDao.ListPanierVoyageID(id_voyage);
			request.setAttribute("voyages", voyages );
			
			request.getRequestDispatcher("/skydash/pages/Client/VoyageClient.jsp").forward(request, response);
		}
		//================================================================================================================================
		if(path.equals("/skydash/pages/Client/ClientHotel.php")) {
			System.out.println("fghjk");
			  IClient e= new ClientImpl(DBconnect.getConnection());
			  request.setAttribute("e", e);
			  
			  String id_client = request.getParameter("id_client");
			  String id_hotel = request.getParameter("id_hotel");
			
				
			IHotel H = new HotelImpl(DBconnect.getConnection());
			Hotel hotel= H.getHotelParId(Integer.parseInt(id_hotel)); 
			request.setAttribute("v", hotel);
			
			IHotel hotelDao = new HotelImpl();
			List<Hotel> hoteles =  hotelDao.ListPanierHotelID(id_hotel);
			
			System.out.println(hoteles);
			request.setAttribute("hoteles", hoteles );
			
			request.getRequestDispatcher("/skydash/pages/Client/ClientHotel.jsp").forward(request, response);
		}
		if(path.equals("/skydash/pages/addHotels/editHotels.php")){
			System.out.println("fghj");
			IHotel hotelDao = new HotelImpl(DBconnect.getConnection());
			int id_h= Integer.parseInt(request.getParameter("id_h"));
			System.out.println(id_h);
			Hotel c = hotelDao.getHotelParId(id_h);
			System.out.println(c);
			request.setAttribute("hotelModel", c);			
			request.getRequestDispatcher("/skydash/pages/addHotels/editHotels.jsp").forward(request, response);
		}
		//=================================================================================================================================
		
		//==================================================================================================================================
		if(path.equals("/pacific-main/detailsVAcc.php")) {
			System.out.println("ok");
			  String id_Voyage_acc = request.getParameter("id_c");
			  Voyage_acc v= voyageAccDao.getVoyage_acc(id_Voyage_acc);
			  System.out.println(v);
			  System.out.println("v"+v.getType_acc());
			  
			  request.setAttribute("v", v);
			  
			  	HttpSession session = request.getSession();
				String nom = (String) session.getAttribute("nom");
				String password = (String) session.getAttribute("password");
				
				Client m = clientDao.getClient(nom,password);
				int id_client = m.getId_client();
				System.out.println(id_client);
				
				
			  request.getRequestDispatcher("/pacific-main/detailsVAcc.jsp").forward(request,response);
			  
			 
			  }
		//==================================================================================================================================
		if(path.equals("/pacific-main/details.php")) {
			  String idControleur = request.getParameter( "id_c" );
				System.out.println(idControleur);
				Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
				if(utilisateurConnecte != null) {
					request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
					int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
					request.setAttribute("nbrCmd", nbrCmd);
				}
			System.out.println("ok");
			  String id_Voyage = request.getParameter("id_c");
			  Voyage v= voyageDao.getVoyage(id_Voyage);
			  request.setAttribute("v", v);
			  
			 List<Hotel> hotels =  hotelDao.getHotelParDEST_CONT(v.getContinent());
			
			 System.out.println(hotels);
			 request.setAttribute("hotels", hotels );
			 
			  	HttpSession session = request.getSession();
				String nom = (String) session.getAttribute("nom");
				String password = (String) session.getAttribute("password");
				
				Client m = clientDao.getClient(nom,password);
				int id_client = m.getId_client();
				System.out.println(id_client);
				
				
			  request.getRequestDispatcher("/pacific-main/details.jsp").forward(request,response);
			  
			 
			  }
		
		//====================================================================================================================
		if(path.equals("/pacific-main/detailsHotels.php")) {
			  String idControleur = request.getParameter( "id_c" );
				System.out.println(idControleur);
				Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
				if(utilisateurConnecte != null) {
					request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
					int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
					request.setAttribute("nbrCmd", nbrCmd);
				}
			System.out.println("ok");
			  int id_hotel = Integer.parseInt(request.getParameter("id_h"));
			  System.out.println("id hotel "+ id_hotel);
			  Hotel v= hotelDao.getHotelParId(id_hotel);
			  request.setAttribute("v", v);
			  System.out.println(v.getPrix_hotel());
			  
			  String id_Voyage = request.getParameter("id_c");
			  Voyage vo= voyageDao.getVoyage(id_Voyage);
			  request.setAttribute("vo", vo);
			
			  	HttpSession session = request.getSession();
				String nom = (String) session.getAttribute("nom");
				String password = (String) session.getAttribute("password");
				
				Client m = clientDao.getClient(nom,password);
				int id_client = m.getId_client();
				System.out.println(id_client);
				
				
			  request.getRequestDispatcher("/pacific-main/detailsHotels.jsp").forward(request,response);
			  
			 
			  }
		//==========================================================================================================================
		if (path.equals("/pacific-main/logout.php")) {
			System.out.println("logOut");
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("/pacific-main/index1.php").forward(request, response);
			}
		if (path.equals("/skydash/logoutA.php")) {
			System.out.println("loghjkOut");
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("/skydash/pages/samples/login_admin.jsp").forward(request, response);
			}
		//==========================================================================================================================
		if(path.equals("/pacific-main/panier.php")) {
			HttpSession session = request.getSession();
			String nom = (String) session.getAttribute("nom");
			String password = (String) session.getAttribute("password");
			
			Client m = clientDao.getClient(nom,password);
			int id_client = m.getId_client();
			String idUtilisateur=Integer.toString(id_client);
			
			String idVoyage = request.getParameter( "id_c" );
			String idHotel= request.getParameter( "id_h" );
			Date date_res= new java.sql.Date(System.currentTimeMillis());
		
	    	if(idVoyage != null && idUtilisateur != null && idHotel != null) {
	    		
				request.setAttribute("e", voyageDao);
				request.setAttribute("h", hotelDao);

				Voyage v2=voyageDao.getVoyage(idVoyage);
	    		
	    		Panier p=new Panier(Integer.parseInt(idVoyage),Integer.parseInt(idUtilisateur),	Integer.parseInt(idHotel),date_res);
	    		panierDao.addPanier(p);
	    		
	    		int nbrCmd = panierDao.CountPanier(Integer.parseInt(idUtilisateur));
				request.setAttribute("nbrCmd", nbrCmd);
				
				List<Panier> panier =  panierDao.ListPanier(idUtilisateur);
				List<PanierAcc> panierAcc =  panierAccDao.ListPanierAcc(idUtilisateur);

				request.setAttribute("idUtilisateur", idUtilisateur);
				request.setAttribute("panier", panier );
				request.setAttribute("panierVACC", panierAcc );
				request.setAttribute("e", voyageDao);
				request.setAttribute("v", voyageAccDao);
				request.setAttribute("h", hotelDao);

	    		request.getRequestDispatcher("/pacific-main/panier.jsp").forward(request, response);
	    	} 
	    	
	    	else if(idVoyage == null && idUtilisateur != null){
	    		System.out.println("ghjk");
	    	IPanier panierDao = new PanierImpl();
	    	List<Panier> panier =  panierDao.ListPanier(idUtilisateur);
			List<PanierAcc> panierAcc =  panierAccDao.ListPanierAcc(idUtilisateur);

			request.setAttribute("idUtilisateur", idUtilisateur);
			request.setAttribute("panier", panier );
			request.setAttribute("panierVAcc", panierAcc );
			request.setAttribute("e", voyageDao);
			request.setAttribute("v", voyageAccDao);
		
			request.setAttribute("h", hotelDao);
			System.out.println("ton panier est :"+ panierAcc);
			
			int nbrCmd = panierDao.CountPanier(Integer.parseInt(idUtilisateur));
			request.setAttribute("nbrCmd", nbrCmd);
			
			
			
			request.getRequestDispatcher("/pacific-main/panier.jsp").forward(request, response);
	    	}
	    	else{
	    		request.getRequestDispatcher("/pacific-main/index.jsp").forward(request, response);
	    	}
		}
		//===========================================================================================================================================
		if(path.equals("/pacific-main/panierAcc.php")) {
			System.out.println("dfghj");
			HttpSession session = request.getSession();
			String nom = (String) session.getAttribute("nom");
			String password = (String) session.getAttribute("password");
			
			Client m = clientDao.getClient(nom,password);
			int id_client = m.getId_client();
			String idUtilisateur=Integer.toString(id_client);
			
			String idVoyage_acc = request.getParameter( "id_VAcc" );
			Date date_res= new java.sql.Date(System.currentTimeMillis());
	    	if(idVoyage_acc != null && idUtilisateur != null) {
	    		
				request.setAttribute("e", voyageAccDao);

				Voyage_acc v2=voyageAccDao.getVoyage_acc(idVoyage_acc);
	    		
	    		PanierAcc p=new PanierAcc(Integer.parseInt(idVoyage_acc),Integer.parseInt(idUtilisateur),date_res);
	    		panierAccDao.addPanierAcc(p);
	    		
	    		int nbrCmd = panierAccDao.CountPanierAcc(Integer.parseInt(idUtilisateur));
				request.setAttribute("nbrCmd", nbrCmd);
				
				List<PanierAcc> panier =  panierAccDao.ListPanierAcc(idUtilisateur);
				request.setAttribute("idUtilisateur", idUtilisateur);
				request.setAttribute("panierVAcc", panier );
				request.setAttribute("e", voyageAccDao);
	    		request.getRequestDispatcher("/pacific-main/panier.php").forward(request, response);
	    	} 
	    	
	    	else if(idVoyage_acc == null && idUtilisateur != null){
	    	List<PanierAcc> panier =  panierAccDao.ListPanierAcc(idUtilisateur);
	    	System.out.println(panier);
			request.setAttribute("idUtilisateur", idUtilisateur);
			request.setAttribute("panierVAcc", panier );
			request.setAttribute("e", voyageAccDao);
			
			int nbrCmd = panierAccDao.CountPanierAcc(Integer.parseInt(idUtilisateur));
			request.setAttribute("nbrCmd", nbrCmd);
			
			
			
			request.getRequestDispatcher("/pacific-main/panier.jsp").forward(request, response);
	    	}
	    	else{
	    		request.getRequestDispatcher("/pacific-main/index.jsp").forward(request, response);
	    	}
		}
		//====================================================================================================================================
		if(path.equals("/pacific-main/supprimer_panier.php")) {
			HttpSession session = request.getSession();
			String nom = (String) session.getAttribute("nom");
			String password = (String) session.getAttribute("password");
			
			Client m = clientDao.getClient(nom,password);
			int id_client = m.getId_client();
			String idUtilisateur=Integer.toString(id_client);
			
			String idHotel=request.getParameter("id_hotel");
			
			String id_v = request.getParameter("id_c");
			System.out.println(id_v);
			
			String id_p = request.getParameter("id_panier");
			System.out.println(id_p);
			  try {
			  panierDao.deletePanierC(Integer.parseInt(id_p));
			  }
			  
			  catch(Exception e) {
			  e.printStackTrace(); }
			  request.getRequestDispatcher("/pacific-main/panier.php").forward(request, response);
		}
		//==================================================================================================================================
		if(path.equals("/pacific-main/supprimer_panierAcc.php")) {
			HttpSession session = request.getSession();
			String nom = (String) session.getAttribute("nom");
			String password = (String) session.getAttribute("password");
			
			Client m = clientDao.getClient(nom,password);
			int id_client = m.getId_client();
			String idUtilisateur=Integer.toString(id_client);
			
			
			String id_v = request.getParameter("id_panier_acc");
			System.out.println(id_v);
			
			String id_p = request.getParameter("id_panier_acc");
			System.out.println(id_p);
			  try {
			  panierAccDao.deletePanierAccC(Integer.parseInt(id_p));
			  }
			  
			  catch(Exception e) {
			  e.printStackTrace(); }
			  request.getRequestDispatcher("/pacific-main/panier.php").forward(request, response);
		}
		
	
		//===================================================================================================================================
		if(path.equals("/pacific-main/panierC.php")) {
			HttpSession session = request.getSession();
			String nom = (String) session.getAttribute("nom");
			String password = (String) session.getAttribute("password");
			
			Client m = clientDao.getClient(nom,password);
			int id_client = m.getId_client();
			String idUtilisateur=Integer.toString(id_client);
			
			String idHotel=request.getParameter("id_hotel");
			
			String id_v = request.getParameter("id_c");
			
			  try {
			
			 
			  List<Panier> panier = panierDao.ListPanier(idUtilisateur);
			  System.out.println(panier);
			  request.setAttribute("panier", panier );
			  
			  int nbrCmd = panierDao.CountPanier(Integer.parseInt(idUtilisateur));
			  request.setAttribute("nbrCmd", nbrCmd); }
			 
		catch(Exception e) {
			  e.printStackTrace(); }
			  request.getRequestDispatcher("/pacific-main/panierC.jsp").forward(request, response);
		}
		//=======================================================================================================================
		if(path.equals("/pacific-main/voyage.php")) {
			System.out.println("dkhelt");
    		Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null) {
				request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
				int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				request.setAttribute("nbrCmd", nbrCmd);
			}
			String idControleur = request.getParameter( "id_c" );
			System.out.println(idControleur);
			//String idControleur ="2";
	    	if(idControleur == null) {
				List<Voyage> voyages =  voyageDao.ListVoyage();	
				request.setAttribute("voyages", voyages);	
				
				request.getRequestDispatcher("/pacific-main/voyage.jsp").forward(request, response);
			}			
	    	
	    	else {
	    		System.out.println(idControleur);
				Voyage voyageAffichee = voyageDao.getVoyage(idControleur);  
				
				request.setAttribute("voyage", voyageAffichee);			
				request.getRequestDispatcher("/pacific-main/details.jsp").forward(request, response);
			}
		}
		//========================================================================================================================
		if(path.equals("/pacific-main/VoyageAcc.php")) {
			System.out.println("dkhelt");
    		Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null) {
				request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
				int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				request.setAttribute("nbrCmd", nbrCmd);
			}
			String idControleur = request.getParameter( "id_c" );
			System.out.println(idControleur);
			//String idControleur ="2";
	    	if(idControleur == null) {
	    		
				List<Voyage_acc> voyagesAcc =  voyageAccDao.ListVoyage_acc();	
				System.out.println(voyagesAcc);
				request.setAttribute("voyages", voyagesAcc);	
				
				request.getRequestDispatcher("/pacific-main/VoyageAcc.jsp").forward(request, response);
			}			
	    	
	    	else {
	    		System.out.println(idControleur);
				Voyage_acc voyageAccAffichee = voyageAccDao.getVoyage_acc(idControleur);  
				
				request.setAttribute("voyage", voyageAccAffichee);			
				request.getRequestDispatcher("/pacific-main/detailsVAcc.jsp").forward(request, response);
			}
		}
		//=============================================================================================================================
		if(path.equals("/pacific-main/hotel.php")) {
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null) {
				request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
				int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				request.setAttribute("nbrCmd", nbrCmd);
			}
			 IHotel c = new HotelImpl(DBconnect.getConnection());
			 List<Hotel> hh= c.ListesHotels();
			 
			 request.setAttribute("hh", hh);

			request.getRequestDispatcher("/pacific-main/hotel.jsp").forward(request, response);
			}

		//=========================================================================================================================
		if(path.equals("/skydash/pages/addHotels/allHotels.php")) {
			
			  IHotel c = new HotelImpl(DBconnect.getConnection());
			  List<Hotel> h = c.ListesHotels();
			  
			  request.setAttribute("h", h);
	
			request.getRequestDispatcher("/skydash/pages/addHotels/allHotels.jsp").forward(request, response);
		}
		//=====================================================================================================================
		if(path.equals("/skydash/pages/addHotels/deleteHotels.php")){
			int id = Integer.parseInt(request.getParameter("id_hotel"));
			try {
				hotelDao.deleteHotel(id);
			}catch(Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/skydash/pages/addHotels/allHotels.php");
		}
		//======================================================================================================================
		if(path.equals("/pacific-main/index.php")) {
			System.out.println("dkhelt");
			    String idControleur = request.getParameter( "id_c" );
			System.out.println(idControleur);
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null) {
				request.setAttribute("idUtilisateur", utilisateurConnecte.getId_client());
				int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				request.setAttribute("nbrCmd", nbrCmd);
			}
			    if(idControleur == null) {
			List<Voyage> voyagess =  voyageDao.ListVoyage(); 
			System.out.println("fghjk"+voyagess);
			request.setAttribute("voyagess", voyagess); 
			System.out.println("la liste est"+voyagess);

			       
			List<Voyage_acc> voyagesAcc =  voyageAccDao.ListVoyage_acc(); 
			System.out.println(voyagesAcc);
			request.setAttribute("voyagesAcc", voyagesAcc); 

			 List<Hotel> hh = hotelDao.ListesHotels();
			 System.out.println(hh);
			 request.setAttribute("hh", hh);
			 
			request.getRequestDispatcher("/pacific-main/index.jsp").forward(request, response);
			       
			} 
			  }
		
		if(path.equals("/pacific-main/index1.php")) {
			System.out.println("dkhelt");
			    String idControleur = request.getParameter( "id_c" );
			System.out.println(idControleur);
			
			    if(idControleur == null) {
			List<Voyage> voyagess =  voyageDao.ListVoyage(); 
			System.out.println("fghjk"+voyagess);
			request.setAttribute("voyagess", voyagess); 
			System.out.println("la liste est"+voyagess);

			       
			List<Voyage_acc> voyagesAcc =  voyageAccDao.ListVoyage_acc(); 
			System.out.println(voyagesAcc);
			request.setAttribute("voyagesAcc", voyagesAcc); 

			 List<Hotel> hh = hotelDao.ListesHotels();
			 System.out.println(hh);
			 request.setAttribute("hh", hh);
			 
			request.getRequestDispatcher("/pacific-main/index1.jsp").forward(request, response);
			       
			} 
			  }

		//======================================================================================================================
		if(request.getServletPath().equals("/skydash/pages/Client/pdf.php")) {
			try {
                response.setContentType("application/pdf");
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                String currentDateTime = dateFormatter.format(new java.util.Date());
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=liste " + currentDateTime + ".pdf";
                response.setHeader(headerKey, headerValue);
                IClient clt = new ClientImpl(DBconnect.getConnection());
                List<Client> listUsers = clt.ListeClient();
                System.out.println(listUsers);
                PDFClient exporter = new PDFClient(listUsers);
                exporter.export1(response);
            }
			catch(Exception e) {
				response.sendRedirect("page_404.jsp");
                e.printStackTrace();
            }
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		System.out.println("path : " + path);

		
		
		if(path.equals("/material-design-sign-up-form/dist/register.php")) {
			Client user = new Client();
			System.out.println("ici");

			  user.setNom(request.getParameter("username"));
			  user.setEmail(request.getParameter("email"));
			  user.setPassword(hashPassword(request.getParameter("password")));
			  user.setTel(request.getParameter("telephone"));


			  System.out.println(user.toString());
			  ClientImpl newclt = new ClientImpl();
			  

			  if (clientDao.register(user)) {
			   System.out.println("connexion avec bd");
			response.sendRedirect(request.getContextPath() + "/pacific-main/login.jsp");
			}



			} 

			
		if(path.equals("/skydash/pages/samples/login_admin.php")) {
			System.out.println("dkhelt");
			String nom = request.getParameter("nom");
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			Admin login = new Admin();
			login.setNom(nom);
			login.setPassword(password);
			System.out.println("ouiii");
			try {

			if (adminDao.login(nom, password)) {
			HttpSession session1 = request.getSession();
			session1.setAttribute("nom",nom);
			session1.setAttribute("password", password);

			Admin admin= adminDao.getAdmin(nom, password);
			session1.setAttribute("session", admin);
			Admin ad = (Admin) request.getSession().getAttribute("session");

			/*if(utilisateurConnecte != null) { 
			int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
			request.setAttribute("nbrCmd", nbrCmd);
			}*/

			response.sendRedirect(request.getContextPath() + "/skydash/index.jsp");
			}
			}catch (Exception e) {
			request.getRequestDispatcher("/skydash/pages/samples/login_admin.php").forward(request, response);

			}

			}


		
		
		if(path.equals("/pacific-main/login_client.php")) {
			String nom = request.getParameter("nom");
			String password = hashPassword(request.getParameter("password"));



			Client login = new Client();


			login.setNom(nom);
			login.setPassword(password);

			try {

			if (clientDao.login(nom, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("nom",nom);
			session.setAttribute("password", password);

			Client utilisateur = clientDao.getClient(nom, password);
			session.setAttribute("session", utilisateur);
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");

			if(utilisateurConnecte != null) { 
			int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
			request.setAttribute("nbrCmd", nbrCmd);
			}
			response.sendRedirect(request.getContextPath() + "/pacific-main/index.php");

			}
			}catch (Exception e) {
			request.getRequestDispatcher("/pacific-main/login_client.php").forward(request, response);

			}

			}

				
			
		
		
		// ----------------------------------------------------------------------------

		if(path.equals("/skydash/pages/addVoyages/addVoyage.php")) {
			
			String destination = request.getParameter("destination");
			String ville = request.getParameter("continent");
			String type = request.getParameter("type");
			String date = request.getParameter("date");
			int duree = Integer.valueOf(request.getParameter("duree"));
			double prix = Double.valueOf(request.getParameter("prix"));	
			String theme = "individuel";
			Part img = request.getPart("image");
			
			
			InputStream inputImg = null;
			if(img!=null)
			{
				inputImg = img.getInputStream();
			}
			
			Voyage nouveauVoyage = new Voyage(destination,ville, type, date, duree, inputImg, theme,prix);
			IVoyage VoyageModel = new VoyageImpl(DBconnect.getConnection());
			if (VoyageModel.saveVoyage(nouveauVoyage)) {
				response.sendRedirect(request.getContextPath() + "/skydash/pages/addVoyages/allVoyage.php");
			} 
	}
		
		//===============================================================================================================================
		if(path.equals("/skydash/pages/VoyageAcc/addVoyageAcc.php")) {
			String destination_acc = request.getParameter("destination_acc");
			String ville_acc = request.getParameter("continent_acc");
			String type_acc = request.getParameter("type_acc");
			String date_acc = request.getParameter("date_acc");
			int duree_acc = Integer.valueOf(request.getParameter("duree_acc"));
			double prix_acc = Double.valueOf(request.getParameter("prix_acc"));	
			String theme_acc = "Accompagne";
			String hebergement_acc = request.getParameter("hebergement_acc");
			Part img= request.getPart("image_acc");
			String activite = request.getParameter("activite");
			String genre = request.getParameter("genre");
			String guide = request.getParameter("guide");

			
			InputStream inputImg = null;
			if(img!=null)
			{
				inputImg = img.getInputStream();
			}
			
			Voyage_acc nouveauVoyageAcc = new Voyage_acc(destination_acc,ville_acc, type_acc, date_acc, duree_acc, inputImg, theme_acc, hebergement_acc,prix_acc,activite,genre,guide);
			IVoyage_acc VoyageAccModel = new VoyageImp_acc(DBconnect.getConnection());
			if (VoyageAccModel.saveVoyage_acc(nouveauVoyageAcc)) {
				response.sendRedirect(request.getContextPath() + "/skydash/pages/VoyageAcc/allVoyageAcc.php");
			} 
	}
		//================================================================================================================================
		if(path.equals("/skydash/pages/addHotels/addHotels.php")) {
			
			String  nom_hotel =request.getParameter("nom_hotel"); 
			String  ville =request.getParameter("ville"); 
			double  prix_hotel = Double.parseDouble(request.getParameter("prix_hotel"));
			int etoile_hotel = Integer.parseInt(request.getParameter("etoile_hotel"));
			
			Part img = request.getPart("image_hotel");
			
			
			InputStream inputImg = null;
			if(img!=null)
			{
				inputImg = img.getInputStream();
			}
			Hotel Hotel = new Hotel(nom_hotel,prix_hotel,etoile_hotel,ville,inputImg);
			IHotel HotelModel = new HotelImpl(DBconnect.getConnection());
				
			if (HotelModel.saveHotel(Hotel)) {
				response.sendRedirect(request.getContextPath() + "/skydash/pages/addHotels/allHotels.php");
			} 
				}
		
		//===========================================================================================================================
			if(path.equals("/skydash/pages/addHotels/updateHotels.php")){
				
				int id_hotel = Integer.parseInt(request.getParameter("id_hotel"));
				String nom_hotel1 = request.getParameter("nom_hotel");
				String ville = request.getParameter("ville");
				double prix_hotel = Double.parseDouble(request.getParameter("prix_hotel"));
				int etoile_hotel = Integer.parseInt(request.getParameter("etoile_hotel"));
				
				
				Hotel c = new Hotel(id_hotel,nom_hotel1,prix_hotel,etoile_hotel,ville);			
				IHotel h = new  HotelImpl(DBconnect.getConnection());
				
				h.updateHotel(c,id_hotel);
				
				response.sendRedirect(request.getContextPath() + "/skydash/pages/addHotels/allHotels.php");
			}
	//========================================================================================================================
			
			

			if(path.equals("/pacific-main/contact.php")){
			   System.out.println("dkhelt");
			 
		
					String nom = request.getParameter("nom");
					String email = request.getParameter("email");
					String subject = request.getParameter("subject");
					String textMessage = request.getParameter("textMessage");
				
					System.out.println("nom :" + nom);
					System.out.println("email :" + email);
					System.out.println("subject :" + subject);
					System.out.println("textMessage :" + textMessage);
					
					String USER_NAME = "elkaam2001@gmail.com";
			   	    String PASSWORD = "Hiba2001";
			   	    
			       String[] to = { email };
			       String body = "A costumer message";

			       System.out.println("sent");
			       Properties props = System.getProperties();
			       String host = "smtp.gmail.com";
			       props.put("mail.smtp.starttls.enable", "true");
			       props.put("mail.smtp.host", host);
			       props.put("mail.smtp.user", USER_NAME);
			       props.put("mail.smtp.password", PASSWORD);
			       props.put("mail.smtp.port", "587");
			       props.put("mail.smtp.auth", "true");

			       Session session = Session.getDefaultInstance(props);
			       MimeMessage message = new MimeMessage(session);

			       try {
			           message.setFrom(new InternetAddress(USER_NAME));
			           InternetAddress[] toAddress = new InternetAddress[to.length];

			           for( int i = 0; i < to.length; i++ ) {
			               toAddress[i] = new InternetAddress(to[i]);
			           }

			           for( int i = 0; i < toAddress.length; i++) {
			               message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			           }

			           message.setSubject(subject);
			           message.setText(textMessage);
			           Transport transport = session.getTransport("smtp");
			           transport.connect(host, USER_NAME, PASSWORD);
			           transport.sendMessage(message, message.getAllRecipients());
			           System.out.println("Done");
			           request.getRequestDispatcher("/pacific-main/index.jsp").forward(request, response);
			           transport.close();
			           
			       }
			       catch (AddressException ae) {
			           ae.printStackTrace();
			       }
			       catch (MessagingException me) {
			           me.printStackTrace();
			       }
			           
			        } 
			//===============================================================================================================================
			
			  if(request.getServletPath().equals("/pacific-main/recherche.php")) {
				  System.out.println("hello");
				  try{
				  String destination = request.getParameter("destination");
				  String date = request.getParameter("date");
				  System.out.println(date);
				  double prix=Double.valueOf(request.getParameter("prix"));
				  System.out.println(prix);
				  List<Voyage> voyage =  voyageDao.RechercheAvancee(destination,date,prix); 
				  System.out.println(voyage);
				  request.setAttribute("voyages", voyage);

				  Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
				  if(utilisateurConnecte != null) {
				  int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				  request.setAttribute("nbrCmd", nbrCmd);
				  }
				  request.getRequestDispatcher("/pacific-main/recherche.jsp").forward(request, response);
				  }catch (Exception e) {
				  request.setAttribute("erreur", "Une erreur est survenue. Veuillez verifier que vous avez bien saisi les champs du formulaire "); 
				  request.getRequestDispatcher("recherche.jsp").forward(request, response);
				  }

				  }
			  //==========================================================================================================================
			  if(path.equals("/pacific-main/confirmation.php")){
				   System.out.println("dkhelt");
				  
				   		HttpSession session1 = request.getSession();
				   		String nom = (String) session1.getAttribute("nom");
				   		String password = (String) session1.getAttribute("password");
				   		System.out.println(nom);
				   		Client m = clientDao.getClient(nom,password);
				   		int id_client = m.getId_client();
				   		String email = clientDao.getEmail(id_client);
				   		
						//String nom = request.getParameter("ELKAAM HIBA");
						String subject = "Email de confirmation du paneir";
						String textMessage = "thanks for visiting our website.Your choice is successfully added.We invite you to pay the montant requested";
						//String email="elkaam.hiba@gmail.com";
						System.out.println("ghj:"+email);
						
						System.out.println("nom :" + nom);
						System.out.println("email :" + email);
						System.out.println("subject :" + subject);
						System.out.println("textMessage :" + textMessage);
						
						String USER_NAME = "elkaam2001@gmail.com";
				   	    String PASSWORD = "Hiba2001";
				   	    
				       String[] to = { email };
				       String body = "Confirmation message";

				       System.out.println("sent");
				       Properties props = System.getProperties();
				       String host = "smtp.gmail.com";
				       props.put("mail.smtp.starttls.enable", "true");
				       props.put("mail.smtp.host", host);
				       props.put("mail.smtp.user", USER_NAME);
				       props.put("mail.smtp.password", PASSWORD);
				       props.put("mail.smtp.port", "587");
				       props.put("mail.smtp.auth", "true");

				       Session session = Session.getDefaultInstance(props);
				       MimeMessage message = new MimeMessage(session);

				       try {
				           message.setFrom(new InternetAddress(USER_NAME));
				           InternetAddress[] toAddress = new InternetAddress[to.length];

				           for( int i = 0; i < to.length; i++ ) {
				               toAddress[i] = new InternetAddress(to[i]);
				           }

				           for( int i = 0; i < toAddress.length; i++) {
				               message.addRecipient(Message.RecipientType.TO, toAddress[i]);
				           }

				           message.setSubject(subject);
				           message.setText(textMessage);
				           Transport transport = session.getTransport("smtp");
				           transport.connect(host, USER_NAME, PASSWORD);
				           transport.sendMessage(message, message.getAllRecipients());
				           System.out.println("Done");
				           request.getRequestDispatcher("/pacific-main/index.jsp").forward(request, response);
				           transport.close();
				           
				       }
				       catch (AddressException ae) {
				           ae.printStackTrace();
				       }
				       catch (MessagingException me) {
				           me.printStackTrace();
				       }
				           
				        } 
			  //=====================================================================================================================
			  if(path.equals("/skydash/pages/addVoyages/updateVoyages.php")){
					int id_Voyage= Integer.parseInt(request.getParameter("id_Voyage"));
					System.out.println(id_Voyage);
				  	String destination = request.getParameter("destination");
				  	System.out.println("dest"+destination);
					String ville = request.getParameter("continent");
					System.out.println("ville"+ville);
					String type = request.getParameter("type");
					System.out.println("type"+type);
					
					int durree = Integer.valueOf(request.getParameter("durree"));
					System.out.println("vbn"+durree);
					double prix = Double.valueOf(request.getParameter("prix"));	
					System.out.println(prix);
					
					Voyage c = new Voyage(id_Voyage,destination,ville,type,durree,prix);			
					IVoyage h = new  VoyageImpl(DBconnect.getConnection());
					
					h.updateVoyage(c,id_Voyage);
					
					response.sendRedirect(request.getContextPath() + "/skydash/pages/addVoyages/allVoyage.php");
				}
			  //==========================================================================================================================
			  if(path.equals("/skydash/pages/VoyageAcc/updateVoyagesAcc.php")){
					int id_Voyage_acc= Integer.parseInt(request.getParameter("id_Voyage_acc"));
					System.out.println(id_Voyage_acc);
				  	String destination_acc = request.getParameter("destination_acc");
				  	System.out.println("dest"+destination_acc);
					String ville = request.getParameter("continent_acc");
					System.out.println("ville"+ville);
					String date_acc = request.getParameter("date_acc");
					String type_acc = request.getParameter("type_acc");
					System.out.println("type"+type_acc);
					
					int durree_acc = Integer.valueOf(request.getParameter("durree_acc"));
					System.out.println("vbn"+durree_acc);
					double prix_acc = Double.valueOf(request.getParameter("prix_acc"));	
					System.out.println(prix_acc);
					String hebergement_acc = request.getParameter("hebergement_acc");
					String activite = request.getParameter("activite");

					String genre = request.getParameter("genre");
					String guide = request.getParameter("guide");

					
					Voyage_acc c = new Voyage_acc(id_Voyage_acc,destination_acc,ville,type_acc,date_acc,durree_acc,hebergement_acc,prix_acc,activite,genre,guide);			
					IVoyage_acc h = new  VoyageImp_acc(DBconnect.getConnection());
					
					h.updateVoyageAcc(c,id_Voyage_acc);
					
					response.sendRedirect(request.getContextPath() + "/skydash/pages/VoyageAcc/allVoyageAcc.php");
				}
			  
			  if(request.getServletPath().equals("/pacific-main/recherchehotel.php")) {
				  System.out.println("oui");
				  try{
				  String hotel = request.getParameter("hotel");
				  String ville = request.getParameter("ville");
				  double prix = Double.parseDouble(request.getParameter("prix_hotel"));

				  List<Hotel> hotell =  hotelDao.RechercheAvanceeParhotel(hotel,ville,prix); 
				  System.out.println("les hotes sont "+hotell);
				  request.setAttribute("hotell", hotell);

				  Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
				  if(utilisateurConnecte != null) {
				  int nbrCmd = panierDao.CountPanier(utilisateurConnecte.getId_client());
				  request.setAttribute("nbrCmd", nbrCmd);
				  }
				  request.getRequestDispatcher("/pacific-main/recherchehotel.jsp").forward(request, response);
				  }catch (Exception e) {
				  request.setAttribute("erreur", "Une erreur est survenue. Veuillez verifier que vous avez bien saisi les champs du formulaire "); 
				  request.getRequestDispatcher("recherchehotel.jsp").forward(request, response);
				  }
				  
				  

				  }

	}
			  //=====================================================================================================================
		

	
	
			  public String hashPassword(String password) {
				    String passwordToHash = password;
				    String generatedPassword = null;

				    try 
				    {
				      // Creer MessageDigest comme instance de MD5
				      MessageDigest md = MessageDigest.getInstance("MD5");

				      // ajouter password bytes to digest
				      md.update(passwordToHash.getBytes());

				      // avoir hash's bytes
				      byte[] bytes = md.digest();

				      // This bytes[] est en format decimal. le transformer en hexadecimal format
				      StringBuilder sb = new StringBuilder();
				      for (int i = 0; i < bytes.length; i++) {
				        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				      }

				      // avoir le password complete hashed en hex format
				      generatedPassword = sb.toString();
				    } catch (NoSuchAlgorithmException e) {
				      e.printStackTrace();
				    }
				    System.out.println(generatedPassword);
				    return generatedPassword;
				  }

				}

	


