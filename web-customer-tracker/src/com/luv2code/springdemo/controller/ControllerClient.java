package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.dao.ClientDAO;
import com.luv2code.entity.Client;
import com.luv2code.service.ClientService;
import com.luv2code.util.Util;


@RequestMapping("/client")
@Controller
public class ControllerClient {
	
	@Autowired
	private ClientService clientService;
	
//	@GetMapping("/liste")
//	public String listerClients(Model leModele) {
//		
//		//récupérer les clients du service
//		List<Client> clients = clientService.getClients();
//		
//		//ajouter les clients au modèle
//		leModele.addAttribute("clients", clients);
//		
//		return "liste-clients";
//	}
	
	@GetMapping("afficherFormulaireAjout")
	public String afficherFormulaireAjout(Model leModele) {
		
		//créer un attribut pour le modèle afin de lier les données du formulaire
		Client leClient = new Client();
		
		leModele.addAttribute("client", leClient);
		
		return "formulaire-client";
	}
	
	@PostMapping("/enregistrerClient")
	public String enregistrerClient(@ModelAttribute("client") Client leClient) {
		
		//enregistrer le Client en utilisant la couche Service
		clientService.enregistrerClient(leClient);
		
		return "redirect:/client/liste";
	}
	
	@GetMapping("afficherFormulaireMaj")
	public String afficherFormulaireMaj(@RequestParam("clientId")int Id, Model leModele) {
		
		// récupérer le client depuis la couche de service
		Client leClient = clientService.getClient(Id);
		
		// définir le client comme un attribut du modèle pour préremplir le formulaire
		leModele.addAttribute("client",leClient);
		
		// envoyer vers notre formulaire
		return "formulaire-client";
		
		
	}
	
	@GetMapping("/supprimerClient")
	public String supprimerClient(@RequestParam("clientId")int Id, Model leModele) {
		
		// supprimer le client
		clientService.supprimerClient(Id);
		
		// envoyer vers notre formulaire
		return "redirect:/client/liste";
		
		
	}
	
	@GetMapping("/recherche")
	public String rechercherClient(@RequestParam("nomCherche")String nomCherche, Model leModele) {
	
	// chercher les Clients depuis la couche service
	List<Client> lesClients = clientService.rechercherClients(nomCherche);
	
	// ajouter la liste obtenue au modèle
	leModele.addAttribute("clients",lesClients);
	
	return "liste-clients";
		
		
	}
	
	@GetMapping("/liste")
	public String listeClient(Model leModele, @RequestParam(required=false) String tri) {
		
		// récupérer les clients depuis le service
		List<Client> lesClients = null;
		
		// vérifier si le tri est activé
		if(tri != null) {
			
			int champTri = Integer.parseInt(tri);
			lesClients = clientService.getClients(champTri);
			
		}
		else { // en l'absence du champ de tri, on trie par noms de famille
			lesClients =
					clientService.getClients(Util.NOM);
		}
		
		// ajouter au modèle
		leModele.addAttribute("clients",lesClients);
		
		return "liste-clients";
		
		
	}
	
	
}






