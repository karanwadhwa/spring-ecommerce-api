package edu.neu.karanwadhwa.springecommerceapi.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import edu.neu.karanwadhwa.springecommerceapi.model.Address;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.OrderItem;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Map;

public class InvoiceGenerator extends AbstractPdfView {
    private final Order order;
    private final User customer;
    private final Address address;

    public InvoiceGenerator(Order order) {
        this.order = order;
        this.customer = order.getUser();
        this.address = order.getAddress();
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Font titleFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
        Font subtileBold = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
        Font subtileFont = new Font(Font.TIMES_ROMAN, 16);
        Font textBold = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
        Font textFont = new Font(Font.TIMES_ROMAN, 14);
        Element title = new Chunk("INVOICE", titleFont).setUnderline(1.0F, -1.8F);
        document.add(title);

        Paragraph customerDetails = new Paragraph();
        customerDetails.setSpacingBefore(20.0F);
        customerDetails.add(new Chunk("Customer", subtileBold).setUnderline(0.5F, -1.5F));
        customerDetails.add(new Chunk("\nName: ", textBold));
        customerDetails.add(new Chunk(customer.getFname()+ " "+ customer.getLname(), textFont));
        customerDetails.add(new Chunk("\nEmail: ", textBold));
        customerDetails.add(new Chunk(customer.getEmail(), textFont));
        customerDetails.add(new Chunk("\nAddress: ", textBold));
        customerDetails.add(new Chunk(address.getStreet()+", "+address.getApt()+", "+address.getCity()+", "+address.getCountry()+" - "+address.getPin(), textFont));
        document.add(customerDetails);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        Paragraph orderInfo = new Paragraph();
        orderInfo.setSpacingAfter(20.0F);
        orderInfo.add(new Chunk("\nOrder ID: ", textBold));
        orderInfo.add(new Chunk(String.valueOf(order.getOrderId()), textFont));
        orderInfo.add(new Chunk("\nStatus: ", textBold));
        orderInfo.add(new Chunk(order.getStatus(), textFont));
        orderInfo.add(new Chunk("\nPurchase Date: ",textBold));
        orderInfo.add(new Chunk( simpleDateFormat.format(order.getCreatedOn()), textFont));
        document.add(orderInfo);

        float [] columnWidths = {12, 35, 10, 15, 15, 13};
        Table table = new Table(6);
        table.setPadding(5);
        table.setWidths(columnWidths);
        table.setWidth(100);
        table.addCell("Prod. id");
        table.addCell("Name");
        table.addCell("Seller");
        table.addCell("Price");
        table.addCell("Quantity");
        table.addCell("Amount");
        table.setAlignment("Center");
        table.endHeaders();
        for(OrderItem item : order.getItems()){
            table.addCell(String.valueOf(item.getProductId()));
            table.addCell(item.getName());
            table.addCell(String.valueOf(item.getSellerId()));
            table.addCell(String.valueOf(item.getPrice()));
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getTotalPrice()));
        }
        document.add(table);

        Paragraph total = new Paragraph();
        total.setSpacingBefore(20F);
        total.add(new Chunk("Order Total: ",subtileBold));
        total.add(new Chunk(String.valueOf(order.getOrderTotal()), subtileFont));
        document.add(total);

        document.close();
    }
}
