package akoletter.devakoletterapi.jpa.membermst.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMstPk implements Serializable {

    private static final long serialVersionUID = -3707559740976324731L;
    @Column(name = "unq_usr_id")
    private Long unqUsrId;
}
