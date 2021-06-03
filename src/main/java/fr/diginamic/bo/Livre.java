package fr.diginamic.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "LIVRE")
public class Livre implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(length = 50, nullable = false)
    private String auteur;

    public Livre() {
    }

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}
