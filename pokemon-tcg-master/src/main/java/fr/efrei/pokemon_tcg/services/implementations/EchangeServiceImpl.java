package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.CreateEchange;
import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import fr.efrei.pokemon_tcg.services.ICarteService;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IEchangeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchangeServiceImpl implements IEchangeService {

    private final IDresseurService dresseurService;
    private final ICarteService carteService;
    private final EchangeRepository repository;

    public EchangeServiceImpl(EchangeRepository repository, IDresseurService dresseurService, ICarteService carteService) {
        this.repository = repository;
        this.dresseurService = dresseurService;
        this.carteService = carteService;
    }

    @Override
    public List<Echange> findAll() {
        return repository.findAll();
    }

    @Override
    public Echange findById(String uuid) {
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public Echange create(CreateEchange createEchange) {
        Echange echange = new Echange();

        Dresseur dresseur1 = dresseurService.findById(createEchange.getUuid_dresseur1());
        Dresseur dresseur2 = dresseurService.findById(createEchange.getUuid_dresseur2());

        Carte carte1 = carteService.findById(createEchange.getUuid_carte1());
        Carte carte2 = carteService.findById(createEchange.getUuid_carte2());

       /* dresseur1.deleteCarte(carte1);
        dresseur2.deleteCarte(carte2);
        dresseur1.addCarte(carte2);
        dresseur2.addCarte(carte1);

        dresseurService.update(dresseur1.getUuid(), dresseur1);
        dresseurService.update(dresseur2.getUuid(), dresseur2);*/


        echange.setDresseur1(dresseur1);
        echange.setDresseur2(dresseur2);
        echange.setCarte_dresseur1(carte1);
        echange.setCarte_dresseur2(carte2);

        echange.setStatut("En cours");
        return repository.save(echange);
    }

    @Override
    public Echange echanger(String uuid_echange) {
        Echange echange = findById(uuid_echange);
        Dresseur dresseur1 = echange.getDresseur1();
        Dresseur dresseur2 = echange.getDresseur2();
        String uuid_carte1 = echange.getCarte_dresseur1().getUuid();
        String uuid_carte2 = echange.getCarte_dresseur2().getUuid();

        dresseurService.echange_joueur(dresseur1.getUuid(), uuid_carte1, uuid_carte2);
        dresseurService.echange_joueur(dresseur2.getUuid(), uuid_carte2, uuid_carte1);

        echange.setDresseur1(dresseur1);
        echange.setDresseur2(dresseur2);
        echange.setStatut("Terminer");

        return repository.save(echange);
    }

    @Override
    public boolean delete(String uuid) {
        return false;
    }
}
