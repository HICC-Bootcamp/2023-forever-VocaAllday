package forever.vocaAllday.service;

import forever.vocaAllday.dto.request.InputInfoDto;
import forever.vocaAllday.dto.response.UserInfoDto;
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

import java.util.List;


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

        List<String> words = inputInfoDto.getWord();

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i) != null & words.get(i).equals(words.get(j))) {
                    throw new IllegalStateException("wordError");
                }
            }
        }

        String word = String.join(",", inputInfoDto.getWord());
        String meaning = String.join(",", inputInfoDto.getMeaning());

        InputVoca inputVoca = inputInfoDto.createInputVoca(word, meaning);
        WrongVoca wrongVoca = new WrongVoca(null, null);
        Report report = new Report(member, vocaTitle, inputVoca, wrongVoca);

        inputVocaRepository.save(inputVoca);
        reportRepository.save(report);

        return report.getReportId();

    }

    private void validateDuplicateTitle(Long id, String title) {
        Report report = reportRepository.findByReport(id, title);
        if (report != null) {
            throw new IllegalStateException("vocaTitleError");
        }
    }


}
