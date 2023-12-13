public class Memoire {
    private int id;
    private String titre ;
    private String auteur;
    private String cote;
    private String resume;
    private String annee;

    public String getDirct() {
        return dirct;
    }

    public void setDirct(String dirct) {
        this.dirct = dirct;
    }

    private String dirct;
    private  int Idens;

    public Memoire(){

    }

    public Memoire(int id, String titre, String auteur, String cote, String resume, String annee,String dirct, int idens) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.cote = cote;
        this.resume = resume;
        this.annee = annee;
        this.dirct = dirct;
        Idens = idens;

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

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getIdens() {
        return Idens;
    }

    public void setIdens(int idens) {
        Idens = idens;
    }
}
