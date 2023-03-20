package loginpage.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUserRequest {

    @Schema(
            description = "도서의 유니크한 키 값. 실제 도서에 부여되는 값이다.",
            hidden = true
    )
    private String bkIsbn; //api 명세서 ..정하면서 작성예정.
}
