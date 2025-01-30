package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.AttackRepository;
import fr.efrei.pokemon_tcg.repositories.CarteRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.ICarteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarteServiceImpl implements ICarteService {

    private final CarteRepository carteRepository;
    private final PokemonRepository pokemonRepository;
    private final AttackRepository attackRepository;

    public CarteServiceImpl(CarteRepository carteRepository, PokemonRepository pokemonRepository, AttackRepository attackRepository) {
        this.carteRepository = carteRepository;
        this.pokemonRepository = pokemonRepository;
        this.attackRepository = attackRepository;
    }

    @Override
    public List<Carte> findAll() {
    return carteRepository.findAll();
    }

    @Override
    public Carte findById(String uuid) {
        return carteRepository.findById(uuid).orElse(null);
    }

    @Override
    public Carte create() {
       // Carte newCarte = new Carte();
       Carte newCarte = new Carte();
        Random random = new Random();

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        int indexAleatoire = random.nextInt(pokemonList.size());
        Pokemon pokemon = pokemonList.get(indexAleatoire);
        newCarte.setPokemon(pokemon);

        List<Attack> attackList = attackRepository.findAll();
        if (attackList.size() < 2) {
            throw new RuntimeException("Pas assez d'attaques disponibles !");
        }
    
        Attack attack_1 = attackList.get(random.nextInt(attackList.size()));
        Attack attack_2;
        do {
            attack_2 = attackList.get(random.nextInt(attackList.size()));
        } while (attack_1.equals(attack_2));
    
        // Ajouter les deux attaques distinctes à la carte
        newCarte.addAttack(attack_1);
        newCarte.addAttack(attack_2);

        int r = random.nextInt(100);
        int star;
        if (r < 5) {
            star = 5;
        } else if (r < 10) {
            star = 4;
        } else {
            star = 1 + random.nextInt(3);
        }
        newCarte.setEtoile(star);

        return carteRepository.save(newCarte);
    }


    @Override
    public List<Carte> genererCartes(int nombre) {
        List<Carte> cartes = new ArrayList<>();
        for (int i = 0; i < nombre; i++) {
            cartes.add(create());
        }
        return cartes;
    }

    @Override
    public boolean update(String uuid, Carte carte) {
        return false;
    }

    @Override
    public boolean delete(String uuid) {
        return false;
    }
}
