package com.example.demo.service.impl;

import com.example.demo.config.GlobalVariable;
import com.example.demo.entity.Post;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FileService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileServiceImpl implements FileService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isCsvFormat(MultipartFile file) {
        return GlobalVariable.CSV_TYPE.equals(file.getContentType());
    }

    @Override
    public void processAndSaveData(MultipartFile file) {
        try {
            List<User> input = readDataFromCsvFile(file.getInputStream());
            if (input != null) {
                userRepository.saveAll(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportUserToCsv(Writer writer) {
        int userLimit = 5;
        long count = userRepository.count();
        int sumOffset = (int) (count / userLimit);
        String gender = "Men";
        Pageable pageable;
        Page<User> users;
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Full Name", "Email", "Phone",
                    "Username", "Gender", "Status", "Role", "Created Date");

            for(int i = 0; i <= sumOffset; i++){
                pageable = PageRequest.of(i,userLimit);
                users = userRepository.findAll(pageable);

                for (User user : users) {
                    if (user.getGender().equals("0")) {
                        gender = "Women";
                    }
                    csvPrinter.printRecord(user.getId(),
                            user.getFullName(),
                            user.getEmail(),
                            user.getPhone(),
                            user.getUsername(),
                            gender,
                            user.getStatus(),
                            user.getUserRole().getName(),
                            user.getCreatedDate());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exportUserToPDF(HttpServletResponse response) throws DocumentException,IOException {
        // Create the Object of Document
        Document document = new Document(PageSize.A4);
        // get the document and write the response to output stream
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Add Font
        Font fontTitle = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
        fontTitle.setSize(18);
        // Create Object of Paragraph
        Paragraph paragraph = new Paragraph("List of Users", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        // Add to the document
        document.add(paragraph);
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1, 3, 3, 3, 3, 2.5f, 2, 2, 3 });
        table.setSpacingBefore(20);

        writeUserTableHeader(table);
        writeTableUserData(table);
        // Add table to document
        document.add(table);
        document.close();
    }

    private void writeUserTableHeader(PdfPTable table) {
        // Create Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.getColor("#2196F3"));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // Add Font
        Font headerFont = new Font(Font.TIMES_ROMAN, 13, Font.BOLD, Color.WHITE);
        cell.setPhrase(new Phrase("ID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Full Name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Phone", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Username", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gender", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Status", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Role", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Created Date", headerFont));
        table.addCell(cell);
    }

    private void writeTableUserData(PdfPTable table) {
        int userLimit = 5;
        long count = userRepository.count();
        int sumOffset = (int) (count / userLimit);
        String gender = "Men";
        Pageable pageable;
        Page<User> users;
        Font font = new Font(Font.TIMES_ROMAN, 13);
        for(int i = 0; i <= sumOffset; i++){
            pageable = PageRequest.of(i,userLimit);
            users = userRepository.findAll(pageable);

            for (User user : users) {
                if (user.getGender().equals("0")) {
                    gender = "Women";
                }

                table.addCell(new Phrase(String.valueOf(user.getId()),font));
                table.addCell(new Phrase(user.getFullName(),font));
                table.addCell(new Phrase(user.getEmail(),font));
                table.addCell(new Phrase(user.getPhone(),font));
                table.addCell(new Phrase(user.getUsername(),font));
                table.addCell(new Phrase(gender,font));
                table.addCell(new Phrase(user.getStatus(),font));
                table.addCell(new Phrase(user.getUserRole().getName(),font));
                table.addCell(new Phrase(String.valueOf(user.getCreatedDate()),font));
            }
        }

    }

    private List<User> readDataFromCsvFile(InputStream inputStream){
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Role roleUser = roleRepository.findById(Long.parseLong("2"))
                    .orElseThrow(() -> new UsernameNotFoundException("Not found")); //Lấy ROLE_USER

            String defaultAvatar = "https://firebasestorage.googleapis.com/v0/b/cnpm-30771.appspot.com/o/no-user.png?alt=media&token=517e08ab-6aa4-42eb-9547-b1b10f17caf0";
            String defaultPassword = passwordEncoder.encode("12345678");

            List<User> users = new ArrayList<>();
            List<CSVRecord> records = csvParser.getRecords();
            for(CSVRecord r : records){
                User user = new User();
                user.setFullName(r.get("fullname"));
                user.setEmail(r.get("email"));
                user.setPhone(r.get("phone"));
                user.setUsername(r.get("username"));

                user.setAvatar(defaultAvatar);
                user.setPassword(defaultPassword);
                user.setUserRole(roleUser);
                user.setGender("3");

                users.add(user);
            }

            return users;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
