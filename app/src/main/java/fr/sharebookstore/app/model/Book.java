package fr.sharebookstore.app.model;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class Book {
    private String id;
    private String titre;
    private String image;
    private String prix;
    private String type;
    private String duree;

    public Book(String id, String titre, String image, String prix, String type, String duree) {
        this.id = id;
        this.titre = titre;
        this.image = image;
        this.prix = prix;
        this.type = type;
        this.duree = duree;
    }

    public String getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public String getPrix() {
        return prix;
    }

    public String getType() {
        return type;
    }

    public String getDuree() {
        return duree;
    }
}
