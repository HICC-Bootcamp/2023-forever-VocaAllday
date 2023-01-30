package forever.vocaAllday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TestInfoDto {  //프론트 시험 정보 전달 DTO
    String[] mList;
    String[] wList;
    String type;

}
