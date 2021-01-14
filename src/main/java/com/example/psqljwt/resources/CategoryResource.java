package com.example.psqljwt.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        //List<Category> categories = categoryService.fetchAllCategories(userId);
        //return new ResponseEntity<>(categories, HttpStatus.OK);
        return "Authenticated! UserID: " + userId;
    }
}
