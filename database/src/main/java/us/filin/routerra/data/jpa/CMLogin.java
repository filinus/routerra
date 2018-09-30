package us.filin.routerra.data.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Data
@Table(
        name = "cmlogin",
        indexes = {
                @Index(name = "cmlogin", columnList = "login", unique = true)
        }
)
public class CMLogin extends Identified {

    @NaturalId
    private String login;

    @ManyToOne(targetEntity = Fleet.class)
    @JoinColumn(name = "fleet_id", referencedColumnName = "id")
    private Fleet fleet;
}