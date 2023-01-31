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

public class CrawlingService {
    private final ReportRepository reportRepository;

    private final MemberRepository memberRepository;

    private static String URL = "https://ko.ichacha.net/sentence/";

    public CrawlingService(ReportRepository reportRepository, MemberRepository memberRepository) {
        this.reportRepository = reportRepository;
        this.memberRepository = memberRepository;
    }

    public Map<String, String> crawling (String ARR ) throws IOException{

        String KEY_WORD = ARR;

        Document doc = Jsoup.connect(URL + getParameter(KEY_WORD)).get();
        Elements el = doc.getElementsByAttributeValue("class","sent_list");

        String sent = el.select("li").get(0).text();
        String word = el.select("li").get(0).select("font").text();
        String sentence = makeBlank(sent,word);

        HashMap<String,String> map = new HashMap<String,String>();

        map.put(sentence,word);

        return map;
    }

    public static String getParameter(String KEY_WORD){
        String params = KEY_WORD + ".html";
        return params;
    }

    public  InputVoca  getInputVoca(String email, String vocaTitle){
        Member member = memberRepository.findByEmail(email);
        Long id = member.getId();
        Report report = reportRepository.findByReport(id,vocaTitle);
        InputVoca inputVoca = report.getInputVoca();
        return inputVoca;
    }


    public static String makeBlank(String sentence, String word){
        String sent = sentence.replace(word, "_______");
        return sent;
    }

    public SentenceInfoDto makeTest (String email, String vocaTitle) throws IOException {
        InputVoca i = getInputVoca(email,vocaTitle);
        List<String> tempWord = Arrays.asList(i.getWord().split(","));

        Collections.shuffle(tempWord);

        HashMap<String, String> map = new HashMap<String, String>();

        int len = tempWord.size();

        for(int j=0;j<len;j++){
            map = (HashMap<String, String>) crawling(tempWord.get(j));
        }

        List<String> tempsentence = new ArrayList(map.keySet());
        List<String> tempanswer= new ArrayList<String>();

        Iterator<String> it = tempsentence.iterator();

        while (it.hasNext()) {
            String s = it.next();
            tempanswer.add(map.get(s));
        }

        SentenceInfoDto sentinfo = new SentenceInfoDto(tempsentence,tempanswer , tempWord);
        return sentinfo;
    }
}
