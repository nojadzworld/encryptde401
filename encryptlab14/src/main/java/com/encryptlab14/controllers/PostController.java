package com.encryptlab14.controllers;

import com.encryptlab14.models.Post;
import com.encryptlab14.models.SiteUser;
import com.encryptlab14.repositories.PostRepo;
import com.encryptlab14.repositories.SiteUserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    SiteUserRepo siteUserRepo;
    @Autowired
    PostRepo postRepo;

@GetMapping("/fav-songs/{username}")
    public String getFavSongs(HttpServletRequest request, Model m, @PathVariable String username) {
    try {
        HttpSession session = request.getSession();
        String sessionUsername = (String) session.getAttribute("username");
        if (sessionUsername == null || !sessionUsername.equals(username)) {
            return "redirect:/login";
        } else {
            List<Post> allPosts = postRepo.findAllByOrderByDateDesc();
            m.addAttribute("allPosts", allPosts);
            SiteUser siteUser = siteUserRepo.getSiteUserByUsername(username);
            m.addAttribute("siteUser", siteUser);
            return "fav-songs";
        }
    } catch (Exception e) {
        return "redirect:/login";
    }
}
@PostMapping("/fav-songs/{username}/add-post")
    public RedirectView addNewPost(@PathVariable String username, String postContent) {
    Date date = new Date();
    Post post = new Post(postContent, date);
    SiteUser siteUser = siteUserRepo.getSiteUserByUsername(username);
    siteUser.addPost(post);
    postRepo.save(post);
    siteUserRepo.save(siteUser);
    return new RedirectView("/fav-songs/" + siteUser.getUsername());
}
}
