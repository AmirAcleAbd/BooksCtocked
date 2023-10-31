package booksctocked.booksctocked.QueryCenter;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String genre;
    private long isbn;
    private int pubDate;
    private int copyNum;
    private int rating;
    private String description;

    public Book(String title, String author, String genre, long isbn, int pubDate, int rating, int copyNum) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.pubDate = pubDate;
        this.copyNum = copyNum;
        this.rating = rating;
    }

    public Book(String title, String author, String genre, long isbn, String pubDate, int rating, int copyNum) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.pubDate = convertDate(pubDate);
        this.copyNum = copyNum;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public long getIsbn() {
        return isbn;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatingString(){
        return Integer.toString(rating);
    }

    public int getPubDate() {
        return pubDate;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public int setCopyNum(int copyNum) {
        this.copyNum = copyNum;
        return copyNum;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPubDateString(){
        return convertDate(pubDate);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nISBN: " + isbn + "\nRating: " + rating + "\nPublication Date: " + pubDate + "\nNumber of Copies: " + copyNum;
    }

    public boolean isAvailable() {
        return copyNum > 0;
    }

    public static int convertDate(String date) {
        String[] parts;
        if (date.contains("-")) {
            parts = date.split("-");
        } else if (date.contains("/")) {
            parts = date.split("/");
        } else {
            throw new IllegalArgumentException("Invalid date format prolly");
        }
        int year = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        int convertedDate = year * 10000 + month * 100 + day;
        return convertedDate;
    }

    public static String convertDate(int date) {
        String convertedDate = Integer.toString(date);

        String year;
        String month = "01";
        String day = "01";

        switch (convertedDate.length()) {
            case 4: // yyyy
                year = convertedDate;
                break;
            case 6: // yyyymm
                year = convertedDate.substring(0, 4);
                month = convertedDate.substring(4, 6);
                break;
            case 8: // yyyymmdd
                year = convertedDate.substring(0, 4);
                month = convertedDate.substring(4, 6);
                day = convertedDate.substring(6, 8);
                break;
            default:
                return "01/01/2000";
        }
        return month + "/" + day + "/" + year;
    }

    public String getNameNoSpaces() {
        return title.replaceAll("\\s+","");
    }
}