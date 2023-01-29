package forever.vocaAllday.controller;

import forever.vocaAllday.dto.ValueFormDto;
import forever.vocaAllday.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Map;

@RequestMapping(value = "/test")
@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/word")
    public String showWordTest(Principal principal, @RequestParam("title") String title,
                               @RequestParam("type") String type,
                               HttpServletResponse response, Model model) {

        String email = principal.getName();
        Map<String, String[]> list = testService.makeTest(email, title);

        model.addAttribute("mList", list.get("meaning"));
        model.addAttribute("wList", list.get("word"));
        model.addAttribute("type",type);

        try{
            Cookie[] cList = testService.makeCookie(list);
            response.addCookie(cList[0]);
            response.addCookie(cList[1]);
        }catch ( UnsupportedEncodingException e){
            return "error";
        }

        return "makeTest/makeTest";
    }

    @PostMapping(value = "/word")
    public String GetUserValue(@ModelAttribute("userValue") ValueFormDto valueFormDto, Principal principal) {
        return "redirect:/";
    }

}