package forever.vocaAllday.controller;

import forever.vocaAllday.dto.TestInfoDto;
import forever.vocaAllday.dto.ValueFormDto;
import forever.vocaAllday.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping(value = "/test")
@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/word")
    public String showWordTest(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type, Model model) {

        String email = principal.getName();
        TestInfoDto t = testService.makeTest(email, title, type);

        model.addAttribute("testInfo",t);
        model.addAttribute("valueFormDto",new ValueFormDto());

        return "makeTest/makeTest";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute("userValue") ValueFormDto valueFormDto,
                               Principal principal) {

        return "redirect:/"; //forward

    }


}

