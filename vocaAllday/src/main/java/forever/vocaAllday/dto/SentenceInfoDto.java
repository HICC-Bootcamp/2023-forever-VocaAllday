package forever.vocaAllday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SentenceInfoDto {
    List<String > sentence;//예문
    List<String > answer;//답
    List<String > word;//<보기>에 들어갈 단어=사용자가 입력한 단어
    List<String > meaning;//뜻
    String vocaTitle;//단어장 제목
}
