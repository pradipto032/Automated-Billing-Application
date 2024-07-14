package com.craftMarine.emailSender.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.craftMarine.emailSender.Model.CustProdModel;
import com.craftMarine.emailSender.Model.CustomerModel;
import com.craftMarine.emailSender.Model.ProductModel;
import com.craftMarine.emailSender.Repository.CustomerDetailsRepository;
import com.craftMarine.emailSender.Repository.CustomerProductRepository;
import com.craftMarine.emailSender.RequestBody.CustProdRequestBody;
import com.craftMarine.emailSender.RequestBody.DeleteBillRequestBody;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

@Service
public class GmailSenderService {

        @Autowired
        private CustomerProductRepository customerProductRepository;

        @Autowired
        private CustomerDetailsRepository customerDetailsRepository;

        @Autowired
        private JavaMailSender javaMailSender;

        ZoneId indiaZoneId = ZoneId.of("Asia/Kolkata");
        public String mailSender(CustProdRequestBody custProdRequestBody, String pdfFilePath, HttpServletResponse response) throws MessagingException, IOException {
            //Saving in the Customer Product DB
            CustomerModel customerModel = customerDetailsRepository.getCustomer(custProdRequestBody.getCustDetails().getCustMail());
            if(customerModel == null)
            {
                return "Please register the customer details first";
            }
            else {
                CustProdModel custProdModel = new CustProdModel();
                custProdModel.setInvoiceId(customerProductRepository.findAll().size() + 1);
                custProdModel.setCustName(custProdRequestBody.getCustDetails().getCustName());
                custProdModel.setCustMail(custProdRequestBody.getCustDetails().getCustMail());
                custProdModel.setProductDetails(custProdRequestBody.getProductDetails());
                custProdModel.setOrderDate(String.valueOf(LocalDate.now(indiaZoneId)));

                customerProductRepository.save(custProdModel);
                //Generating the PDF
                boolean resp = generatePdf(custProdRequestBody, response, pdfFilePath);

                //Sending Mail with attachment
                if (resp) {
                    MimeMessage message = javaMailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setTo(custProdModel.getCustMail());
                    String subject = "Hi, " + custProdModel.getCustName() + ". Thanks for ordering.";
                    helper.setSubject(subject);
                    String body = "Please find PDF attached below with your order details";
                    helper.setText(body);
                    helper.addAttachment(custProdModel.getInvoiceId() + "_" + custProdModel.getCustName(), new File(pdfFilePath));
                    javaMailSender.send(message);

                    return "Email sent successfully";
                } else {
                    return "Email not sent properly";
                }
            }
        }

