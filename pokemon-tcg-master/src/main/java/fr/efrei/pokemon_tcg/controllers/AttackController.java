package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.AttackDTO;
import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.services.IAttackService;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attacks")
public class AttackController {

    private final IAttackService attService;


    public AttackController(IAttackService attService) {
        this.attService = attService;
    }

    @GetMapping
    public ResponseEntity<List<Attack>> findAll() {
        return new ResponseEntity<>(attService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttackDTO attackDTO) {
        attService.create(attackDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        attService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
