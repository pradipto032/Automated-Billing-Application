package com.craftMarine.emailSender.Controller;

import com.craftMarine.emailSender.Model.CustProdModel;
import com.craftMarine.emailSender.Repository.CustomerProductRepository;
import com.craftMarine.emailSender.RequestBody.CustProdRequestBody;
import com.craftMarine.emailSender.RequestBody.DeleteBillRequestBody;
import com.craftMarine.emailSender.Service.GmailSenderService;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CustomerProductController {

    @Autowired
    private GmailSenderService gmailSenderService;

    @Autowired
    private CustomerProductRepository customerProductRepository;

    //Mail sending controller. Used response to show the PDF in the web browser only.
    @PostMapping("/custprod/sendmail")
    public ResponseEntity<String> mailSending(@RequestBody CustProdRequestBody custProdRequestBody, HttpServletResponse response) throws MessagingException, IOException {
//        response.setContentType("application/pdf");
//        String headerkey = "Content-Disposition";
//        String headervalue = "attachment; filename=\"invoice.pdf\"";
//        response.setHeader(headerkey, headervalue);
        // Set the directory where you want to save the PDF
        String directoryPath = "../pdfs/";

        // Set the file name for the PDF
        String fileName = (customerProductRepository.findAll().size()+1)+"_"+custProdRequestBody.getCustDetails().getCustName()+".pdf";

        // Combine the directory path and file name to get the absolute file path
        String pdfFilePath = directoryPath + fileName;

        return new ResponseEntity<>(gmailSenderService.mailSender(custProdRequestBody, pdfFilePath, response), HttpStatus.OK);

    }

    //Controller for deleting a bill
    @DeleteMapping("/custprod/delete")
    public ResponseEntity<String> deleteBill(@RequestBody DeleteBillRequestBody deleteBillRequestBody)
    {
        return new ResponseEntity<>(gmailSenderService.billDetailsDelete(deleteBillRequestBody), HttpStatus.OK);
    }

    //Get All Bill
    @GetMapping("/custprod/allbills")
    public ResponseEntity<List<CustProdModel>> deleteBill()
    {
        return new ResponseEntity<>(gmailSenderService.getAllBills(), HttpStatus.OK);
    }



}











