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


    //매개변수: 문자열(사용자가 입력한 영단어)
    //반환값: <빈칸뚫린문장, 답>
    //기능: 영단어로 크롤링해서 <빈칸뚫린문장, 답>을 전달
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


    public static String makeBlank(String sentence, String word){//예문에 빈칸을 뚫어 반환
        String sent = sentence.replace(word, "_______");
        return sent;
    }

    public SentenceInfoDto makeTest (String email, String vocaTitle) throws IOException {
        InputVoca i = getInputVoca(email,vocaTitle);//inputvoca 가져옴
        List<String> tempWord = Arrays.asList(i.getWord().split(","));//inputvoca에서 가져온 영단어를 ,기준으로 분리->리스트 저장

        Collections.shuffle(tempWord);//영단어의 순서를 셔플

        HashMap<String, String> map = new HashMap<String, String>();//<문장,답> 저장할 맵 생성

        int len = tempWord.size();//사용자가 입력한 단어의 개수 세기

        for(int j=0;j<len;j++){
            map = (HashMap<String, String>) crawling(tempWord.get(j));//리스트에 저장된 순서가 랜덤이 영단어를 하나씩 크롤링해 문장과 답 가져옴
        }

        List<String> tempsentence = new ArrayList(map.keySet());//문장을 저장할 리스트 생성
        List<String> tempanswer= new ArrayList<String>();//답을 저장할 리스트 생성

        Iterator<String> it = tempsentence.iterator();

        while (it.hasNext()) {
            String s = it.next();
            tempanswer.add(map.get(s));
        }

        SentenceInfoDto sentinfo = new SentenceInfoDto(tempsentence,tempanswer , tempWord);
        return sentinfo;
    }
}
