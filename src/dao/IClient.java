package dao;

import java.util.List;

import entities.Client;

	public interface IClient {
		public void addClient(Client c);
		public Client getClient(String id_c);
		public boolean login(String nom, String mdp);
		public Client getClient(String nom, String mdp);
		public boolean register(Client clt);
		public  List<Client> ListeClient();
		public void deleteClient(int id);
		public  String getEmail(int id_client);
	}

