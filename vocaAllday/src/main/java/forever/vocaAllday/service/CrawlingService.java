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

    public CrawlingService(ReportRepository reportRepository, MemberRepository memberRepository) {
        this.reportRepository = reportRepository;
        this.memberRepository = memberRepository;
    }

    public List<String > crawling (String ARR ) throws IOException{

        String URL = "https://ko.ichacha.net/sentence/";

        String params = ARR + ".html";

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
        List<String> tempMeaning = Arrays.asList(inputvoca.getMeaning().split(","));

        HashMap<String, String> map1 = new HashMap<String, String>();//<문장,답>
        HashMap<String, String> map2 = new HashMap<String, String>();//<보기,뜻>

        for (int j = 0; j < tempMeaning.size(); j++) {
            map2.put(tempWord.get(j), tempMeaning.get(j));
        }
        List<String> word = new ArrayList(map2.keySet());
        List<String> meaning = new ArrayList<String>();
        Collections.shuffle(word);

        Iterator<String> it = word.iterator();

        while (it.hasNext()) {
            String s = it.next();
            meaning.add(map2.get(s));
        }

        int len = word.size();

        for(int j=0;j<len;j++){
            List<String> templist = crawling(word.get(j));
            map1.put(templist.get(0),templist.get(1));
        }

        List<String> tempsentence = new ArrayList(map1.keySet());
        List<String> tempanswer= new ArrayList<String>();

        Iterator<String> its = tempsentence.iterator();

        while (its.hasNext()) {
            String key = its.next();
            tempanswer.add(map1.get(key));
        }

        SentenceInfoDto sentinfo = new SentenceInfoDto(tempsentence, tempanswer , word, meaning);
        return sentinfo;
    }
}