        public boolean generatePdf(CustProdRequestBody custProdRequestBody, HttpServletResponse response, String pdfFilePath) throws IOException, DocumentException {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));


            // Creating the Object of Document
            // Getting instance of PdfWriter
            PdfWriter.getInstance(document, response.getOutputStream());
            // Opening the created document to change it
            document.open();
            // Creating font
            // Setting font style and size
            //HEADING
            Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_BOLD);
            fontTiltle.setSize(20);
            // Creating paragraph
            Paragraph paragraph1 = new Paragraph("Craft Marine", fontTiltle);
            // Aligning the paragraph in the document
            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

            //TAG LINE
            Font fontTiltle1 = FontFactory.getFont(FontFactory.TIMES_ITALIC);
            fontTiltle.setSize(15);
            Paragraph paragraph2 = new Paragraph("THE NEW STYLE FOR YOUR SWEET HOME", fontTiltle1);
            paragraph2.setAlignment(Paragraph.ALIGN_CENTER);


            // Creating font
            // Setting font style and size
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            font.setColor(CMYKColor.BLACK);

            //Starting the customer details table
            Paragraph paragraph7 = new Paragraph("Your details:", font);

            // Creating a table of the 2 columns for Customer Datas
            PdfPTable table = new PdfPTable(3);
            // Setting width of the table, its columns and spacing
            table.setWidthPercentage(100);
            table.setWidths(new int[]{5, 5, 5});
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            // Create Table Cells for the table header
            PdfPCell cell = new PdfPCell();
            // Setting the background color and padding of the table cell
            cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
            cell.setPadding(5);
            // Adding headings in the created table cell or  header
            // Adding Cell to table
            cell.setPhrase(new Phrase("Customer Details", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Shipping Address", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Billing Address", font));
            table.addCell(cell);
            // Adding Customer Details
            String name = custProdRequestBody.getCustDetails().getCustName();
            String phone = custProdRequestBody.getCustDetails().getCustPhone();
            String mailId = custProdRequestBody.getCustDetails().getCustMail();
            Paragraph paragraph3 = new Paragraph(name + "\n" + phone + "\n" + mailId, font);
            table.addCell(paragraph3);
            // Adding Customer shipping details
            String shipAddress = custProdRequestBody.getCustDetails().getCustShippingAddress().getAddress();
            String shipState = custProdRequestBody.getCustDetails().getCustShippingAddress().getState();
            String shipPincode = custProdRequestBody.getCustDetails().getCustShippingAddress().getPinCode();
            Paragraph paragraph4 = new Paragraph(shipAddress + "\n" + shipState + "\n" + shipPincode, font);
            table.addCell(paragraph4);
            // Adding Customer billing details
            String billAddress = custProdRequestBody.getCustDetails().getCustBillingAddress().getAddress();
            String billState = custProdRequestBody.getCustDetails().getCustBillingAddress().getState();
            String billPincode = custProdRequestBody.getCustDetails().getCustBillingAddress().getPinCode();
            Paragraph paragraph5 = new Paragraph(billAddress + "\n" + billState + "\n" + billPincode, font);
            table.addCell(paragraph5);


            //PRODUCT TABLE
            Paragraph paragraph6 = new Paragraph("Below is the product details :", font);

            //Creating the billing table
            PdfPTable table1 = new PdfPTable(4);
            // Setting width of the table, its columns and spacing
            table1.setWidthPercentage(100);
            table1.setWidths(new int[]{10, 5, 5, 5});
            table1.setSpacingBefore(5);
            table1.setSpacingAfter(10);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBackgroundColor(CMYKColor.LIGHT_GRAY);
            cell1.setPadding(5);
            cell1.setPhrase(new Phrase("Product Details", font));
            table1.addCell(cell1);
            cell1.setPhrase(new Phrase("Qnt", font));
            table1.addCell(cell1);
            cell1.setPhrase(new Phrase("Cost", font));
            table1.addCell(cell1);
            cell1.setPhrase(new Phrase("Total Cost", font));
            table1.addCell(cell1);
            int grossTotal = 0;
            for(ProductModel productModel : custProdRequestBody.getProductDetails()) {
                //Product Name
                String productName = productModel.getProductName();
                table1.addCell(productName);
                //Quantity
                int quantity = productModel.getQuantity();
                table1.addCell(String.valueOf(quantity));
                //Each quantity price
                int productPrice = productModel.getProductPrice();
                table1.addCell(String.valueOf(productPrice));
                //Total price = qnt * productprice
                int totalPrice = productModel.getTotalPrice();
                table1.addCell(String.valueOf(totalPrice));

                grossTotal = grossTotal + totalPrice;
            }

            int advancePaid = custProdRequestBody.getAdvancePaid();
            if(advancePaid > grossTotal)
            {
                return false;
            }
            int remainingBalance = grossTotal - advancePaid;

            Paragraph gro = new Paragraph("Gross Total = Rs." + grossTotal, font);
            Paragraph adv = new Paragraph("Advance Paid = Rs." + advancePaid , font);
            Paragraph rem = new Paragraph("Remaining Balance = Rs." + remainingBalance, font);

            Paragraph date = new Paragraph("Order Date is : "+String.valueOf(LocalDate.now(indiaZoneId)));



            // Adding the created table to the document
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph7);
            document.add(table);
            document.add(paragraph6);
            document.add(table1);
            document.add(gro);
            document.add(adv);
            document.add(rem);
            document.add(date);
            // Closing the document
            document.close();

            return true;
        }
    }

        //Service to delete bills
        public String billDetailsDelete(DeleteBillRequestBody deleteBillRequestBody)
        {
            int inv = deleteBillRequestBody.getInvoiceId();
            String mail = deleteBillRequestBody.getCustMail();
            CustProdModel custProdModel = customerProductRepository.getCustomerBill(inv, mail);
            if(custProdModel == null)
            {
                return "Bill not present";
            }
            customerProductRepository.deleteCustomerBill(inv, mail);
            return "Bill deleted successfully";
        }

        public List<CustProdModel> getAllBills()
        {
            return customerProductRepository.findAll(Sort.by(Sort.Direction.ASC, "invoiceId"));
        }
}


