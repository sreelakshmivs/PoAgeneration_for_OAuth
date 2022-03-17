package com.example.psqljwt.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//::::::::::::::NO NEED OF THIS CLASS FILE::::::::JUST KEEPING::::::::::

@RestController
@RequestMapping("/api/resourceserver")
public class AuthorizationcCodeResource {
    @GetMapping("")
    public String getAuthorizationCode(HttpServletRequest request) {
        //int clientCode = (Integer) request.getAttribute("userId");
        //List<Category> categories = categoryService.fetchAllCategories(userId);
        //return new ResponseEntity<>(categories, HttpStatus.OK);
        return "Authorization code";
    }
}
