package forever.vocaAllday.service;

import forever.vocaAllday.dto.ResultDto;
import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
import forever.vocaAllday.entity.WrongVoca;
import forever.vocaAllday.repository.MemberRepository;
import forever.vocaAllday.repository.ReportRepository;
import forever.vocaAllday.repository.WrongVocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final WrongVocaRepository wrongVocaRepository;

    public Report findReport(String email, String vocaTitle) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        return report;
    }
    public List<String> getVocaTitleList(String email){
        return reportRepository.findByVocaTitles(email);

    }

    public ResultDto showVocaInfo(String email, String title) {
        Report report = findReport(email, title);
        WrongVoca wrongVoca = report.getWrongVoca();
        InputVoca inputVoca = report.getInputVoca();

        List<String> AllWordList = Arrays.asList(inputVoca.getWord().split(","));
        List<String> AllMeaningList = Arrays.asList(inputVoca.getMeaning().split(","));

        List<String> WrongWordList = Arrays.asList(wrongVoca.getWord().split(","));
        List<String> WrongMeaningList = Arrays.asList(wrongVoca.getMeaning().split(","));

        ResultDto resultDto = new ResultDto(AllWordList, AllMeaningList, WrongWordList, WrongMeaningList);

        return resultDto;
    }

}