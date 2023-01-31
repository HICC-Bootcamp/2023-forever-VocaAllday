package forever.vocaAllday.service;

import forever.vocaAllday.dto.InputInfoDto;
import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
import forever.vocaAllday.entity.WrongVoca;
import forever.vocaAllday.repository.InputVocaRepository;
import forever.vocaAllday.repository.MemberRepository;
import forever.vocaAllday.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;
    private final InputVocaRepository inputVocaRepository;

    public Long saveReport(InputInfoDto inputInfoDto, String email) {

        Member member = memberRepository.findByEmail(email);

        String vocaTitle = inputInfoDto.getVocaTitle();
        validateDuplicateTitle(member.getId(), vocaTitle);

        String word = String.join(",", inputInfoDto.getWord());
        String meaning = String.join(",", inputInfoDto.getMeaning());

        InputVoca inputVoca = inputInfoDto.createInputVoca(word, meaning);
        WrongVoca wrongVoca = new WrongVoca(null,null);
        Report report = new Report(member, vocaTitle, inputVoca, wrongVoca);

        inputVocaRepository.save(inputVoca);
        reportRepository.save(report);

        return report.getReportId();

    }

    private void validateDuplicateTitle(Long id, String title){
        Report report = reportRepository.findByReport(id,title);
        if(report != null){
            throw new IllegalStateException("단어장 이름이 중복되었습니다.");
        }
    }

}



