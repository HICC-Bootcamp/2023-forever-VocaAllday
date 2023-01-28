package forever.vocaAllday.service;

import forever.vocaAllday.dto.InputInfoDto;
import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
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

        String word = String.join(",", inputInfoDto.getWord());
        String meaning = String.join(",", inputInfoDto.getMeaning());

        String vocaTitle = inputInfoDto.getVocaTitle();

        InputVoca inputVoca = inputInfoDto.createInputVoca(word, meaning);
        Report report = new Report(member, vocaTitle, inputVoca);

        inputVocaRepository.save(inputVoca);
        reportRepository.save(report);

        return report.getReportId();

    }
}


