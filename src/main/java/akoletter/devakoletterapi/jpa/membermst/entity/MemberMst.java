package akoletter.devakoletterapi.jpa.membermst.entity;

import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.util.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@IdClass(MemberMstPk.class)
@Table(name = "member_mst")
public class MemberMst extends BaseEntity {

    @Id
    @Column(name = "unq_usr_id")
    @Schema(description = "유저의 고유 아이디값")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unqUsrId;
    @Column(name = "usr_nm")
    @Schema(description = "유저이름", nullable = false)
    private String usrNm;
    @Column(name = "usr_id", unique = true)
    @Schema(description = "유저 아이디")
    private String usrId;
    @Column(name = "usr_email", unique = true)
    @Schema(description = "유저이메일")
    private String usrEmail;
    @Column(name = "usr_pwd")
    @Schema(description = "유저 패스워드", nullable = false)
    private String usrPwd;
    @Column(name = "usr_tel_no")
    @Schema(description = "유저 전화번호")
    private String usrTelNo;

    @Column(name = "refresh_token")
    @Schema(description = "리프레시 토큰")
    private String refreshToken;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setMember(this));
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
