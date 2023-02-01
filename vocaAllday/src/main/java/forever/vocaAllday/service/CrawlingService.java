package forever.vocaAllday.service;

import forever.vocaAllday.dto.SentenceInfoDto;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.entity.Report;
import forever.vocaAllday.repository.MemberRepository;
import forever.vocaAllday.repository.ReportRepository;
import java.io.IOException;
import java.util.*;

import forever.vocaAllday.entity.InputVoca;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService {
    private final ReportRepository reportRepository;

    private final MemberRepository memberRepository;

    private static String URL = "https://ko.ichacha.net/sentence/";

    public CrawlingService(ReportRepository reportRepository, MemberRepository memberRepository) {
        this.reportRepository = reportRepository;
        this.memberRepository = memberRepository;
    }

    public List<String > crawling (String ARR ) throws IOException{

        String KEY_WORD = ARR;
        String params = KEY_WORD + ".html";

        Document doc = Jsoup.connect(URL + params).get();
        Elements el = doc.getElementsByAttributeValue("class","sent_list");

        String sent = el.select("li").get(0).text();
        String word = el.select("li").get(0).select("font").text();

        String sentence = sent.replace(word,"_______");

        List<String > sent_word =  new ArrayList<String>();
        sent_word.add(sentence);
        sent_word.add(word);

        return sent_word;
    }

    public  InputVoca  getInputVoca(String email, String vocaTitle){
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id,vocaTitle);
        InputVoca inputVoca = report.getInputVoca();
        return inputVoca;
    }

    public SentenceInfoDto makeTest (String email, String vocaTitle) throws IOException {
        InputVoca inputvoca = getInputVoca(email,vocaTitle);
        List<String> tempWord = Arrays.asList(inputvoca.getWord().split(","));

        Collections.shuffle(tempWord);

        HashMap<String, String> map = new HashMap<String, String>();

        int len = tempWord.size();

        for(int j=0;j<len;j++){
            List<String> templist = crawling(tempWord.get(j));
            map.put(templist.get(0),templist.get(1));
        }

        List<String> tempsentence = new ArrayList(map.keySet());
        List<String> tempanswer= new ArrayList<String>();

        Iterator<String> it = tempsentence.iterator();

        while (it.hasNext()) {
            String key = it.next();
            tempanswer.add(map.get(key));
        }

        SentenceInfoDto sentinfo = new SentenceInfoDto(tempsentence,tempanswer , tempWord);
        return sentinfo;
    }
}
