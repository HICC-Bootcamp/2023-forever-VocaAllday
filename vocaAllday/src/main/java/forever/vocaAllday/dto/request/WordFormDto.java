package forever.vocaAllday.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WordFormDto {
    private List<String> userValues;
    private List<String> wordList;
    private List<String> meaningList;
    private String examType;
    private String vocaTitle;
}
