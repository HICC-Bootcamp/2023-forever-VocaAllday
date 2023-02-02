package forever.vocaAllday.controller;

import forever.vocaAllday.dto.MyPageDto;
import forever.vocaAllday.dto.ResultDto;
import forever.vocaAllday.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/mypage")
    public String showVocaTitleList(Principal principal, Model model) {
        List<String> vocaTitleList = myPageService.getVocaTitleList(principal.getName());
        model.addAttribute("vocaTitleList", vocaTitleList);
        return "mypage";
    }

    @PostMapping("/mypage")
    public String selectVocaTitle(@RequestBody String vocaTitle,
                                  RedirectAttributes redirectAttr) {
        redirectAttr.addAttribute("title", vocaTitle);
        return "redirect:/mypage/word";
    }

    @GetMapping("/mypage/info")
    public String showVocaInfo(Principal principal, @RequestParam("title") String title, Model model) {
        String email = principal.getName();
        ResultDto resultDto = myPageService.showVocaInfo(email, title);
        model.addAttribute("vocaTitle", title);
        model.addAttribute("resultDto", resultDto);
        return "mypage/info";
    }

    @PostMapping("/mypage/info")
    public String selectReExamInfo(@ModelAttribute MyPageDto myPageDto,
                                   RedirectAttributes redirectAttr) {
        String title = myPageDto.getVocaTitle();
        String examType = myPageDto.getExamType();

        if (forever.vocaAllday.enums.ExamType.EXAMPLE_SENTENCE.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/example-sentence";
        } else if (forever.vocaAllday.enums.ExamType.MEANING.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/word";
        } else if (forever.vocaAllday.enums.ExamType.WORD.equals(examType)) {
            redirectAttr.addAttribute("title", title);
            redirectAttr.addAttribute("type", examType);
            return "redirect:/exam/word";
        }
        return "redirect:/";
    }
}
