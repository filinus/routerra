package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Builder
@Table(
        name = "device",
        indexes = {
                @Index(name = "fleet", columnList = "fleet_id", unique = false),
                @Index(name = "cmlogin_devname", columnList = "cmlogin_id,devname", unique = true)
        }
)
public class Device extends Identified {
    @ManyToOne(targetEntity = Fleet.class)
    @JoinColumn(name = "fleet_id", referencedColumnName = "id", nullable = false)
    private Fleet fleet;

    @ManyToOne(targetEntity = CMLogin.class)
    @JoinColumn(name = "cmlogin_id", referencedColumnName = "id", nullable = false)
    private CMLogin login;

    @Column(name = "devname", nullable = false)
    private String devname;

    @OneToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_location", referencedColumnName = "id", nullable = true)
    private Location lastLocation;
}
