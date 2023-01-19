package forever.vocaAllday.entity;

import forever.vocaAllday.constant.AnswerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Table(name= "voca_note" )
@Getter
@Setter
@ToString
public class VocaNote {

    @Id
    @Column(name="VocaNote_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length=30)
    public String word;

    @Column(nullable = false,length=30)
    public String meaning;

    @Enumerated(EnumType.STRING)
    public AnswerStatus AnswerStatus; // 정답 여부 상태

    // 이거 member 생성할 때 만들어주는 것 고려
    public static VocaNote createVocaNote(Member member){
        VocaNote vocaNote = new VocaNote();
        vocaNote.setMember(member);
        return vocaNote;
    }

    @OneToOne
    @JoinColumn(name="member_id")


}
