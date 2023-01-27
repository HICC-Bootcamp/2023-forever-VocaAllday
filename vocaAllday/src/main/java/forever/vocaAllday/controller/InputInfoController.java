package forever.vocaAllday.controller;

import forever.vocaAllday.TestInfo;
import forever.vocaAllday.dto.InputVocaDto;
import forever.vocaAllday.dto.ReportDto;
import forever.vocaAllday.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class InputInfoController {
    private final ReportService reportService;

    @GetMapping(value="/")
    public String infoForm(Model model){
        model.addAttribute("ReportDto", new ReportDto());
        model.addAttribute("InputVoca", new InputVocaDto());
        model.addAttribute("TestInfo", new TestInfo());
        return "makeTest/makeTest";

    }

    @PostMapping(value = "/")
    public String createReport(InputVocaDto inputVocaDto, ReportDto reportDto, TestInfo testInfo, Principal principal) {
        String email = principal.getName();
        String vocaTitle = reportDto.getVocaTitle();
        String testType = testInfo.getTestType();

        try {
            reportService.saveReport(inputVocaDto, reportDto, email);
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
