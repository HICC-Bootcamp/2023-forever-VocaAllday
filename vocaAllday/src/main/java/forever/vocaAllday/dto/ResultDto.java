package forever.vocaAllday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResultDto {
    private List<String> AllWordList;
    private List<String> AllMeaningList;
    private List<String> WrongWordList;
    private List<String> WrongMeaningList;

}