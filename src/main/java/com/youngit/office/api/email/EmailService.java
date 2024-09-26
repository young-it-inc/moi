package com.youngit.office.api.email;

import com.youngit.office.api.bill.dto.BillDto;
import com.youngit.office.api.estimate.dto.EstimateDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * 견적서 메일 발송 (엑셀)
     */
    public int sendEmailEstimateWithAttachment(String from, String to, String subject, String text, EstimateDto estimateDto, File file) throws MessagingException, jakarta.mail.MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // 첨부파일 추가
        FileSystemResource fileResource = new FileSystemResource(file);
        helper.addAttachment("견적서.xlsx", fileResource);

        emailSender.send(message);
        return 1;
    }

    /**
     * 설치팀 내역서 메일 발송 (엑셀)
     */
    public int sendEmailBillAttachment(String from, String to, String subject, String text, BillDto billDto, File file) throws MessagingException, jakarta.mail.MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        // 첨부파일 추가
        FileSystemResource fileResource = new FileSystemResource(file);
        helper.addAttachment("견적서.xlsx", fileResource);

        emailSender.send(message);
        return 1;
      }
}
