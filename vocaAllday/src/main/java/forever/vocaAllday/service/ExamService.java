package forever.vocaAllday.service;


import forever.vocaAllday.dto.ExamInfoDto;
import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
import forever.vocaAllday.repository.MemberRepository;
import forever.vocaAllday.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public InputVoca getInputVoca(String email, String vocaTitle) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        InputVoca inputVoca = report.getInputVoca();
        return inputVoca;
    }

    public ExamInfoDto makeExam(String email, String vocaTitle, String type) {

        InputVoca inputVoca = getInputVoca(email, vocaTitle);
        List<String> tempWord = Arrays.asList(inputVoca.getWord().split(","));
        List<String> tempMeaning = Arrays.asList(inputVoca.getMeaning().split(","));

        HashMap<String, String> map = new HashMap<String, String>();

        for (int j = 0; j < tempMeaning.size(); j++) {
            map.put(tempWord.get(j), tempMeaning.get(j));
        }

        List<String> word = new ArrayList(map.keySet());
        List<String> meaning = new ArrayList<String>();
        Collections.shuffle(word);

        Iterator<String> it = word.iterator();

        while (it.hasNext()) {
            String s = it.next();
            meaning.add(map.get(s));
        }

        ExamInfoDto t = new ExamInfoDto(word,meaning,type);

        return t;

    }


}


