package fr.efrei.pokemon_tcg.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Echange {
    
    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    private String statut;

    private LocalDateTime dateEchange;

    @ManyToOne
    Dresseur dresseur1;

    @ManyToOne
    Dresseur dresseur2;

    @ManyToOne
    Carte carte_dresseur1;

    @ManyToOne
    Carte carte_dresseur2;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Carte getCarte_dresseur2() {
        return carte_dresseur2;
    }

    public void setCarte_dresseur2(Carte carte_dresseur2) {
        this.carte_dresseur2 = carte_dresseur2;
    }

    public Carte getCarte_dresseur1() {
        return carte_dresseur1;
    }

    public void setCarte_dresseur1(Carte carte_dresseur1) {
        this.carte_dresseur1 = carte_dresseur1;
    }

    public Dresseur getDresseur2() {
        return dresseur2;
    }

    public void setDresseur2(Dresseur dresseur2) {
        this.dresseur2 = dresseur2;
    }

    public Dresseur getDresseur1() {
        return dresseur1;
    }

    public void setDresseur1(Dresseur dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public LocalDateTime getDateEchange() {
        return dateEchange;
    }

    public void setDateEchange(LocalDateTime dateEchange) {
        this.dateEchange = dateEchange;
    }
}
