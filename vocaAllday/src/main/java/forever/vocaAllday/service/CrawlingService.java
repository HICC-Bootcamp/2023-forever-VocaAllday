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

        HashMap<String, String> map = new HashMap<String, String>();//<보기,뜻>

        for (int j = 0; j <8; j++) {
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

        List<String > tempsent = new ArrayList<>();
        List<String > tempan = new ArrayList<>();

        for(int i=0;i<8;i++){
            List<String> templist = crawling(word.get(i));
            tempsent.add(templist.get(0));
            tempan.add(templist.get(1));
        }

        SentenceInfoDto sentinfo = new SentenceInfoDto(tempsent, tempan , word, meaning, vocaTitle);
        return sentinfo;
    }
}
