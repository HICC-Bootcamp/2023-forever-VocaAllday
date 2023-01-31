package forever.vocaAllday.controller;

import forever.vocaAllday.dto.ExamInfoDto;
import forever.vocaAllday.dto.ValueFormDto;
import forever.vocaAllday.service.ExamService;
import forever.vocaAllday.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping(value = "/exam")
@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final GradeService gradeService;

    @GetMapping(value = "/word")
    public String showWordExam(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type, Model model) {

        String email = principal.getName();
        ExamInfoDto exam = examService.makeExam(email, title, type);
        model.addAttribute("examInfoDto",exam);

        return "makeTest/solveTestWord";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute ValueFormDto valueFormDto,
                               Principal principal) {
        String email = principal.getName();
        gradeService.grade(email,valueFormDto);

        return "redirect:/"; //forward

    }


}

