package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.CreateEchange;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.services.IEchangeService;
import java.util.List;

import fr.efrei.pokemon_tcg.services.implementations.EchangeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/echanges")
public class EchangeController {

    private final IEchangeService echangeService;

    public EchangeController(IEchangeService echangeService) {
        this.echangeService = echangeService;
    }

    @GetMapping
    public ResponseEntity<List<Echange>> findAll() {
        List<Echange> echanges = echangeService.findAll();
        return new ResponseEntity<>(echanges, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> postMethodName(@RequestBody CreateEchange createEchange) {
        this.echangeService.create(createEchange);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PatchMapping("/{uuid_echange}/echanger")
    public ResponseEntity<String> echanger(@PathVariable String uuid_echange) {
        this.echangeService.echanger(uuid_echange);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable String uuid) {
        this.echangeService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
