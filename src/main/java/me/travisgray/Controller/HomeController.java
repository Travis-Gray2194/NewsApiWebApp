package me.travisgray.Controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.travisgray.Models.Article;
import me.travisgray.Models.NewsFavorites;
import me.travisgray.Models.RootObject;
import me.travisgray.Models.User;
import me.travisgray.Repositories.NewsFavoritesRepository;
import me.travisgray.Repositories.UserRepository;
import me.travisgray.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ${TravisGray} on 11/13/2017.
 */

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    NewsFavoritesRepository newsFavoritesRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("news",new NewsFavorites());
        return "index3";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration2";
    }

    @PostMapping("/register")
    public String processregistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model ){

        model.addAttribute("user",user);
        if(result.hasErrors()){
            return "registration2";
        }else{
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "index3";
    }


    @RequestMapping("/newsapi")
    public String showlist(Model model) {

        StringBuilder stringBuilder =  new StringBuilder();
        RestTemplate restTemplate = new RestTemplate();
        RootObject rootObject = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=11dc03d697484293bf3d12a126d8a398",RootObject .class);
//        return rootObject.getArticles().get(0).getTitle();
//        return rootObject.getArticles().get(1).getUrlToImage().toString();

        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(rootObject.getArticles().get(1).getTitle());
        model.addAttribute("arttitle",stringBuilder1);

//

        StringBuilder articleimg = new StringBuilder();
        articleimg.append(rootObject.getArticles().get(1).getUrlToImage());
        model.addAttribute("artimg",articleimg);

        StringBuilder articledes = new StringBuilder();
        articledes.append(rootObject.getArticles().get(1).getDescription());
        model.addAttribute("artdes",articledes);


        StringBuilder articleauth = new StringBuilder();
        articleauth.append(rootObject.getArticles().get(1).getAuthor());
        model.addAttribute("artauth",articleauth);

//        StringBuilder articledate = new StringBuilder();
//        articledate.append(rootObject.getArticles().get(1).getPublishedAt().toString());
//        model.addAttribute("artdate",articledate);
        model.addAttribute("articles" , rootObject.getArticles());


        return "listcategories";

    }


    @RequestMapping("/sportsnewsapi")
    public String showsportslist(Model model) {

        StringBuilder stringBuilder =  new StringBuilder();
        RestTemplate restTemplate = new RestTemplate();
        RootObject rootObject = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=11dc03d697484293bf3d12a126d8a398",RootObject .class);
//        return rootObject.getArticles().get(0).getTitle();
//        return rootObject.getArticles().get(1).getUrlToImage().toString();

//        StringBuilder articledate = new StringBuilder();
//        articledate.append(rootObject.getArticles().get(1).getPublishedAt().toString());
//        model.addAttribute("artdate",articledate);
        model.addAttribute("articles" , rootObject.getArticles());


        return "sportscategories";

    }

    @RequestMapping("/musicnewsapi")
    public String showmusiclist(Model model) {

        StringBuilder stringBuilder =  new StringBuilder();
        RestTemplate restTemplate = new RestTemplate();
        RootObject rootObject = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?sources=mtv-news&apiKey=11dc03d697484293bf3d12a126d8a398",RootObject .class);
        model.addAttribute("articles" , rootObject.getArticles());


        return "musiccategories";

    }



//Testing Method for News APi
//    @RequestMapping("/testnewsapi")
//    public @ResponseBody String showtest(Model model) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        RootObject rootObject = restTemplate.getForObject("https://newsapi.org/v2/everything?q=sports&apiKey=11dc03d697484293bf3d12a126d8a398", RootObject.class);
//
//
//
////        return rootObject.getArticles().get(1).getPublishedAt().toString();
////
////        StringBuilder stringBuilder1 = new StringBuilder();
////        stringBuilder1.append(rootObject.getArticles().get(0).getTitle());
////        model.addAttribute("rootobjectart",stringBuilder1);
////
//////
////
////        StringBuilder articleimg = new StringBuilder();
////        articleimg.append(rootObject.getArticles().get(1).getUrlToImage());
////        model.addAttribute("artimg",articleimg);
////        return "listcategories";
//
//
//
//    }







    @GetMapping("/selecttopics")
    public String selecttopics(Model model){
        model.addAttribute("newsfavorites",new NewsFavorites());
        return "topicchoice2";

    }
    @PostMapping("/topic")
    public String showtopics(HttpServletRequest request,RootObject rootObject, Model model){
        String topic = request.getParameter("topic");
        RestTemplate restTemplate = new RestTemplate();
        rootObject= restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&category="+topic+"&apiKey=11dc03d697484293bf3d12a126d8a398", RootObject.class);
        model.addAttribute("articles",rootObject.getArticles());

        return "listcategories";
    }


    @GetMapping("/searchtopics")
    public String searchtopics(Model model){
        model.addAttribute("newsfavorites",new NewsFavorites());
        return "searchtopicchoice2";

    }

    @PostMapping("/searchtopic")
    public String searchtopics(HttpServletRequest request,RootObject rootObject, Model model){
        String topic = request.getParameter("topic");
        RestTemplate restTemplate = new RestTemplate();
        rootObject= restTemplate.getForObject("https://newsapi.org/v2/everything?q="+topic+"&apiKey=11dc03d697484293bf3d12a126d8a398", RootObject.class);
        model.addAttribute("articles",rootObject.getArticles());

        return "listcategories";
    }

    @GetMapping("/addtopics")
    public String addtopics(Model model){
        model.addAttribute("newsfavorites",new NewsFavorites());
        return "topicchoice3";

    }

    @PostMapping("/addtopic")
    public String addtopicstouserlist(HttpServletRequest request,RootObject rootObject, Model model,Authentication auth,@ModelAttribute("newsfavorites") NewsFavorites newsFavorites){
        String topicfav = request.getParameter("topic");
        RestTemplate restTemplate = new RestTemplate();
        rootObject= restTemplate.getForObject("https://newsapi.org/v2/everything?q="+topicfav+"&apiKey=11dc03d697484293bf3d12a126d8a398", RootObject.class);
        model.addAttribute("articles",rootObject.getArticles());
        newsFavorites.setTopic(topicfav);
        newsFavoritesRepository.save(newsFavorites);
        model.addAttribute("newsfavorites", newsFavoritesRepository.findAll());
        return "listcategorieswithtopics";
    }

    @GetMapping("/showalltopics")
    public String showtopics(Model model){
        model.addAttribute("newsfavorites", newsFavoritesRepository.findAll());
        return "listcategorieswithtopics";

    }

    @GetMapping("/removetopics")
    public String removetopicstouserlist(HttpServletRequest request,RootObject rootObject, Model model,Authentication auth,@ModelAttribute("newsfavorites") NewsFavorites newsFavorites){
        String topicfav = request.getParameter("topic");
        RestTemplate restTemplate = new RestTemplate();
        rootObject= restTemplate.getForObject("https://newsapi.org/v2/everything?q="+topicfav+"&apiKey=11dc03d697484293bf3d12a126d8a398", RootObject.class);
        model.addAttribute("articles",rootObject.getArticles());
        newsFavorites.setTopic(null);
        newsFavoritesRepository.save(newsFavorites);
        return "redirect:/showalltopics";
    }




    @RequestMapping("/secure")
    public String secure(HttpServletRequest request, Authentication authentication, Principal principal) {
        Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUSer = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails)
                authentication.getPrincipal();
        String username = principal.getName();
        return "secure";
    }
}
