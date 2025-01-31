package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EchangeRepository extends JpaRepository<Echange, String> {
    List<Echange> findAll();
}
