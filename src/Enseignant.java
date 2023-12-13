public class Enseignant {
    private int id;
    private String nom ;
    private String prenom;
    private String speciality;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Enseignant(){
    }

    public Enseignant(int id, String nom,String prenom, String speciality) {
        this.id = id;
        this.nom = nom;
        this.speciality = speciality;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
