package fr.efrei.pokemon_tcg.services;

import java.util.List;

import fr.efrei.pokemon_tcg.dto.CreateEchange;
import fr.efrei.pokemon_tcg.models.Echange;

public interface IEchangeService {
    
	List<Echange> findAll();
	Echange findById(String uuid);
	Echange create(CreateEchange Echange);
    Echange echanger(String uuid_echange);
	boolean delete(String uuid);

}
