package us.filin.routerra.data.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class Identified extends Timestamped {
    private static final Class clazz = UUIDGenerator.class;


    @Id
    @GeneratedValue(generator = "uuidgen", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuidgen", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false, length = 36)
    private String id;

    //@Id
    //@GeneratedValue
    //private Long id;
}
