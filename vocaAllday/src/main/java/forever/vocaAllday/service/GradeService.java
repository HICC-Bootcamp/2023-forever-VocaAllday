package forever.vocaAllday.service;

import forever.vocaAllday.dto.ValueFormDto;
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
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GradeService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final WrongVocaRepository wrongVocaRepository;

    public void updateWrongVoca(String email, String vocaTitle,
                                String word, String meaning) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        WrongVoca wrongVoca = report.getWrongVoca();
        Long wrongId = wrongVoca.getWrongId();
        System.out.println(word);
        wrongVocaRepository.updateMeaning(wrongId, meaning);
        wrongVocaRepository.updateWord(wrongId, word);
    }

    public void grade(String email, ValueFormDto valueFormDto) {

        String type = valueFormDto.getExamType();
        List<String> userValues = valueFormDto.getUserValues(); // 유저 입력 값

        List<String> meanings = valueFormDto.getMeaningList(); // 정답 의미
        List<String> words = valueFormDto.getWordList(); // 정답 단어

        List<String> wrongWords = new ArrayList<>(); // 틀린 단어 저장 배열
        List<String> wrongMeanings = new ArrayList<>(); // 틀린 의미 저장 배열

        int index = 0;
       
        if (type.equals("MEANING")) {
            for (String meaning : meanings) {
                if (!(meaning.equals(userValues.get(index)))) {
                    wrongWords.add(meaning);
                    wrongMeanings.add(words.get(index));
                }
                index++;
            }
        } else if (type.equals("WORD")) {
            for (String word : words) {
                if (!(word.equals(userValues.get(index)))) {
                    wrongWords.add(word);
                    wrongMeanings.add(meanings.get(index));
                }
                index++;
            }
        }

        String wrongMeaning = String.join(",", wrongMeanings);
        String wrongWord = String.join(",", wrongWords);
        String title=valueFormDto.getVocaTitle();

        updateWrongVoca(email, title, wrongWord, wrongMeaning);

    }
}

