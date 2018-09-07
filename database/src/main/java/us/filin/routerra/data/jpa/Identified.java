package us.filin.routerra.data.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class Identified extends Timestamped {
    public final static Class clazz = org.hibernate.id.UUIDGenerator.class;

    @Id
    @GeneratedValue(generator = "gen1")
    @GenericGenerator(name = "gen1", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, length = 36)
    private String id;
}
