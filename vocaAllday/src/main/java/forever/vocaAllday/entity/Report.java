package forever.vocaAllday.entity;

import forever.vocaAllday.dto.InputVocaDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "report")
@Getter
@Setter
@ToString
public class Report {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId; // 레포트 ID

    @Column(nullable = false, name="voca_title")
    private String vocaTitle; //단어장 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_id")
    private InputVoca inputVoca;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrong_id")
    private WrongVoca wrongVoca;

    public static Report createReport(Member member, String vocaTitle, InputVocaDto inputVocaDto){
        Report report = new Report();
        report.setMember(member);
        report.setVocaTitle(vocaTitle);
        report.setInputVoca(inputVocaDto.createInputVoca());
        return report;
    }

}