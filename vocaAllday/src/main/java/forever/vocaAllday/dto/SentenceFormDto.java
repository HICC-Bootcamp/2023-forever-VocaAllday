package forever.vocaAllday.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SentenceFormDto {
    private List<String> userValues;//사용자 입력 값
    private List<String> wordList;//<보기>에 들어간 단어
    private List<String> meaningList;//뜻
    private List<String> answerList;//정답
    private String vocaTitle;//단어장제목
}
