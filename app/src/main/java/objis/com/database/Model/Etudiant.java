package objis.com.database.Model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yeo-sglo on 25/12/16.
 */

public class Etudiant {
    private Long mId;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private String lieuNaiss;
    private String filiere;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public void setLieuNaiss(String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public Etudiant() {
    }

    public Etudiant(Long id, String nom, String prenom, Date dateNaiss, String lieuNaiss, String filiere) {
        mId = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.lieuNaiss = lieuNaiss;
        this.filiere = filiere;
    }

    public Etudiant(String nom, String prenom, Date dateNaiss, String lieuNaiss, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.lieuNaiss = lieuNaiss;
        this.filiere = filiere;
    }

}
