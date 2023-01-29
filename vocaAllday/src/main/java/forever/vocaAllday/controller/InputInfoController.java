package forever.vocaAllday.controller;

import forever.vocaAllday.dto.InputInfoDto;
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
        String testType = inputInfoDto.getTestType().toString();
        String title = inputInfoDto.getVocaTitle();
        int n = inputInfoDto.getNumOfQuestions();

        try {
            reportService.saveReport(inputInfoDto, email);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "makeTest/makeTest";
        }

        if (testType == "EXAMPLE_SENTENCE") {
            redirectAttr.addAttribute("title", title);
            return "redirect:/test/example-sentence";
        } else if (testType == "MEANING") {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", testType);
            return "redirect:/test/word";
        } else if (testType == "WORD") {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", testType);
            return "redirect:/test/word";
        }

        return "redirect:/";
    }


}