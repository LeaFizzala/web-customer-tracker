package com.luv2code.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.entity.Client;
import com.luv2code.util.Util;

@Repository
public class ClientDAOImpl implements ClientDAO {
	
	//injecter la dépendence sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Client> getClients(int champTri) {

		//récupérer la session Hibernate en cours
		Session sessionEnCours = sessionFactory.getCurrentSession();
		
		String nomChamp = null;
		
		switch(champTri) {
		
		case Util.PRENOM : 
			nomChamp = "prenom";
			break;
		case Util.NOM:
			nomChamp = "nom";
			break;
		case Util.EMAIL:
			nomChamp = "email";
			break;
			
		default:
			nomChamp = "nom";
		
		}
		
		String requete = "from Client order by " + nomChamp;
		
		// créer une requête
		Query<Client> laRequete = sessionEnCours.createQuery(requete , Client.class); // on appelle la classe et non pas la table dans la bdd
		
		// exécuter la requête et obtenir une liste de résultats
		List<Client> clients = laRequete.getResultList();
		
		//retourner les résultats
		return clients;
		
	
	}

	@Override
	public void enregistrerClient(Client leClient) {

		//récupérer la session Hibernate en cours
		Session sessionEnCours = sessionFactory.getCurrentSession();
		
		// enregistrer le client
		sessionEnCours.saveOrUpdate(leClient);
		
		
	}

	@Override
	@Transactional
	public Client getClient(int id) {
		
		 //récupérer la session Hibernate en cours
		Session session = sessionFactory.getCurrentSession();
		
		// récupérer depuis la bdd en utilisant la clé primaire
		Client leClient = session.get(Client.class, id);
		
		return leClient;
	}

	@Override
	@Transactional
	public void supprimerClient(int id) {

		// récupérer la session Hibernate en cours
		Session sessionEnCours = sessionFactory.getCurrentSession();
		
		// supprimer l'objet grâce à sa clé primaire
		Query laRequete =
				sessionEnCours.createQuery("delete from Client where id=:clientId");
		laRequete.setParameter("clientId", id);
		
		laRequete.executeUpdate();
	}

	@Override
	@Transactional
	public List<Client> rechercherClient(String nomCherche) {

		//récupérer la session en cours
		Session sessionEnCours = sessionFactory.getCurrentSession();
		
		// créer une requête
		Query listeClients = null;
		
		// chercher par nom seulement si le nomCherche n'est pas vide
		
		if(nomCherche != null && nomCherche.trim().length()>0) {
			
			// chercher par prénom OU nom, en minuscules
			listeClients = sessionEnCours.createQuery("from Client where lower(prenom) like :leNom or lower(nom) like :leNom", Client.class);
			
			listeClients.setParameter("leNom", "%" + nomCherche.toLowerCase() + "%");
		}
		else {
			// le nomCherche est vide on obtient tous les les clients
			listeClients = sessionEnCours.createQuery("from Client", Client.class);
			
		}
		
		// effectuer la recherche et obtenir le résultat
		List<Client> clients = listeClients.getResultList();
		
		return clients;
		
	}
	
	
	

}










