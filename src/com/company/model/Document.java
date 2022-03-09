package com.company.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    private List<String> number;
    private String phone;
    private String mail;

    public Document(File file) {
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((str = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\d{4}[\\-][a-zA-Zа-яА-Я]{3}[\\-]\\d{4}[\\-][a-zA-Zа-яА-Я]{3}[\\-]\\d[a-zA-Zа-яА-Я]\\d[a-zA-Zа-яА-Я]");
                number = new ArrayList<>();
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    number.add(matcher.group());
                }
                Pattern pattern2 = Pattern.compile("\\+\\(\\d{2}\\)\\d{7}");
                Matcher matcher2 = pattern2.matcher(str);
                while (matcher2.find()){
                    phone = str.substring(matcher2.start(), matcher2.end());
                }
                Pattern pattern3 = Pattern.compile("\\w+@\\w+\\.\\w+");
                Matcher matcher3 = pattern3.matcher(str);
                while (matcher3.find()){
                    mail = str.substring(matcher3.start(), matcher3.end());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getNumber() {
        return number;
    }

    public void setNumber(List<String> number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(number, document.number) && Objects.equals(phone, document.phone) && Objects.equals(mail, document.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, phone, mail);
    }

    @Override
    public String toString() {
        return "Document{" +
                "number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
