package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.CreateEchange;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.Echange_interne;
import fr.efrei.pokemon_tcg.models.Dresseur;

import java.util.List;

public interface IDresseurService {

	List<Dresseur> findAll();
	Dresseur findById(String uuid);
	void create(DresseurDTO dresseurDTO);

	boolean update(String uuid, Dresseur dresseur);
	boolean delete(String uuid);

	void tirageCarte(String uuid);
	void init_deck_attack(String uuid);
	void echange_interne(String uuid, Echange_interne echange_interne);
	void echange_joueur(String uuid_dresseur, String uuid_old_carte, String uuid_new_carte);
	void capturerPokemon(String uuid, CapturePokemon capturePokemon);
}
