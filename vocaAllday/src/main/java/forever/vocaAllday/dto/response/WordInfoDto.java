package forever.vocaAllday.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class WordInfoDto {  //프론트 word 시험 정보 전달 DTO
    List<String> words;
    List<String> meanings;
    String type;
    String vocaTitle;
}
