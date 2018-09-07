package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "location")
@ToString
public class Location extends Identified {

    @Column(nullable = false)
    private double lattitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = true)
    private Double course;
}
