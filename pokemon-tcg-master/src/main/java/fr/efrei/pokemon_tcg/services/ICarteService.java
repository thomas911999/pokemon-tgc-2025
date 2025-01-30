package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Carte;

import java.util.List;

public interface ICarteService {
    List<Carte> findAll();

    Carte findById(String uuid);

    Carte create();

    List<Carte> genererCartes(int nombre);

    boolean update(String uuid, Carte carte);

    boolean delete(String uuid);

}
