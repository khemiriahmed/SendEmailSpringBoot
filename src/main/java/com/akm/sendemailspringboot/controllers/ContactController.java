package com.akm.sendemailspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akm.sendemailspringboot.models.ContactDto;
import com.akm.sendemailspringboot.services.EmailSenderService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	private EmailSenderService emailSender;
    @GetMapping({ "", "/" })
    public String getContactForm(Model model) {
        model.addAttribute("contactDto", new ContactDto());
        return "contact";
    }

    @PostMapping({ "", "/" })
    public String saveContact(@Valid @ModelAttribute("contactDto") ContactDto contactDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {

            return "contact";
        }
          model.addAttribute("successMessage", "Your message is received correctly");
            model.addAttribute("contactDto", new ContactDto());
            String content = "Dear " + contactDto.getName() +"\n\n" + "Thanks so mush fr contacting us";
            emailSender.sendEmail(contactDto.getEmail(), "Contact confirmation", content);
         return "contact";
    }
}
