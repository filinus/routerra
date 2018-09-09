package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(
    name = "location",
    indexes = {
            @Index(name = "bydevice", columnList = "device_id", unique = false)
    }
)
public class Location extends Identified {
    @Column(name = "device_id", nullable = false)
    private String device;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = true)
    private Double course;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tstamp", nullable = false)
    private Date tstamp;
}
