package forever.vocaAllday.dto;

import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.WrongVoca;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class WrongVocaDto {

    private String word;
    private String meaning;
    private static ModelMapper modelMapper = new ModelMapper();

    public WrongVoca createWrongVoca(String word, String meaning)
    {
        WrongVoca wrongVoca = new WrongVoca(word, meaning);
        return wrongVoca;
    }



    public static WrongVocaDto of(WrongVoca wrongVoca) {
        return modelMapper.map(wrongVoca, WrongVocaDto.class);
    }
}

