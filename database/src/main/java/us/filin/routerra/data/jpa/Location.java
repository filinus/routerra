package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "locations")
public class Location extends Identified {

    @Column(nullable = false)
    private double lattitude;

    @Column(nullable = false)
    private double logitude;

    @Column(nullable = true)
    private Double cource;
}
