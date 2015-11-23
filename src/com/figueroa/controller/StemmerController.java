package com.figueroa.controller;

import com.figueroa.nlp.StemmerMain;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StemmerController {

    @RequestMapping(value="/stemmer", method = RequestMethod.GET)
    public String getPage(ModelMap model, HttpServletRequest request) {

        model.addAttribute("originalText", "");
        model.addAttribute("stemmedText", "");
        // Prepare the result view:
        return "stemmer";
    }
    
    @RequestMapping(value="/stemmer", method = RequestMethod.POST)
    public String stemText(ModelMap model, HttpServletRequest request) {
        
        String path = request.getSession().getServletContext().getRealPath("/");
        
        StemmerMain stemmer = new StemmerMain(path);
        
        try {
            String stemmedText;
            String text = request.getParameter("text");
            if (text != null) {
                stemmedText = stemmer.stemText(text);
            }
            else {
                text = "";
                stemmedText = "";
            }

            model.addAttribute("originalText", text);
            model.addAttribute("stemmedText", stemmedText);
            
            // Prepare the result view:
            return "stemmer";
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }
}