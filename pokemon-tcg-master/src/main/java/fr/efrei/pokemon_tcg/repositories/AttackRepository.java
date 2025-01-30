package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.models.Dresseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttackRepository extends JpaRepository<Attack, String> {
    List<Attack> findAll();

}
