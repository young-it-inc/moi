package com.youngit.office.api.email;

import com.youngit.office.api.estimate.dto.EstimateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * 견적서 발송 (엑셀)
     */
    public int sendEmailEstimate(EstimateDto estimateDto) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(estimateDto.getEmail());
        message.setSubject(estimateDto.getEstimateNote());
        message.setText(estimateDto.getClientName());
        emailSender.send(message);
        return 1;
      }

    /**
     * 세금계산서 발송 (엑셀)
     */
    public void sendEmailTaxInvoice(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
      }

}
