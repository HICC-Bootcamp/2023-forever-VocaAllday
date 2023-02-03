package forever.vocaAllday.controller;

import forever.vocaAllday.dto.request.SentenceFormDto;
import forever.vocaAllday.dto.request.WordFormDto;
import forever.vocaAllday.dto.response.ResultDto;
import forever.vocaAllday.dto.response.SentenceInfoDto;
import forever.vocaAllday.dto.response.UserInfoDto;
import forever.vocaAllday.dto.response.WordInfoDto;
import forever.vocaAllday.service.CrawlingService;
import forever.vocaAllday.service.ExamService;
import forever.vocaAllday.service.GradeService;
import forever.vocaAllday.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@RequestMapping(value = "/exam")
@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final GradeService gradeService;
    private final CrawlingService crawlingService;

    private final MyPageService myPageService;


    @GetMapping(value = "/word")
    public String showWordExam(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type, Model model) {

        String email = principal.getName();
        WordInfoDto exam = examService.makeExam(email, title, type);
        UserInfoDto userInfoDto = myPageService.getUserInfo(email);
        model.addAttribute("wordInfoDto", exam);
        model.addAttribute("UserInfoDto",userInfoDto);

        return "makeTest/solveTestWord";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute WordFormDto wordFormDto, Principal principal,
                               RedirectAttributes redirectAttr) {
        String email = principal.getName();
        gradeService.grade(email, wordFormDto);
        String title = wordFormDto.getVocaTitle();
        redirectAttr.addAttribute("title", title);

        return "redirect:/exam/word/grading-result";

    }

    @GetMapping(value = "/example-sentence")
    public String showEXSentence(Principal principal, @RequestParam("title") String title,
                                 @RequestParam("type") String type, Model model) throws IOException {

        String email = principal.getName();
        SentenceInfoDto examInfo = crawlingService.makeTest(email, title);
        UserInfoDto userInfoDto = myPageService.getUserInfo(email);

        model.addAttribute("sentenceInfo", examInfo);
        model.addAttribute("SentenceFormDto", new SentenceFormDto());
        model.addAttribute("UserInfoDto",userInfoDto);

        return "makeTest/solveTestSentence";
    }

    @PostMapping(value = "/example-sentence")
    public String GetUservalue(@ModelAttribute SentenceFormDto sentenceFormDto, Principal principal,
                               RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        gradeService.gradeTest(email, sentenceFormDto);

        String title = sentenceFormDto.getVocaTitle();
        redirectAttributes.addAttribute("title", title);


        return "redirect:/exam/sentence/grading-result";// 추후 수정

    }
    @GetMapping(value = "/sentence/grading-result")
    public String ShowGradingresult(Principal principal, Model model,
                                    @RequestParam("title") String title) {
        String email = principal.getName();
        ResultDto resultDto = gradeService.showGradingResult(email, title);
        model.addAttribute("resultDto", resultDto);
        return "makeTest/gradeTestSentence";//추후 수정
    }

    @GetMapping(value = "/word/grading-result")
    public String ShowGradingResult(Principal principal, Model model,
                                    @RequestParam("title") String title) {
        String email = principal.getName();
        ResultDto resultDto = gradeService.showGradingResult(email, title);
        UserInfoDto userInfoDto = myPageService.getUserInfo(email);

        model.addAttribute("UserInfoDto",userInfoDto);
        model.addAttribute("resultDto", resultDto);
        return "makeTest/gradeTestWord";
    }

}
