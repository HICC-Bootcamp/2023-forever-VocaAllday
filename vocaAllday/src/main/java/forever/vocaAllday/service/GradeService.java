package forever.vocaAllday.service;

import forever.vocaAllday.dto.ResultDto;
import forever.vocaAllday.dto.SentenceFormDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GradeService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final WrongVocaRepository wrongVocaRepository;
    public Report findReport(String email, String vocaTitle) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        return report;
    }
    public void updateWrongVoca(Report report,
                                String word, String meaning) {
        WrongVoca wrongVoca = report.getWrongVoca();
        Long wrongId = wrongVoca.getWrongId();

        wrongVocaRepository.updateMeaning(wrongId, meaning);
        wrongVocaRepository.updateWord(wrongId, word);
    }

    public void gradeTest(String email, SentenceFormDto sentenceFormDto){
        List<String> userValues = sentenceFormDto.getUserValues(); //사용자 입력 값
        List<String> meanings = sentenceFormDto.getMeaningList(); // 의미
        List<String> answers = sentenceFormDto.getAnswerList(); // 정답
        List<String> words = sentenceFormDto.getWordList();//<보기> 단어

        List<String> wrongWords = new ArrayList<>();
        List<String> wrongMeanings = new ArrayList<>();

        int index = 0;

        for(String answer: answers){
            if(!(answer.equals(userValues.get(index))));{
                wrongWords.add(words.get(index));
                wrongMeanings.add(meanings.get(index));
            }
            index++;
        }

        String wrongMeaning = String.join(",", wrongMeanings);
        String wrongWord = String.join(",", wrongWords);
        String title = sentenceFormDto.getVocaTitle();
        Report report = findReport(email, title);
        updateWrongVoca(report, wrongWord, wrongMeaning);
    }

    public ResultDto showGradingResultsent(String email, String title) {
        Report report = findReport(email, title);
        WrongVoca wrongVoca = report.getWrongVoca();//틀린 단어 모음집
        InputVoca inputVoca = report.getInputVoca();//입력된 단어 모음집

        List<String> AllWordList = Arrays.asList(inputVoca.getWord().split(","));//입력된 영단어
        List<String> AllMeaningList = Arrays.asList(inputVoca.getMeaning().split(","));//입력된 뜻

        List<String> WrongWordList = Arrays.asList(wrongVoca.getWord().split(","));//입력된 영단어
        List<String> WrongMeaningList = Arrays.asList(wrongVoca.getMeaning().split(","));//입력된 뜻

        ResultDto resultDto = new ResultDto(AllWordList, AllMeaningList, WrongWordList, WrongMeaningList);

        return resultDto;
    }
}