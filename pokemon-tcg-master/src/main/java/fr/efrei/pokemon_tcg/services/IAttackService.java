package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.AttackDTO;
import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.models.Dresseur;

import java.util.List;

public interface IAttackService {
    List<Attack> findAll();
    Attack findById(String uuid);
    void create(AttackDTO attackDTO);

    boolean update(String uuid, AttackDTO attackDTO);
    boolean delete(String uuid);

    //void capturerPokemon(String uuid, CapturePokemon capturePokemon);
}
