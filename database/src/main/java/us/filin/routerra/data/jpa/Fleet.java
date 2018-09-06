package us.filin.routerra.data.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.ToString;
import org.hibernate.annotations.NaturalId;


@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(
    name = "fleet",
    indexes = {
            @Index(name = "fleetname_index", columnList = "fleetname", unique = true)
    }
)
public class Fleet  {
    public final static String clazz = Fleet.class.getCanonicalName();

    @Id
    @GeneratedValue
    Long id;

    @NaturalId
    private String fleetname;
}
