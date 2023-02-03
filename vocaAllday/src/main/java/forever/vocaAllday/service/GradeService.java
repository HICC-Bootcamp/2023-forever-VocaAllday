package forever.vocaAllday.service;

import forever.vocaAllday.dto.response.ResultDto;
import forever.vocaAllday.dto.request.SentenceFormDto;
import forever.vocaAllday.dto.request.WordFormDto;
import forever.vocaAllday.dto.response.UserInfoDto;
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
import java.util.Objects;

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
        System.out.println(word);
        wrongVocaRepository.updateMeaning(wrongId, meaning);
        wrongVocaRepository.updateWord(wrongId, word);
    }

    public void grade(String email, WordFormDto wordFormDto) {

        String type = valueFormDto.getExamType();
        List<String> userValues = valueFormDto.getUserValues();

        List<String> meanings = valueFormDto.getMeaningList();
        List<String> words = valueFormDto.getWordList();

        List<String> wrongWords = new ArrayList<>();
        List<String> wrongMeanings = new ArrayList<>();

        int index = 0;

        if (type.equals("MEANING")) {
            for (String word : words) {
                if (!(word.equals(userValues.get(index)))) {
                    wrongWords.add(word);
                    wrongMeanings.add(meanings.get(index));
                }
                index++;
            }

        } else if (type.equals("WORD")) {
            for (String meaning : meanings) {
                if (!(meaning.equals(userValues.get(index)))) {
                    wrongWords.add(meaning);
                    wrongMeanings.add(words.get(index));
                }
                index++;
            }
        }

        String wrongMeaning = String.join(",", wrongMeanings);
        String wrongWord = String.join(",", wrongWords);
        String title = wordFormDto.getVocaTitle();
        Report report = findReport(email, title);
        updateWrongVoca(report, wrongWord, wrongMeaning);

    }
    public void gradeTest(String email, SentenceFormDto sentenceFormDto){
        List<String> userValues = sentenceFormDto.getUserValues();
        List<String> meanings = sentenceFormDto.getMeaningList();
        List<String> answers = sentenceFormDto.getAnswerList();
        List<String> words = sentenceFormDto.getWordList();
        //테스트용


        List<String> wrongWords = new ArrayList<>();
        List<String> wrongMeanings = new ArrayList<>();

        for(int i=0;i<8;i++){
            if(!Objects.equals(answers.get(i), userValues.get(i))){
                wrongWords.add(words.get(i));
                wrongMeanings.add(meanings.get(i));
            }
        }

        String wrongMeaning = String.join(",", wrongMeanings);
        String wrongWord = String.join(",", wrongWords);
        String title = sentenceFormDto.getVocaTitle();
        Report report = findReport(email, title);
        updateWrongVoca(report, wrongWord, wrongMeaning);
    }

    public ResultDto showGradingResult(String email, String title) {
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
