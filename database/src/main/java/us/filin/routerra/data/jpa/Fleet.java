package us.filin.routerra.data.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(
    name = "fleet",
    indexes = {
            @Index(name = "fleetname_index", columnList = "fleetname", unique = true)
    }
)
public class Fleet extends Identified {
    public final static String clazz = Fleet.class.getCanonicalName();

    @NaturalId
    private String fleetname;
}
