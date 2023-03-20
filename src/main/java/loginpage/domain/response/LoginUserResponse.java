package loginpage.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUserResponse {
    @Schema(
            description = "도서 대여 가능 수량"
    )
    private long bkAvailableCnt;
    //api 명세서 ..정하면서 작성예정.
}
