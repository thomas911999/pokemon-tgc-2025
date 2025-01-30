package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.services.ICarteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartes")
public class CarteController {

    private final ICarteService carteService;

    public CarteController(ICarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping("/generer")
    public ResponseEntity<Carte> genererCarte() {
        Carte carteCree = carteService.create();
        return new ResponseEntity<>(carteCree, HttpStatus.CREATED);
    }

    @GetMapping("/tirage")
    public ResponseEntity<List<Carte>> tirerCartes(@RequestParam(defaultValue = "5") int nombre) {
        try {
            List<Carte> cartesCrees = carteService.genererCartes(nombre);
            return new ResponseEntity<>(cartesCrees, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Carte>> getAllCartes() {
        List<Carte> cartes = carteService.findAll();
        return new ResponseEntity<>(cartes, HttpStatus.OK);
    }
}