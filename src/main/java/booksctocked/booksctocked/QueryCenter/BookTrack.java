package booksctocked.booksctocked.QueryCenter;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookTrack implements Serializable {
    private ArrayList<Book> books = new ArrayList<Book>();

    private static BookTrack instance = null;

    private ArrayList<String> genres = new ArrayList<String>();

    private BookTrack() {
    }

    public static BookTrack getInstance() {
        if (instance == null) {
            try {
                FileInputStream fis = new FileInputStream("target/CrystalPalace/BookCatalog.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                instance = (BookTrack) ois.readObject();
                fis.close();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                instance = new BookTrack();
            }
        }
        return instance;
    }

    public void saveInstance() {
        try {
            FileOutputStream fos = new FileOutputStream("target/CrystalPalace/BookCatalog.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBookList() {
        return books;
    }

    public boolean addBook(Book book) {
        Book bookIndex = isThere(book);

        if (bookIndex != null) {
            bookIndex.setCopyNum(bookIndex.getCopyNum() + book.getCopyNum());
            if(book.getRating() > 0 && book.getRating() <= 5) {
                bookIndex.setRating(book.getRating());
                return false;
            }
        } else {
            String bookGenre = book.getGenre();

            if (!genres.contains(bookGenre)) {
                genres.add(bookGenre.toUpperCase());
            }
            books.add(book);
            return true;
        }
        return false;
    }

    public boolean removeBook(long isbnToRemove){
        for(Book b : books){
            if(b.getIsbn() == isbnToRemove){
                books.remove(b);
                return true;
            }
        }
        return false;
    }

    public Book isThere(Book book){
        for(Book b : books){
            if(b.getIsbn() == book.getIsbn()){
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> searchGeneral(String search, int[] searchRange) {
        //searchRange [0][1] = price range, [2][3] = pubDate range
        ArrayList<Book> results = new ArrayList<Book>();

        boolean searchIsGenre = false;
        String upperCaseSearch = search.toUpperCase();
        for (String genre : genres) {
            if (genre.contains(upperCaseSearch)) {
                searchIsGenre = true;
                break;
            }
        }

        if (searchIsGenre) {
            results.addAll(searchByGenre(search, searchRange));
        } else {
            if (!search.matches(".*\\d+.*")) {
                results.addAll(searchByTitle(search, searchRange));
                results.addAll(searchByAuthor(search, searchRange));
            } else {
                if (search.matches("\\d+") || search.length() == 13 || search.length() == 10) {
                    results.addAll(searchByISBN(Long.parseLong(search), searchRange));
                    results.addAll(searchByTitle(search, searchRange)); //in case books have weird titles
                } else {
                    results.addAll(searchByTitle(search, searchRange));
                    results.addAll(searchByAuthor(search, searchRange));
                }
            }
        }
        return results;
    }

    public ArrayList<Book> searchByTitle(String title, int[] searchRange) {
        ArrayList<Book> results = new ArrayList<Book>();
        String[] inputTitleWords = title.trim().toUpperCase().split("\\s+");

        for (Book b : books) {
            String[] bookTitleWords = b.getTitle().trim().toUpperCase().split("\\s+");

            boolean titleMatch = false;
            for (String inputWord : inputTitleWords) {
                for (String bookWord : bookTitleWords) {
                    if (inputWord.equals(bookWord)) {
                        titleMatch = true;
                        break;
                    }
                }
                if (titleMatch) {
                    break;
                }
            }

            if (titleMatch &&
                    b.getRating() >= searchRange[2] && b.getRating() <= searchRange[3] &&
                    b.getPubDate() >= searchRange[0] && b.getPubDate() <= searchRange[1]) {
                results.add(b);
            }
        }
        return results;
    }

    public ArrayList<Book> searchByAuthor(String author, int[] searchRange) {
        ArrayList<Book> results = new ArrayList<Book>();
        String[] inputAuthorWords = author.trim().toUpperCase().split("\\s+");

        for (Book b : books) {
            String[] bookAuthorWords = b.getAuthor().trim().toUpperCase().split("\\s+");

            boolean authorMatch = false;
            for (String inputWord : inputAuthorWords) {
                for (String bookWord : bookAuthorWords) {
                    if (inputWord.equals(bookWord)) {
                        authorMatch = true;
                        break;
                    }
                }
                if (authorMatch) {
                    break;
                }
            }

            if (authorMatch &&
                    b.getRating() >= searchRange[2] && b.getRating() <= searchRange[3] &&
                    b.getPubDate() >= searchRange[0] && b.getPubDate() <= searchRange[1]) {
                results.add(b);
            }
        }
        return results;
    }

    public ArrayList<Book> searchByGenre(String genre, int[] searchRange) {
        ArrayList<Book> results = new ArrayList<Book>();
        String[] inputGenreWords = genre.trim().toUpperCase().split("\\s+");

        for (Book b : books) {
            String[] bookGenreWords = b.getGenre().trim().toUpperCase().split("\\s+");

            boolean genreMatch = false;
            for (String inputWord : inputGenreWords) {
                for (String bookWord : bookGenreWords) {
                    if (inputWord.equals(bookWord)) {
                        genreMatch = true;
                        break;
                    }
                }
                if (genreMatch) {
                    break;
                }
            }

            if (genreMatch &&
                    b.getRating() >= searchRange[2] && b.getRating() <= searchRange[3] &&
                    b.getPubDate() >= searchRange[0] && b.getPubDate() <= searchRange[1]) {
                results.add(b);
            }
        }
        return results;
    }

    public ArrayList<Book> searchByISBN(long isbn, int[] searchRange){
        ArrayList<Book> results = new ArrayList<Book>();
        for(Book b : books){
            if(b.getIsbn() == isbn &&
                    b.getRating() >= (float)searchRange[2] && b.getRating() <= (float)searchRange[3] &&
                    b.getPubDate() >= searchRange[0] && b.getPubDate() <= searchRange[1]){
                results.add(b);
            }
        }
        return results;
    }

    public Book[] sortBooks(int sortingBy, int rangeStart, int rangeEnd, int dateRatingNone){
        //if range start and range end are -1, no range, othetwise, range is inclusive
        Book[] bookArray = books.toArray(new Book[0]);
        switch(sortingBy){
            case 1:
                //sort by title
                sort(bookArray, 0, bookArray.length - 1, 1, rangeStart, rangeEnd, dateRatingNone);
                break;
            case 2:
                //sort by author
                sort(bookArray, 0, bookArray.length - 1, 2, rangeStart, rangeEnd, dateRatingNone);
                break;
            case 3:
                //sort by rating
                sort(bookArray, 0, bookArray.length - 1, 3, rangeStart, rangeEnd, dateRatingNone);
                break;
            case 4:
                //sort by pubDate
                sort(bookArray, 0, bookArray.length - 1, 4, rangeStart, rangeEnd, dateRatingNone);
                break;
            case 5:
                //sort by genre
                sort(bookArray, 0, bookArray.length - 1, 5, rangeStart, rangeEnd, dateRatingNone);
                break;
            case 6:
                //sort by copyNums
                sort(bookArray, 0, bookArray.length - 1, 6, rangeStart, rangeEnd, dateRatingNone);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting criteria");

        }

        return bookArray;
    }

    public Book getBookFromTitle(String name){
        for(Book b : books){
            if(b.getTitle().equals(name)){
                return b;
            }
        }
        return null;
    }


    private static void sort(Book[] books, int low, int high, int sortingCriteria, int rangeStart, int rangeEnd, int dateRatingNone) {
        if (low < high) {
            int pivotIndex = partition(books, low, high, sortingCriteria, rangeStart, rangeEnd, dateRatingNone);
            sort(books, low, pivotIndex - 1, sortingCriteria, rangeStart, rangeEnd, dateRatingNone);
            sort(books, pivotIndex + 1, high, sortingCriteria, rangeStart, rangeEnd, dateRatingNone);
        }
    }

    private static int partition(Book[] books, int low, int high, int sortingCriteria, int rangeStart, int rangeEnd, int dateRatingNone) {
        Comparable pivot = getSortingValue(books[high], sortingCriteria, rangeStart, rangeEnd, dateRatingNone);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if(getSortingValue(books[j], sortingCriteria, rangeStart, rangeEnd, dateRatingNone).equals(-1)){
                continue;
            }
            Comparable currentValue = getSortingValue(books[j], sortingCriteria, rangeStart, rangeEnd, dateRatingNone);
            if (currentValue.compareTo(pivot) <= 0) {
                i++;
                swap(books, i, j);
            }
        }

        swap(books, i + 1, high);
        return i + 1;
    }

    private static Comparable getSortingValue(Book book, int sortingCriteria, int rangeStart, int rangeEnd, int dateRatingNone) {
        switch (sortingCriteria) {
            case 1:
                return book.getTitle();
            case 2:
                String[] authorNameParts = book.getAuthor().split(" ");
                return authorNameParts[authorNameParts.length - 1];
            case 3:
                if(dateRatingNone == 2 && (rangeStart != -1 && rangeEnd != -1)) {
                    if(book.getRating() >= rangeStart && book.getRating() <= rangeEnd) {
                        return book.getRating();
                    } else {
                        return -1;
                    }
                }
                return book.getRating();
            case 4:
                if(dateRatingNone == 1 && (rangeStart != -1 && rangeEnd != -1)) {
                    if(book.getPubDate() >= rangeStart && book.getPubDate() <= rangeEnd) {
                        int date = book.getPubDate();
                        int dateLength = String.valueOf(date).length();
                        if (dateLength == 4) {
                            return date * 10000;
                        } else if (dateLength == 6) {
                            return date * 100;
                        } else if (dateLength == 8) {
                            return date;
                        } else {
                            throw new IllegalArgumentException("Invalid date length");
                        }
                    } else {
                        return -1;
                    }
                }
                return book.getPubDate();
            case 5:
                return book.getGenre();
            case 6:
                return book.getCopyNum();
            default:
                throw new IllegalArgumentException("Invalid sorting criteria");
        }
    }

    private static void swap(Book[] books, int i, int j) {
        Book temp = books[i];
        books[i] = books[j];
        books[j] = temp;
    }

    public static void saveBooksToCSV(BookTrack bookTrack) {
        String filePath = "target/CrystalPalace/books_csv.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Book book : bookTrack.getBookList()) {
                writer.append(book.getTitle())
                        .append(",")
                        .append(book.getAuthor())
                        .append("\n");
            }
        } catch (IOException e) {
        }
    }
}
