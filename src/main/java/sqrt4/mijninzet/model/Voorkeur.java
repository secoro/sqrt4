package sqrt4.mijninzet.model;

import javax.persistence.*;

@Entity
public class Voorkeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int voorkeurId;
    @OneToOne
    private User user;
    @OneToOne
    private Vak vak;
    private String voorkeur;

    public Voorkeur(int voorkeurId, User user, Vak vak, String voorkeur) {
        this.voorkeurId = voorkeurId;
        this.user = user;
        this.vak = vak;
        this.voorkeur = voorkeur;
    }

    public Voorkeur() {
    }

    public int getVoorkeurId() {
        return voorkeurId;
    }

    public void setVoorkeurId(int voorkeurId) {
        this.voorkeurId = voorkeurId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public String getVoorkeur() {
        return voorkeur;
    }

    public void setVoorkeur(String voorkeur) {
        this.voorkeur = voorkeur;
    }

    @Override
    public String toString() {
        return "Voorkeur{" +
                "voorkeurId=" + voorkeurId +
                ", user=" + user +
                ", vak=" + vak +
                ", voorkeur='" + voorkeur + '\'' +
                '}';
    }
}
