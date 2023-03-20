package akoletter.devakoletterapi.jpa.membermst.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMstPk implements Serializable {

    private String unqUserId;
}
