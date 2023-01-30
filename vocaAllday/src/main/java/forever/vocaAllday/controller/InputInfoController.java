package forever.vocaAllday.controller;

import forever.vocaAllday.dto.InputInfoDto;
import forever.vocaAllday.enums.ExamType;
import forever.vocaAllday.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class InputInfoController {
    private final ReportService reportService;

    @GetMapping(value = "/")
    public String infoForm() {
        return "makeTest/makeTest";

    }

    @PostMapping(value = "/")
    public String createReport(@ModelAttribute InputInfoDto inputInfoDto, Principal principal,
                               RedirectAttributes redirectAttr, Model model) {
        String email = principal.getName();
        ExamType examType = inputInfoDto.getExamType();

        String title = inputInfoDto.getVocaTitle();

        try {
            reportService.saveReport(inputInfoDto, email);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "makeTest/makeTest";
        }

        if(ExamType.EXAMPLE_SENTENCE.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            return "redirect:/test/example-sentence";
        } else if(ExamType.MEANING.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/test/word";
        } else if(ExamType.WORD.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/test/word";
        }

        return "redirect:/";
    }


}