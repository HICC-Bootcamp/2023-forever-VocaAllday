package forever.vocaAllday.service;


import forever.vocaAllday.dto.TestInfoDto;
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
public class TestService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public InputVoca getInputVoca(String email, String vocaTitle) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        InputVoca inputVoca = report.getInputVoca();
        return inputVoca;
    }

    public TestInfoDto makeTest(String email, String vocaTitle, String type) {

        InputVoca i = getInputVoca(email, vocaTitle);
        List<String> tempWord = Arrays.asList(i.getWord().split(","));
        List<String> tempMeaning = Arrays.asList(i.getMeaning().split(","));

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

        String[] m = meaning.stream().toArray(String[]::new);
        String[] w= word.toArray(new String[0]);

        TestInfoDto t = new TestInfoDto(m,w,type);

        return t;

    }


}


