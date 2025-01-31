package fr.efrei.pokemon_tcg.dto;

public class CreateEchange {

    private String uuid_dresseur1;
    private String uuid_dresseur2;

    private String uuid_carte1;
    private String uuid_carte2;

    public String getUuid_dresseur1() {
        return uuid_dresseur1;
    }

    public void setUuid_dresseur1(String uuid_dresseur1) {
        this.uuid_dresseur1 = uuid_dresseur1;
    }

    public String getUuid_dresseur2() {
        return uuid_dresseur2;
    }

    public void setUuid_dresseur2(String uuid_dresseur2) {
        this.uuid_dresseur2 = uuid_dresseur2;
    }

    public String getUuid_carte1() {
        return uuid_carte1;
    }

    public void setUuid_carte1(String uuid_carte1) {
        this.uuid_carte1 = uuid_carte1;
    }

    public String getUuid_carte2() {
        return uuid_carte2;
    }

    public void setUuid_carte2(String uuid_carte2) {
        this.uuid_carte2 = uuid_carte2;
    }
}
