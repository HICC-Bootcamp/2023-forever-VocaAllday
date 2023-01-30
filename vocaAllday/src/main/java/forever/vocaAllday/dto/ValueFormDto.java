package forever.vocaAllday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ValueFormDto {
    private List<String> userValues;
    private List<String> wordList;
    private List<String> meaningList;
    private String examType;
}
