package com.luv2code.service;

import java.util.List;

import com.luv2code.entity.Client;

public interface ClientService {
	
	public List<Client> getClients(int champTri);
	
	public void enregistrerClient(Client leClient);

	public Client getClient(int id);

	public void supprimerClient(int id);

	public List<Client> rechercherClients(String nomCherche);

}
