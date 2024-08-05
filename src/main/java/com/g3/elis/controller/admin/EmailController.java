package com.g3.elis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.g3.elis.service.UserService;
import com.g3.elis.util.EmailSendResult;
import com.g3.elis.util.EmailSenderService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("emailSendTo") String emailSendTo,
                            @RequestParam("externalEmail") String externalEmail,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body,
                            RedirectAttributes redirectAttributes) {

        List<String> recipientEmails = new ArrayList<>();
        switch (emailSendTo) {
            case "All Admin":
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_ADMIN"));
                break;
            case "Instructors":
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_INSTRUCTOR"));
                break;
            case "Students":
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_STUDENT"));
                break;
            case "All":
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_ADMIN"));
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_INSTRUCTOR"));
                recipientEmails.addAll(userService.getEmailsByRole("ROLE_STUDENT"));
                break;
            default:
                break;
        }

        if (externalEmail != null && !externalEmail.isEmpty()) {
            recipientEmails.add(externalEmail);
        }

        List<EmailSendResult> results = emailSenderService.sendEmail(recipientEmails, subject, body);

        StringBuilder successMessage = new StringBuilder("Email send results:\n");
        for (EmailSendResult result : results) {
            successMessage.append(result.getRecipient()).append(": ").append(result.getStatus()).append("\n");
        }

        redirectAttributes.addFlashAttribute("message", successMessage.toString());

        return "redirect:/admin/admin-setting"; // Redirect to the original page
    }
}
