package forever.vocaAllday.controller;

import forever.vocaAllday.dto.request.MyPageDto;
import forever.vocaAllday.dto.response.ResultDto;
import forever.vocaAllday.dto.response.TitleDto;
import forever.vocaAllday.dto.response.UserInfoDto;
import forever.vocaAllday.enums.ExamType;
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
        UserInfoDto userInfoDto = myPageService.getUserInfo(principal.getName());
        model.addAttribute("UserInfoDto",userInfoDto);
        model.addAttribute("vocaTitleList", vocaTitleList);
        return "report/myReport";
    }

    @PostMapping("/mypage")
    public String selectVocaTitle(@ModelAttribute TitleDto titleDto, RedirectAttributes redirectAttr) {
        String vocaTitle = titleDto.getVocaTitle();
        redirectAttr.addAttribute("title", vocaTitle);
        return "redirect:/mypage/info";
    }

    @GetMapping("/mypage/info")
    public String showVocaInfo(Principal principal, @RequestParam("title") String title, Model model) {
        String email = principal.getName();
        ResultDto resultDto = myPageService.showVocaInfo(email, title);
        model.addAttribute("vocaTitle", title);
        model.addAttribute("resultDto", resultDto);
        UserInfoDto userInfoDto = myPageService.getUserInfo(principal.getName());
        model.addAttribute("UserInfoDto",userInfoDto);
        return "report/reTest";
    }

    @PostMapping("/mypage/info")
    public String selectReExamInfo(@ModelAttribute MyPageDto myPageDto,
                                   RedirectAttributes redirectAttr) {
        String title = myPageDto.getVocaTitle();
        ExamType examType = myPageDto.getExamType();

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