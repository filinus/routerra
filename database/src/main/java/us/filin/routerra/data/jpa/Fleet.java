package us.filin.routerra.data.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Data
@Table(
    name = "fleet",
    indexes = {
            @Index(name = "fleetname", columnList = "fleetname", unique = true)
    }
)
public class Fleet extends Identified {

    @NaturalId
    private String fleetname;
}
