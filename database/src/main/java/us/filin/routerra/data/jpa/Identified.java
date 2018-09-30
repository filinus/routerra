package us.filin.routerra.data.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Identified implements Serializable { //extends Timestamped
    private static final long serialVersionUID = 1L;
    //private final static Class clazz = org.hibernate.id.UUIDGenerator.class;

    @Id
    @GeneratedValue(generator = "gen1")
    @GenericGenerator(name = "gen1", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, length = 36)
    private String id;
}
