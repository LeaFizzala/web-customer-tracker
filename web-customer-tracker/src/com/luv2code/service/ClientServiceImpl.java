package com.luv2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.dao.ClientDAO;
import com.luv2code.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	// need to inject ClientDAO
	
	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional
	public List<Client> getClients(int champTri) {
		
		
		return clientDAO.getClients(champTri);
	}

	@Override
	@Transactional
	public void enregistrerClient(Client leClient) {

		clientDAO.enregistrerClient(leClient);
		
	}

	@Override
	@Transactional
	public Client getClient(int id) {
	
		return clientDAO.getClient(id);
	}

	@Override
	public void supprimerClient(int id) {

		clientDAO.supprimerClient(id);
		
	}

	@Override
	public List<Client> rechercherClients(String nomCherche) {
	
		return clientDAO.rechercherClient(nomCherche);
	}

}
