package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "REGIONS")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGION")
    private int regionId;

    @Column(name = "NAME_REGION")
    private String regionName;


    public Region() {
    }

    public Region(int regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String name_region) {
        this.regionName = name_region;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
