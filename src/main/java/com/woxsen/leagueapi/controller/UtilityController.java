package com.woxsen.leagueapi.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.service.BranchService;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.ArenaTypes;
import com.woxsen.leagueapi.utils.CourseTypes;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/util")
public class UtilityController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping("/course-types")
    public ResponseEntity<Map> getCoursesTypes(){
        HashMap<String,String> types=new HashMap<>();
        Arrays.asList(CourseTypes.values()).stream().forEach(x->{
            types.put(x.name(), x.name());
        });
        return new ResponseEntity<>( types, HttpStatus.OK);

    }
    @GetMapping("/arena-types")
    public ResponseEntity<Map> getArenaTypes(){
        HashMap<String,String> types=new HashMap<>();
        Arrays.asList(ArenaTypes.values()).stream().forEach(x->{
            types.put(x.name(), x.name());
        });
        return new ResponseEntity<>( types, HttpStatus.OK);

    }
    @GetMapping("/availability/email")
    public Boolean checkEmailAvailability(@RequestParam(required = false) String email) {
        if (email == null) {
            throw new BadRequestException("Mention the query parameter email");
        }
        return userService.isEmailAvailability(email);

    }
    @GetMapping("/availability/username")
    public Boolean checkUserNameAvailability(@RequestParam(required = false) String userName) {
        if (userName == null) {
            throw new BadRequestException("Mention the query parameter userName");
        }
        return userService.isUserNameAvailability(userName);
    }
//    @RequestMapping(method = RequestMethod.POST)
//    @RequestMapping(
//            value = "/red",
//            method = RequestMethod.GET)
//    public void redirectToTwitter(HttpServletResponse httpServletResponse) throws IOException {
//        httpServletResponse.sendRedirect("https://twitter.com");
//    }
//    @PostMapping("/submitForm")
//    public RedirectView submitForm(
//                             RedirectAttributes redirectAttributes) {
//        // Do some processing with the form data
//    RedirectView redirectView=new RedirectView();
//    redirectView.setUrl("https://twitter.com");
//        // Add a message to the redirect attributes
//        redirectAttributes.addFlashAttribute("message", "Form submitted successfully!");
//
//        // Redirect to a URL
//        return redirectView;
//    }



}
