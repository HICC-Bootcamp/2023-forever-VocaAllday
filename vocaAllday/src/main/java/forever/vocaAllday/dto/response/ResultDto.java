package forever.vocaAllday.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private List<String> AllWordList;
    private List<String> AllMeaningList;
    private List<String> WrongWordList;
    private List<String> WrongMeaningList;
}
