package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.Echange_interne;
import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.services.ICarteService;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DresseurServiceImpl implements IDresseurService {

	private final DresseurRepository repository;
	private final IPokemonService pokemonService;
	private final ICarteService carteService;

	public DresseurServiceImpl(DresseurRepository repository, PokemonServiceImpl pokemonService, ICarteService carteService) {
		this.repository = repository;
		this.pokemonService = pokemonService;
        this.carteService = carteService;
    }

	@Override
	public List<Dresseur> findAll() {
		return repository.findAllByDeletedAtNull();
	}

	@Override
	public Dresseur findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public void capturerPokemon(String uuid, CapturePokemon capturePokemon) {
		Dresseur dresseur = findById(uuid);
		Pokemon pokemon = pokemonService.findById(capturePokemon.getUuid());
		dresseur.getPokemonList().add(pokemon);
		repository.save(dresseur);
	}

	@Override
	public void init_deck_attack(String uuid){
		Dresseur dresseur = findById(uuid);
		List<Carte> carteList_Attack = dresseur.getCartes_attaques();
		if (carteList_Attack.size() != 5)
		{
			List<Carte> carteList = dresseur.getCartes();
			for (int i = 0; i < 5; i++) {
				carteList_Attack.add(carteList.get(0));
				carteList.remove(0);
			}
			dresseur.setCartes(carteList);
			dresseur.setCartes_attaques(carteList_Attack);
		}
		repository.save(dresseur);
	}

	@Override
	public void echange_interne(String uuid, Echange_interne echange_interne) {
		Dresseur dresseur = findById(uuid);
		List<Carte> carteList_Attack = dresseur.getCartes_attaques();
		if (!carteList_Attack.isEmpty())
		{
			List<Carte> carteList = dresseur.getCartes();
			Carte carte_sec = carteService.findById(echange_interne.getUuid_carte1());
			Carte carte_att = carteService.findById(echange_interne.getUuid_carte2());
			if (carteList.equals(carte_sec) && carteList_Attack.equals(carte_att))
			{
				carteList.remove(carte_att);
				carteList_Attack.add(carte_sec);

				carteList_Attack.remove(carte_sec);
				carteList.add(carte_att);

				dresseur.setCartes(carteList);
				dresseur.setCartes_attaques(carteList_Attack);
			}
		}
		repository.save(dresseur);
	}

	@Override
	public void echange_joueur(String uuid_dresseur, String uuid_old_carte, String uuid_new_carte) {
		Dresseur dresseur = findById(uuid_dresseur);
		List<Carte> getCarts = dresseur.getCartes();
		Carte carte_old = carteService.findById(uuid_old_carte);
		Carte carte_new = carteService.findById(uuid_new_carte);
		if (dresseur.getCartes().contains(carte_new)) {
			throw new IllegalArgumentException("La carte est déjà possédée par le dresseur : " + carte_new.getUuid());
		}

		// Retirer et ajouter la carte
		dresseur.getCartes().remove(carte_old);
		dresseur.getCartes().add(carte_new);
		dresseur.setCartes(getCarts);
		repository.save(dresseur);
	}


	@Override
	public void create(DresseurDTO dresseurDTO) {
		Dresseur dresseur = new Dresseur();
		dresseur.setNom(dresseurDTO.getNom());
		dresseur.setPrenom(dresseurDTO.getPrenom());
		dresseur.setDeletedAt(null);
		repository.save(dresseur);
	}

	@Override
	public void tirageCarte(String uuid) {
		Dresseur dresseur = findById(uuid);
		if (dresseur.getDateTirage() == null || !(dresseur.getDateTirage().toLocalDate().isEqual(LocalDateTime.now().toLocalDate()))) {
			dresseur.setDateTirage(LocalDateTime.now());
			List<Carte> carteList = carteService.genererCartes(5);
		for (Carte carte : carteList) {
			dresseur.addCarte(carte);
		}
		repository.save(dresseur);
		}
	}

	@Override
	public boolean update(String uuid, Dresseur dresseur) {
		Dresseur dresseurToUpdate = findById(uuid);

		dresseurToUpdate.setNom(dresseur.getNom());
		dresseurToUpdate.setPrenom(dresseur.getPrenom());
		dresseurToUpdate.setDateTirage(dresseur.getDateTirage());
		dresseurToUpdate.setDeletedAt(dresseur.getDeletedAt());
		dresseurToUpdate.setPokemonList(dresseur.getPokemonList());
		dresseurToUpdate.setCartes(dresseur.getCartes());
		repository.save(dresseurToUpdate);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		Dresseur dresseur = findById(uuid);
		dresseur.setDeletedAt(LocalDateTime.now());
		repository.save(dresseur);
		return true;
	}


}
