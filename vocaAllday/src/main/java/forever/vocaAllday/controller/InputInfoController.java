package forever.vocaAllday.controller;

import forever.vocaAllday.dto.InputInfoDto;
import forever.vocaAllday.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String createReport(@ModelAttribute InputInfoDto inputInfoDto, Principal principal) {
        String email = principal.getName();
        String testType = inputInfoDto.getTestType().toString();

        try {
            reportService.saveReport(inputInfoDto, email);
        } catch (Exception e) {
            e.getMessage();
        }

        if (testType == "EXAMPLE_SENTENCE") {
            return "redirect:/test/example-sentence";
        } else if (testType == "MEANING") {
            return "redirect:/test/meaning";
        } else if (testType == "WORD") {
            return "redirect:/test/word";
        }

        return "redirect:/";
    }


}