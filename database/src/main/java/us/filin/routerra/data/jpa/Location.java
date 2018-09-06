package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "locations")
public class Location extends Identified {
    private static final long serialVersionUID = 42L;

    @Column(nullable = false)
    private double lattitude;

    @Column(nullable = false)
    private double logitude;

    @Column(nullable = false)
    private Double cource;
}
