package dwbi.proiect_dwbi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "DISTRICTS")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISTRICT")
    private int districtId;

    @Column(name = "NAME_DISTRICT")
    private String districtName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_REGION")
    private Region region;

    public District() {
    }

    public District(int districtId, String districtName, Region region) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.region = region;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", region=" + region +
                '}';
    }
}
