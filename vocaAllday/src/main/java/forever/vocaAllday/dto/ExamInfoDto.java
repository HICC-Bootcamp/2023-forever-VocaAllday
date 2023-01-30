package forever.vocaAllday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ExamInfoDto {  //프론트 시험 정보 전달 DTO
    List<String> words;
    List<String> meanings;
    String type;

}
