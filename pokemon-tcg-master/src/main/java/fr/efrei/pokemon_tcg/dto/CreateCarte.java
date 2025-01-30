package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.models.Pokemon;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CreateCarte {

    @ManyToOne
    private Pokemon pokemon;

    @OneToMany
    private List<Attack> list_attacks;

    private int etoile;


    public Pokemon getPokemon() {
        return pokemon;
    }

    public List<Attack> getList_attacks() {
        return list_attacks;
    }

    public int getEtoile() {
        return etoile;
    }
}
