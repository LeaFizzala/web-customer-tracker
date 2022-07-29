package com.luv2code.dao;

import java.util.List;

import com.luv2code.entity.Client;

public interface ClientDAO {
	
	public List<Client> getClients(int champTri);

	public void enregistrerClient(Client leClient);

	public Client getClient(int id);

	public void supprimerClient(int id);

	public List<Client> rechercherClient(String nomCherche);
		

}
