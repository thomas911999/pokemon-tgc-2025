package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.AttackDTO;
import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.repositories.AttackRepository;
import fr.efrei.pokemon_tcg.services.IAttackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttackServiceImpl implements IAttackService {

    private final AttackRepository attackRepository;

    public AttackServiceImpl(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    @Override
    public List<Attack> findAll() {
        return attackRepository.findAll();
    }

    @Override
    public Attack findById(String uuid) {
        return attackRepository.findById(uuid).orElse(null);
    }

    @Override
    public void create(AttackDTO attackDTO) {
        Attack attack = new Attack();
        attack.setNom(attackDTO.getNom());
        attack.setDamage(attackDTO.getDamage());
        attack.setDescription(attackDTO.getDescription());
        attackRepository.save(attack);
    }

    @Override
    public boolean update(String uuid, AttackDTO attackDTO) {
        Attack attack = findById(uuid);
        if (attack != null) {
            attack.setNom(attackDTO.getNom());
            attack.setDamage(attackDTO.getDamage());
            attack.setDescription(attackDTO.getDescription());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String uuid) {
        Attack attack = findById(uuid);
        if (attack != null) {
            attackRepository.delete(attack);
            return true;
        }
        return false;
    }
}
