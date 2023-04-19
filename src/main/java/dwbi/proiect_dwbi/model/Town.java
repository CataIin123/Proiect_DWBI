package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "TOWNS")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TOWN")
    private int townId;

    @Column(name = "NAME_TOWN")
    private String townName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_DISTRICT")
    private District district;

    public Town() {
    }

    public Town(int townId, String townName , District district) {
        this.townId = townId;
        this.townName = townName;
        this.district = district;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int id_town) {
        this.townId = id_town;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String name_town) {
        this.townName = name_town;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Town{" +
                "id_town=" + townId +
                ", name_town='" + townName + '\'' +
                '}';
    }
}
