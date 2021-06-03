package fr.diginamic.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "EMPRUNT" )
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID")
    private int id;

    @Column( name = "DATE_DEBUT" )
    private Date dateDebut;

    @Column( name = "DELAI" )
    private int delai;

    @Column( name = "DATE_FIN" )
    private Date dateFin;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ID_CLIENT" )
    private Client client;

    @ManyToMany( mappedBy = "emprunts" )
    private Set<Livre> livres;


    public Emprunt() {
        this.livres = new HashSet();
    }

    public Emprunt(Date dateDebut, int delai, Date dateFin) {
        this.dateDebut = dateDebut;
        this.delai = delai;
        this.dateFin = dateFin;
        this.livres = new HashSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public void addLivre(Livre livre){
        this.livres.add(livre);
    }

    public void removeLivre(Livre livre){
        this.livres.remove(livre);
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", delai=" + delai +
                ", dateFin=" + dateFin +
                ", client=" + client.getId() +
                '}';
    }
}
