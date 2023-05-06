package com.encryptlab14.controllers;

import com.encryptlab14.models.SiteUser;
import com.encryptlab14.repositories.PostRepo;
import com.encryptlab14.repositories.SiteUserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    SiteUserRepo siteUserRepo;
    @Autowired
    PostRepo postRepo;

    @GetMapping("/")
    public String splash() {
        return "splash";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signUp(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
        SiteUser siteUser = new SiteUser(username, hashedPassword);

        siteUserRepo.save(siteUser);
        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView loginToTestPage(HttpServletRequest request, String username, String password) {
        SiteUser siteUser = siteUserRepo.getSiteUserByUsername(username);

        if (siteUser == null) {
            return new RedirectView("/?message=Bad login");
        } else if (BCrypt.checkpw(password, siteUser.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return new RedirectView("/" + siteUser.getUsername());
        } else {
            return new RedirectView("/?message=Bad login");
        }
    }

}