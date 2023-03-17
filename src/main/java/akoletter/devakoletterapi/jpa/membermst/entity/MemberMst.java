package akoletter.devakoletterapi.jpa.membermst.entity;

import akoletter.devakoletterapi.util.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@IdClass(MemberMstPk.class)
@Table(name = "member_mst")
public class MemberMst extends BaseEntity {

    @Id
    @Column(name = "unq_user_id")
    @Schema(description = "유저의 고유 아이디값", nullable = false)
    private String UnqUserId;
    @Column(name = "usr_nm")
    @Schema(description = "유저이름", nullable = false)
    private String UsrNm;
    @Id
    @Column(name = "usr_id")
    @Schema(description = "유저 아이디")
    private String UsrId;
    @Column(name = "usr_email")
    @Schema(description = "유저이메일")
    private String UsrEmail;
    @Column(name = "usr_pwd")
    @Schema(description = "유저 패스워드", nullable = false)
    private String UsrPwd;
    @Column(name = "usr_tel_no")
    @Schema(description = "유저 전화번호")
    private String UsrTelNo;

}
