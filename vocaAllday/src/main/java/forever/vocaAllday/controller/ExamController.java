package forever.vocaAllday.controller;

import forever.vocaAllday.dto.ExamInfoDto;
import forever.vocaAllday.dto.ResultDto;
import forever.vocaAllday.dto.SentenceInfoDto;
import forever.vocaAllday.dto.ValueFormDto;
import forever.vocaAllday.service.CrawlingService;
import forever.vocaAllday.service.ExamService;
import forever.vocaAllday.service.GradeService;
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


    @GetMapping(value = "/word")
    public String showWordExam(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type, Model model) {

        String email = principal.getName();
        ExamInfoDto exam = examService.makeExam(email, title, type);
        model.addAttribute("examInfoDto", exam);

        return "makeTest/solveTestWord";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute ValueFormDto valueFormDto, Principal principal,
                               RedirectAttributes redirectAttr) {
        String email = principal.getName();
        gradeService.grade(email, valueFormDto);
        String title = valueFormDto.getVocaTitle();
        redirectAttr.addAttribute("title", title);

        return "redirect:/word/grading-result";

    }

    @GetMapping(value = "/example-sentence")
    public String showEXSentence(Principal principal, @RequestParam("title") String title,
                                 @RequestParam("type") String type, Model model) throws IOException {

        String email = principal.getName();
        SentenceInfoDto examInfo = crawlingService.makeTest(email, title);

        model.addAttribute("sentenceInfo", examInfo);
        model.addAttribute("valueFormDto", new ValueFormDto());

        return "makeTest/solveTestSentence";
    }

    @PostMapping(value = "/example-sentence")
    public String GetUservalue(@ModelAttribute("userValue") ValueFormDto valueFormDto) {

        return "test/word";

    }

    @GetMapping(value = "/word/grading-result")
    public String ShowGradingResult(Principal principal,, Model model,
                                    @RequestParam("title") String title) {
        String email = principal.getName();
        ResultDto resultDto = gradeService.showGradingResult(email, title);
        model.addAttribute("resultDto", resultDto);
        return "makeTest/gradeTestWord";
    }

}

