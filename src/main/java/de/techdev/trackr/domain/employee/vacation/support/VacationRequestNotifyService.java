package de.techdev.trackr.domain.employee.vacation.support;

import de.techdev.trackr.core.mail.MailService;
import de.techdev.trackr.domain.employee.login.support.SupervisorService;
import de.techdev.trackr.domain.employee.vacation.VacationRequest;
import de.techdev.trackr.domain.employee.vacation.VacationRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.text.SimpleDateFormat;

/**
 * @author Moritz Schulze
 */
public class VacationRequestNotifyService {

    @Autowired
    private MailService mailService;

    @Autowired
    private SupervisorService supervisorService;

    public void sendEmailNotification(VacationRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@techdev.de");
        mailMessage.setTo(request.getEmployee().getCredential().getEmail());
        mailMessage.setSubject("Your vacation request has been " + statusPastVerb(request.getStatus()));
        mailMessage.setText(getStatusMailText(request));
        mailService.sendMail(mailMessage);
    }

    protected String statusPastVerb(VacationRequestStatus status) {
        if(status == VacationRequestStatus.APPROVED) {
            return "approved";
        } else if (status == VacationRequestStatus.REJECTED) {
            return "rejected";
        } else {
            return "is pending";
        }
    }

    protected String getStatusMailText(VacationRequest request) {
        if(request.getApprover() == null) {
            return "Your vacation request has been automatically approved.";
        } else {
            return request.getApprover().fullName() + " has " + statusPastVerb(request.getStatus()) + " your vacation request from " + request.getSubmissionTime();
        }
    }

    /**
     * Send a new vacation request notification to all supervisors.
     */
    public void notifySupervisors(VacationRequest vacationRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String[] receiver = supervisorService.getSupervisorEmailsAsArray();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String subject = "New vacation request from " + vacationRequest.getEmployee().fullName();
        String text = "New vacation request from " + vacationRequest.getEmployee().fullName() + " for " + sdf.format(vacationRequest.getStartDate()) + " - " + sdf
                .format(vacationRequest.getEndDate()) + ".";
        mailMessage.setSubject(subject);
        mailMessage.setTo(receiver);
        mailMessage.setText(text);
        mailMessage.setFrom("no-reply@techdev.de");
        mailService.sendMail(mailMessage);
    }
}
