package forever.vocaAllday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "input_voca")
@Getter
@ToString
@NoArgsConstructor
public class InputVoca {

    @Id
    @Column(name = "input_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inputId; // InputVoca ID

    @Lob
    @Column(nullable = false)
    private String word; // 입력한 영어 단어

    @Lob
    @Column(nullable = false)
    private String meaning; // 입력한 한글 뜻

    public InputVoca(String word,String meaning){
        this.word=word;
        this.meaning=meaning;
    }

}