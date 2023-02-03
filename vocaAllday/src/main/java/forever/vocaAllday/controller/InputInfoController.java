package forever.vocaAllday.controller;

import forever.vocaAllday.dto.request.InputInfoDto;
import forever.vocaAllday.dto.response.UserInfoDto;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.enums.ExamType;
import forever.vocaAllday.service.MyPageService;
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

    private final MyPageService myPageService;

    @GetMapping(value = "/")
    public String infoForm(Model model, Principal principal) {
        UserInfoDto userInfoDto = myPageService.getUserInfo(principal.getName());
        model.addAttribute("UserInfoDto", userInfoDto);
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
            System.out.println(e.getMessage());
            if (e.getMessage().equals("vocaTitleError")) {
                System.out.println(e.getMessage());
                model.addAttribute("vocaTitleError", e.getMessage());

                return "makeTest/makeTest";
            } else if (e.getMessage().equals("wordError")) {
                model.addAttribute("wordError", e.getMessage());
                return "makeTest/makeTest";
            }

        }

        if (ExamType.EXAMPLE_SENTENCE.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/example-sentence";
        } else if (ExamType.MEANING.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/word";
        } else if (ExamType.WORD.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/word";
        }

        return "redirect:/";
    }


}
