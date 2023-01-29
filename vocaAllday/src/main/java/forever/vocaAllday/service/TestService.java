package forever.vocaAllday.service;


import forever.vocaAllday.entity.InputVoca;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
import forever.vocaAllday.repository.MemberRepository;
import forever.vocaAllday.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    public Cookie[] makeCookie(Map<String, String[]> list) throws UnsupportedEncodingException {
        String m = String.join(", ", list.get("meaning"));
        String w = String.join(", ", list.get("word"));
        m = URLEncoder.encode(m, "utf-8").replaceAll("\\+", "%20");
        w = URLEncoder.encode(w, "utf-8").replaceAll("\\+", "%20");

        Cookie[] temp = new Cookie[2];
        temp[0] = new Cookie("mList", m);
        temp[1] = new Cookie("wList", w);
        temp[0].setPath("/"); temp[1].setPath("/"); // 추후 수정
        return temp;
    }

    public InputVoca getInputVoca(String email, String vocaTitle) {
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id, vocaTitle);
        InputVoca inputVoca = report.getInputVoca();
        return inputVoca;
    }

    public Map<String, String[]> makeTest(String email, String vocaTitle) {

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

        Map<String, String[]> result = new HashMap<String, String[]>();

        String[] m = meaning.stream().toArray(String[]::new);
        String[] w= word.toArray(new String[0]);

        result.put("meaning", m);
        result.put("word", w);

        return result;

    }


}


