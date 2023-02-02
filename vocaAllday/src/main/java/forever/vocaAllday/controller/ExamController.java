package forever.vocaAllday.controller;

import forever.vocaAllday.dto.*;
import forever.vocaAllday.service.CrawlingService;
import forever.vocaAllday.service.ExamService;
import forever.vocaAllday.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
    private final CrawlingService crawlingService;

    private final GradeService gradeService;


    @GetMapping(value = "/word")
    public String showWordExam(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type, Model model) {

        String email = principal.getName();
        ExamInfoDto exam = examService.makeExam(email, title, type);

        model.addAttribute("examInfoDto",exam);
        model.addAttribute("valueFormDto",new ValueFormDto());

        return "makeTest/solveTestWord";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute("userValue") ValueFormDto valueFormDto,
                               Principal principal) {

        return "redirect:/"; //forward

    }

    @GetMapping(value = "/example-sentence")
    public String showEXSentence(Principal principal, @RequestParam("title") String title,
                                 @RequestParam("type") String type, Model model) throws IOException {

        String email = principal.getName();
        SentenceInfoDto examInfo = crawlingService.makeTest(email,title);

        model.addAttribute("sentenceInfo",examInfo);
        model.addAttribute("SentenceFormDto",new SentenceFormDto());

        return "makeTest/solveTestSentence";
    }

    @PostMapping(value = "/example-sentence")
    public String GetUservalue(@ModelAttribute("userValue") SentenceFormDto sentenceFormDto, Principal principal,
                               RedirectAttributes redirectAttributes)  {
        String email = principal.getName();
        gradeService.gradeTest(email,sentenceFormDto);
        String title = sentenceFormDto.getVocaTitle();
        redirectAttributes.addAttribute("title",title);

        return "redirect:/exam/sentence/grading-result";// 추후 수정

    }
    @GetMapping(value = "/sentence/grading-result")
    public String ShowGradingResult(Principal principal, Model model,
                                    @RequestParam("title") String title) {
        String email = principal.getName();
        ResultDto resultDto = gradeService.showGradingResultsent(email, title);
        model.addAttribute("resultDto", resultDto);
        return "makeTest/gradeTestSentence";//추후 수정
    }


}

