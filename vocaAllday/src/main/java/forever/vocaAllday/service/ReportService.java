package forever.vocaAllday.service;

import forever.vocaAllday.dto.InputVocaDto;
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

    public Long saveReport(InputVocaDto inputVocaDto,String vocaTitle, String email) {

        Member member = memberRepository.findByEmail(email);
        Report report = new Report(member,vocaTitle, inputVocaDto.createInputVoca());
        reportRepository.save(report);
        InputVoca inputVoca = report.getInputVoca();
        inputVocaRepository.save(inputVoca);

        return report.getReportId();

    }
}

