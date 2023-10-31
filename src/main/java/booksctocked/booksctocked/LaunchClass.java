package booksctocked.booksctocked;

import booksctocked.booksctocked.CustomUtil.MultiDatePicker;
import booksctocked.booksctocked.CustomUtil.SearchBarComboBox;
import booksctocked.booksctocked.QueryCenter.Book;
import booksctocked.booksctocked.QueryCenter.BookTrack;
import booksctocked.booksctocked.QueryCenter.User;
import booksctocked.booksctocked.QueryCenter.UserTrack;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LaunchClass extends Application {

    public static Scene sceneMain = null;
    public static User currentUser;
    private VBox rootAdmin = null;
    private VBox rootBookPage = null;
    private VBox rootHome = null;
    private VBox rootAcctPage = null;
    private BorderPane threePane;
    private boolean firstRun = true;
    private boolean firstRunCopyNum = true;
    private boolean inAccountPage = false;
    private Book[] sortedList = null;
    private Book[] sortedCopyNumList = null;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage HomeStage) throws IOException {
        UserTrack.getInstance();
        BookTrack.getInstance();
        //addBooks(BookTrack.getInstance());
        BookTrack.saveBooksToCSV(BookTrack.getInstance());
        sceneMain = new Scene(homePage(), 1200, 800);
        User user = new User("amir", "pass");
        User me = UserTrack.getInstance().getUser(user);
        me.setAdmin(true);
        currentUser = me;
        //me.getBorrowed().clear();

        HomeStage.setScene(sceneMain);
        HomeStage.setTitle("Books Ctocked");
        HomeStage.setResizable(true);
        HomeStage.show();

        HomeStage.setOnCloseRequest((WindowEvent event) -> {
            BookTrack.getInstance().saveInstance();
            UserTrack.getInstance().saveInstance();
        });
    }

    public static void addUsers(UserTrack userTrack) {
        User user1 = new User("Apple", "Kiwi123");
        User user2 = new User("Banana", "Peach456");
        User user3 = new User("Cherry", "Mango789");
        User user4 = new User("Date", "Lemon012");
        User user5 = new User("Elderberry", "Strawberry345");
        User user6 = new User("Fig", "Blueberry678");
        User user7 = new User("Grape", "Raspberry901");
        User user8 = new User("Honeydew", "Blackberry234");
        User user9 = new User("IcedTea", "GreenTea567");
        User user10 = new User("Java", "Python890");

        userTrack.addUser(user1);
        userTrack.addUser(user2);
        userTrack.addUser(user3);
        userTrack.addUser(user4);
        userTrack.addUser(user5);
        userTrack.addUser(user6);
        userTrack.addUser(user7);
        userTrack.addUser(user8);
        userTrack.addUser(user9);
        userTrack.addUser(user10);
    }
    public static void addBooks(BookTrack bookTrack){
        Book book1 = new Book("The Hunger Games", "Suzanne Collins", "Scholastic Press", 1293209223413L, 20061009, 5, 12);
        Book book19 = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 9780061120084L, 19600711, 5, 8);
        Book book2 = new Book("1984", "George Orwell", "Science Fiction", 9780451524935L, 19490608, 4, 10);
        Book book3 = new Book("Pride and Prejudice", "Jane Austen", "Classic", 9780141439518L, 18130128, 5, 6);
        Book book4 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 9780743273565L, 19250410, 4, 15);
        Book book5 = new Book("To the Lighthouse", "Virginia Woolf", "Modernist", 9780156030471L, 19270505, 5, 8);
        Book book6 = new Book("Moby-Dick", "Herman Melville", "Adventure", 9780553213119L, 18511114, 4, 10);
        Book book7 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 9780316769488L, 19510101, 4, 12);
        Book book8 = new Book("Brave New World", "Aldous Huxley", "Science Fiction", 9780060850524L, 19320101, 5, 6);
        Book book9 = new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 9780618260300L, 19370921, 5, 10);
        Book book10 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 9780544003415L, 19540829, 5, 8);
        Book book11 = new Book("Jane Eyre", "Charlotte Brontë", "Gothic", 9780141441146L, 18471016, 5, 12);
        Book book12 = new Book("Frankenstein", "Mary Shelley", "Gothic", 9780486282114L, 18180101, 4, 10);
        Book book13 = new Book("The Odyssey", "Homer", "Epic Poetry", 9780143039952L, -800, 5, 15);
        Book book14 = new Book("The Alchemist", "Paulo Coelho", "Fiction", 9780062315007L, 19880801, 4, 20);
        Book book15 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", 9780140449136L, 18660101, 5, 8);
        Book book16 = new Book("The Chronicles of Narnia", "C.S. Lewis", "Fantasy", 9780064471190L, 19500901, 5, 6);
        Book book17 = new Book("The Kite Runner", "Khaled Hosseini", "Historical Fiction", 9781594631931L, 20030529, 4, 10);
        Book book18 = new Book("The Book Thief", "Markus Zusak", "Historical Fiction", 9780375842207L, 20050314, 5, 12);
        Book book20 = new Book("The Girl on the Train", "Paula Hawkins", "Mystery", 9781594634024L, 20150113, 4, 15);
        Book book21 = new Book("The Da Vinci Code", "Dan Brown", "Thriller", 9780307474278L, 20030318, 5, 10);
        Book book22 = new Book("The Fault in Our Stars", "John Green", "Young Adult", 9780142424179L, 20120110, 4, 12);
        Book book23 = new Book("The Help", "Kathryn Stockett", "Historical Fiction", 9780425232200L, 20090210, 5, 8);
        Book book24 = new Book("The Harry Potter Series", "J.K. Rowling", "Fantasy", 9780545010221L, 19970626, 5, 20);
        Book book25 = new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "Crime Fiction", 9780307949486L, 20050916, 4, 10);
        Book book26 = new Book("The Picture of Dorian Gray", "Oscar Wilde", "Gothic Fiction", 9781503330575L, 18900101, 5, 15);
        Book book27 = new Book("The Road", "Cormac McCarthy", "Post-Apocalyptic", 9780307387899L, 20060926, 4, 12);
        Book book28 = new Book("The Shining", "Stephen King", "Horror", 9780307743657L, 19770128, 5, 8);
        Book book29 = new Book("The Adventures of Tom Sawyer", "Mark Twain", "Adventure", 9780486400778L, 18760101, 5, 6);
        Book book30 = new Book("The Secret Life of Bees", "Sue Monk Kidd", "Historical Fiction", 9780143114550L, 20020128, 4, 10);
        Book book31 = new Book("The Sun Also Rises", "Ernest Hemingway", "Modernist", 9780743297332L, 19260101, 5, 12);
        Book book32 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 9780316769488L, 19510101, 4, 8);
        Book book33 = new Book("The Color Purple", "Alice Walker", "Epistolary Fiction", 9780156031829L, 19820101, 5, 10);
        Book book34 = new Book("The Girl with the Pearl Earring", "Tracy Chevalier", "Historical Fiction", 9780452282155L, 19990901, 4, 6);
        Book book35 = new Book("The Adventures of Huckleberry Finn", "Mark Twain", "Adventure", 9780486280615L, 18841210, 4, 12);
        Book book36 = new Book("Lord of the Flies", "William Golding", "Psychological Fiction", 9780571273577L, 19540717, 5, 8);
        Book book37 = new Book("The Secret Garden", "Frances Hodgson Burnett", "Children's Literature", 9780142437056L, 19110101, 5, 10);
        Book book38 = new Book("Wuthering Heights", "Emily Brontë", "Gothic", 9780141439556L, 18471219, 4, 15);
        Book book39 = new Book("The Handmaid's Tale", "Margaret Atwood", "Dystopian", 9780385490818L, 19850901, 5, 8);
        Book book40 = new Book("Gone with the Wind", "Margaret Mitchell", "Historical Fiction", 9781451635621L, 19360310, 5, 6);
        Book book41 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 9780618640157L, 19540729, 5, 10);
        Book book42 = new Book("One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism", 9780060883287L, 19670630, 5, 8);
        Book book43 = new Book("The Scarlet Letter", "Nathaniel Hawthorne", "Historical Fiction", 9780142437261L, 18500701, 4, 12);
        Book book44 = new Book("The Count of Monte Cristo", "Alexandre Dumas", "Adventure", 9780140449266L, 18440101, 5, 10);
        Book book45 = new Book("The Picture of Dorian Gray", "Oscar Wilde", "Gothic Fiction", 9780486278070L, 18900101, 4, 15);
        Book book46 = new Book("The Stranger", "Albert Camus", "Philosophical Fiction", 9780679720201L, 19420619, 5, 12);
        Book book47 = new Book("The Grapes of Wrath", "John Steinbeck", "Historical Fiction", 9780143039433L, 19390414, 5, 8);
        Book book48 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky", "Philosophical Fiction", 9780374528379L, 18800101, 4, 20);
        Book book49 = new Book("The Sound and the Fury", "William Faulkner", "Modernist", 9780679732242L, 19290406, 5, 10);
        Book book50 = new Book("Les Misérables", "Victor Hugo", "Historical Fiction", 9780140445923L, 18620101, 5, 12);
        Book book51 = new Book("Percy Jackson & The Lightning Thief", "Rick Riordan", "Fantasy", 9781423106977L, 20050101, 1, 15);
        Book book52 = new Book("Percy Jackson & The Sea of Monsters", "Rick Riordan", "Fantasy", 9781423103341L, 20060321, 2, 14);
        Book book53 = new Book("Percy Jackson & The Titan's Curse", "Rick Riordan", "Fantasy", 9781423101453L, 20070501, 3, 14);
        Book book54 = new Book("Percy Jackson & The Battle of the Labyrinth", "Rick Riordan", "Fantasy", 9781423101491L, 20080506, 3, 15);
        Book book55 = new Book("Percy Jackson & The Last Olympian", "Rick Riordan", "Fantasy", 9781423101477L, 20090505, 2, 16);


        Book book56 = new Book("Twilight", "Stephenie Meyer", "Fantasy", 9780316160179L, 20051005, 1, 0);
        book56.setDescription("A captivating love story between a teenage girl and a vampire that explores the complexities of forbidden romance.");

        Book book57 = new Book("New Moon", "Stephenie Meyer", "Fantasy", 9780316160193L, 20060906, 4, 0);
        book57.setDescription("As Bella Swan delves deeper into the supernatural world, she faces heartbreak and discovers secrets that could change her life forever.");

        Book book58 = new Book("Eclipse", "Stephenie Meyer", "Fantasy", 9780316160209L, 20070807, 3, 0);
        book58.setDescription("In the midst of a dangerous love triangle, Bella Swan must make a crucial choice between her vampire and werewolf suitors.");

        Book book59 = new Book("Breaking Dawn", "Stephenie Meyer", "Fantasy", 9780316067928L, 20080802, 4, 0);
        book59.setDescription("The epic conclusion to the Twilight series as Bella's transformation into a vampire unleashes a chain of events that test her strength and loyalty.");

        Book book60 = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "Computer Science", 9780132350884L, 20080811, 2, 0);
        book60.setDescription("This book offers practical advice and guidelines for writing clean, maintainable, and high-quality code.");

        Book book61 = new Book("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", "Computer Science", 9780262033848L, 20090731, 4, 0);
        book61.setDescription("Considered a definitive guide to algorithms, this book covers a wide range of algorithms and their analysis, making it a valuable resource for computer science students and professionals.");

        Book book62 = new Book("Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell", "Computer Science", 9780984782857L, 20110701, 3, 0);
        book62.setDescription("A comprehensive guide for anyone preparing for coding interviews, this book provides coding questions and detailed solutions to help you ace your interviews.");

        Book book63 = new Book("The Pragmatic Programmer: Your Journey to Mastery", "Andrew Hunt, David Thomas", "Computer Science", 9780135957059L, 20190930, 1, 0);
        book63.setDescription("Filled with practical advice and real-world examples, this book teaches you the art of software craftsmanship and guides you on your journey to becoming a better programmer.");

        Book book64 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Computer Science", 9780201633610L, 19941031, 4, 0);
        book64.setDescription("This classic book introduces the concept of design patterns and provides examples and reusable solutions to common design problems in object-oriented software development.");



        book1.setDescription("In a dystopian future, a young girl named Katniss Everdeen volunteers for the brutal Hunger Games to save her sister.");
        book19.setDescription("Set in the racially divided town of Maycomb, Alabama, a young girl named Scout learns about morality and justice when her father defends a black man accused of rape.");
        book2.setDescription("In a totalitarian society, Winston Smith rebels against the oppressive regime in a world where Big Brother is always watching.");
        book3.setDescription("A witty and captivating love story unfolds as Elizabeth Bennet navigates societal expectations and her own prejudices in Regency-era England.");
        book4.setDescription("Jay Gatsby's lavish lifestyle and his pursuit of the elusive Daisy Buchanan serve as a critique of the American Dream in 1920s America.");
        book5.setDescription("Virginia Woolf takes readers on an introspective journey through the thoughts and experiences of her characters as they navigate life's complexities.");
        book6.setDescription("Captain Ahab's relentless pursuit of the great white whale, Moby-Dick, explores themes of obsession and the human condition.");
        book7.setDescription("Holden Caulfield's introspective narrative captures the disillusionment and teenage angst of a young man navigating the challenges of adulthood.");
        book8.setDescription("Aldous Huxley presents a dystopian society where individuality is suppressed and happiness is controlled through advanced technology.");
        book9.setDescription("Bilbo Baggins embarks on an unexpected adventure filled with dragons, dwarves, and a powerful ring in J.R.R. Tolkien's beloved fantasy tale.");
        book10.setDescription("Frodo Baggins embarks on a perilous journey to destroy the One Ring and save Middle-earth from the dark forces of Sauron in this epic fantasy series.");
        book11.setDescription("Jane Eyre's journey from orphan to independent woman is a timeless exploration of love, identity, and social class.");
        book12.setDescription("Mary Shelley's Gothic masterpiece tells the story of Victor Frankenstein and his creation, raising questions about the limits of science and the consequences of playing god.");
        book13.setDescription("Homer's epic poem follows the Greek hero Odysseus as he embarks on a treacherous journey home after the Trojan War.");
        book14.setDescription("Paulo Coelho's allegorical novel follows a young Andalusian shepherd named Santiago as he seeks his personal legend and discovers the true meaning of life.");
        book15.setDescription("Raskolnikov, a troubled former student, grapples with guilt and the psychological effects of his crime in Dostoevsky's gripping exploration of morality and redemption.");
        book16.setDescription("Step into the magical world of Narnia where talking animals, mythical creatures, and epic adventures await a group of children.");
        book17.setDescription("Set in Afghanistan, this powerful novel explores the complex relationship between two friends against the backdrop of a changing society.");
        book18.setDescription("Narrated by Death, this haunting tale follows a young girl who finds solace in books during World War II.");
        book20.setDescription("A gripping psychological thriller that takes readers on a suspenseful ride through the eyes of a troubled woman who becomes entangled in a mysterious disappearance.");
        book21.setDescription("Uncover the secrets and puzzles hidden within the works of Leonardo da Vinci in this thrilling race against time.");
        book22.setDescription("John Green's heartfelt story follows the love and struggles of two teenagers as they navigate life, illness, and the power of friendship.");
        book23.setDescription("Set in Mississippi during the Civil Rights era, this novel explores the lives of African American maids and the courage it takes to challenge societal norms.");
        book24.setDescription("Join Harry Potter on his magical journey through the wizarding world as he battles dark forces and discovers his destiny.");
        book25.setDescription("A gripping crime novel featuring a complex protagonist and a captivating mystery that intertwines secrets, intrigue, and danger.");
        book26.setDescription("Oscar Wilde's provocative novel delves into the depths of human desires, beauty, and the consequences of a life governed by hedonism.");
        book27.setDescription("In a post-apocalyptic world, a father and son embark on a perilous journey to survive and maintain their humanity.");
        book28.setDescription("Enter the eerie and haunted world of the Overlook Hotel as Stephen King weaves a chilling tale of isolation, madness, and supernatural horror.");
        book29.setDescription("Join Tom Sawyer and Huckleberry Finn on their mischievous adventures along the Mississippi River in this classic coming-of-age tale.");
        book30.setDescription("Set in the 1960s American South, this novel explores themes of race, identity, and female empowerment as a young girl seeks to uncover the truth about her mother's past.");
        book31.setDescription("Ernest Hemingway's iconic novel follows a group of expatriates in 1920s Paris as they navigate love, war, and the search for meaning in a post-war world.");
        book32.setDescription("Holden Caulfield's introspective narrative captures the disillusionment and teenage angst of a young man navigating the challenges of adulthood.");
        book33.setDescription("Alice Walker's epistolary novel tells the story of Celie, a young African American woman, as she finds her voice and identity in the face of adversity.");
        book34.setDescription("Set in 17th-century Netherlands, this historical novel imagines the life and enigmatic expression of Vermeer's famous painting through the eyes of a young girl.");
        book35.setDescription("Join Huck Finn and his friend Jim, an escaped slave, as they embark on a series of adventures along the Mississippi River, challenging societal norms and questioning the concept of freedom.");
        book36.setDescription("In this thought-provoking novel, a group of young boys stranded on a deserted island must confront their primal instincts and the darker side of human nature.");
        book37.setDescription("Step into the enchanting world of a hidden garden where two children discover the power of nature and friendship to heal and transform.");
        book38.setDescription("Experience the haunting and passionate tale of Heathcliff and Catherine in Emily Brontë's atmospheric masterpiece set on the windswept moors of Yorkshire.");
        book39.setDescription("Margaret Atwood's dystopian novel depicts a chilling future where women are subjugated, and one woman's fight for freedom and agency.");
        book40.setDescription("Set against the backdrop of the American Civil War, this sweeping novel follows the indomitable Scarlett O'Hara as she navigates love, loss, and survival.");
        book41.setDescription("Embark on an epic quest through Middle-earth as a fellowship of diverse characters seeks to destroy a powerful ring and save the world from darkness.");
        book42.setDescription("Enter the magical world of Macondo, where generations of the Buendía family experience love, war, and the complexities of human existence.");
        book43.setDescription("Nathaniel Hawthorne's tale of sin, guilt, and redemption follows the life of Hester Prynne and explores the consequences of secret passions in Puritan society.");
        book44.setDescription("Immerse yourself in the thrilling story of Edmond Dantès, a man unjustly imprisoned who seeks revenge and redemption in 19th-century France.");
        book45.setDescription("Oscar Wilde's haunting novel delves into the corrupting power of beauty and the pursuit of pleasure, raising questions about the nature of art and the duality of human existence.");
        book46.setDescription("Albert Camus's existential masterpiece follows the story of Meursault, an emotionally detached protagonist who grapples with the meaninglessness of life and the absurdity of existence.");
        book47.setDescription("Set during the Great Depression, this powerful novel explores the struggles of migrant workers in California and highlights the resilience of the human spirit.");
        book48.setDescription("Dive into the philosophical depths of Dostoevsky's masterpiece, which delves into themes of morality, free will, and the nature of God through the lives of three brothers.");
        book49.setDescription("William Faulkner's experimental novel offers a fragmented narrative that explores the decline of a once-great Southern family, examining themes of time, memory, and the complexities of race.");
        book50.setDescription("Victor Hugo's epic tale follows the lives of multiple characters against the backdrop of 19th-century France, intertwining themes of justice, love, and redemption.");
        book51.setDescription("Percy Jackson finds himself on a new quest to prevent a war between the gods. With his friends by his side, he must navigate treacherous waters and face ancient monsters to save the world once again.");

        book52.setDescription("In this thrilling installment, Percy Jackson embarks on a perilous journey through the Sea of Monsters to find the mythical Golden Fleece. With danger at every turn, he must confront his fears and unravel ancient secrets.");

        book53.setDescription("Percy Jackson faces his most challenging quest yet as he races against time to save his friends and prevent a catastrophic war. Filled with twists and turns, this book takes readers on an unforgettable adventure.");

        book54.setDescription("In the fourth book of the series, Percy Jackson enters the treacherous Labyrinth in search of a way to stop an ancient enemy. Along the way, he encounters mythical creatures and unravels the mysteries of the maze.");

        book55.setDescription("The final battle looms as Percy Jackson prepares to face the powerful Titan lord. With the fate of Olympus hanging in the balance, Percy must make a choice that will determine the outcome of the war and his own destiny.");


        bookTrack.getInstance().addBook(book51);
        bookTrack.getInstance().addBook(book52);
        bookTrack.getInstance().addBook(book53);
        bookTrack.getInstance().addBook(book54);
        bookTrack.getInstance().addBook(book55);
        bookTrack.getInstance().addBook(book56);
        bookTrack.getInstance().addBook(book57);
        bookTrack.getInstance().addBook(book58);
        bookTrack.getInstance().addBook(book59);
        bookTrack.getInstance().addBook(book60);
        bookTrack.getInstance().addBook(book61);
        bookTrack.getInstance().addBook(book62);
        bookTrack.getInstance().addBook(book63);
        bookTrack.getInstance().addBook(book64);
        bookTrack.getInstance().addBook(book1);
        bookTrack.getInstance().addBook(book2);
        bookTrack.getInstance().addBook(book3);
        bookTrack.getInstance().addBook(book4);
        bookTrack.getInstance().addBook(book5);
        bookTrack.getInstance().addBook(book6);
        bookTrack.getInstance().addBook(book7);
        bookTrack.getInstance().addBook(book8);
        bookTrack.getInstance().addBook(book9);
        bookTrack.getInstance().addBook(book10);
        bookTrack.getInstance().addBook(book11);
        bookTrack.getInstance().addBook(book12);
        bookTrack.getInstance().addBook(book13);
        bookTrack.getInstance().addBook(book14);
        bookTrack.getInstance().addBook(book15);
        bookTrack.getInstance().addBook(book16);
        bookTrack.getInstance().addBook(book17);
        bookTrack.getInstance().addBook(book18);
        bookTrack.getInstance().addBook(book19);
        bookTrack.getInstance().addBook(book20);
        bookTrack.getInstance().addBook(book21);
        bookTrack.getInstance().addBook(book22);
        bookTrack.getInstance().addBook(book23);
        bookTrack.getInstance().addBook(book24);
        bookTrack.getInstance().addBook(book25);
        bookTrack.getInstance().addBook(book26);
        bookTrack.getInstance().addBook(book27);
        bookTrack.getInstance().addBook(book28);
        bookTrack.getInstance().addBook(book29);
        bookTrack.getInstance().addBook(book30);
        bookTrack.getInstance().addBook(book31);
        bookTrack.getInstance().addBook(book32);
        bookTrack.getInstance().addBook(book33);
        bookTrack.getInstance().addBook(book34);
        bookTrack.getInstance().addBook(book35);
        bookTrack.getInstance().addBook(book36);
        bookTrack.getInstance().addBook(book37);
        bookTrack.getInstance().addBook(book38);
        bookTrack.getInstance().addBook(book39);
        bookTrack.getInstance().addBook(book40);
        bookTrack.getInstance().addBook(book41);
        bookTrack.getInstance().addBook(book42);
        bookTrack.getInstance().addBook(book43);
        bookTrack.getInstance().addBook(book44);
        bookTrack.getInstance().addBook(book45);
        bookTrack.getInstance().addBook(book46);
        bookTrack.getInstance().addBook(book47);
        bookTrack.getInstance().addBook(book48);
        bookTrack.getInstance().addBook(book49);
        bookTrack.getInstance().addBook(book50);


    }

    private Pane homePage(){
        inAccountPage = false;
        BorderPane pinPane = new BorderPane();
        rootHome = new VBox();
        rootHome.setAlignment(Pos.CENTER);


        SearchBarComboBox searchBar = new SearchBarComboBox();

        HBox filterBox = new HBox();
        filterBox.setPrefHeight(45);
        filterBox.setPadding(new Insets(10,10,10,10));
        filterBox.setSpacing(100);
        filterBox.setAlignment(Pos.CENTER);
        filterBox.setStyle("-fx-background-color: blue;");

        ScrollPane audiScroll = new ScrollPane();
        audiScroll.setFitToWidth(true);
        audiScroll.setFitToHeight(true);

        FlowPane contentPane = new FlowPane(Orientation.HORIZONTAL, 10, 10);
        contentPane.setAlignment(Pos.CENTER);
        loadBooks(contentPane, 0, 20);

        ImageView logo;
        Image logoImage = new Image(getClass().getResourceAsStream("BookImage/myLogo.png"));
        logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitHeight(50);

        logo.setOnMouseClicked(event -> {
            sceneMain.setRoot(homePage());
        });


        ComboBox<String> searchComboBox = searchBar.getComboBox();
        searchComboBox.setPrefWidth(400);
        Button btnFilterList = new Button("Filter");
        Button btnNewest = new Button("Newest");
        Button btnPopular = new Button("Popular");
        Button btnRandom = new Button("Random");
        Button btnAvailable = new Button("Available");

        MenuBar btnFilter = new MenuBar();
        Menu dateFilter = new Menu("Date");
        Menu ratingFilter = new Menu("Rating");

        dateFilter.setStyle("-fx-pref-width: 80px;");
        ratingFilter.setStyle("-fx-pref-width: 200px;");

        MenuItem sliderItem = new MenuItem("Rating Filter");
        sliderItem.setOnAction(e -> {
            Slider minSlider = new Slider(1, 5, 1);
            Slider MaxSliders = new Slider(1, 5, 5);

            minSlider.setShowTickMarks(true);
            minSlider.setShowTickLabels(true);
            minSlider.setMajorTickUnit(1);
            minSlider.setBlockIncrement(1);
            minSlider.setSnapToTicks(true);

            MaxSliders.setShowTickMarks(true);
            MaxSliders.setShowTickLabels(true);
            MaxSliders.setMajorTickUnit(1);
            MaxSliders.setBlockIncrement(1);
            MaxSliders.setSnapToTicks(true);


            Label minStars = new Label("Min Stars");
            Label MaxStars = new Label("Max Stars");


            minSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.doubleValue() > MaxSliders.getValue()) {
                    minSlider.setValue(oldVal.doubleValue());
                }
            });

            MaxSliders.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.doubleValue() < minSlider.getValue()) {
                    MaxSliders.setValue(oldVal.doubleValue());
                }
            });


            Button filterButton = new Button("Filter");
            filterButton.setOnAction(event -> {
                Book[] sortedList = BookTrack.getInstance().sortBooks(3, (int) minSlider.getValue(), (int) MaxSliders.getValue(), 2);
                List<Book> filteredList = Arrays.stream(sortedList)
                        .filter(book -> book.getRating() >= (int) minSlider.getValue() && book.getRating() <= (int) MaxSliders.getValue())
                        .collect(Collectors.toList());
                Book[] newList = new Book[filteredList.size()];
                newList = filteredList.toArray(newList);
                contentPane.getChildren().clear();
                loadBooks(contentPane, 0, 20, newList);
                audiScroll.setContent(contentPane);

                Stage stage = (Stage) filterButton.getScene().getWindow();
                stage.close();
            });


            VBox dialogVBox = new VBox(minStars, minSlider, MaxStars, MaxSliders, filterButton);
            dialogVBox.setAlignment(Pos.CENTER_RIGHT);
            dialogVBox.setSpacing(10);
            dialogVBox.setPadding(new Insets(10));

            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(dialogVBox);

            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.getDialogPane().getButtonTypes().clear();


            dialog.showAndWait();
        });

        MenuItem datePickerItem = new MenuItem("Date Picker");
        datePickerItem.setOnAction(e -> {
            //MultiDatePicker multiDatePicker = new MultiDatePicker().withRangeSelectionMode();
            DatePicker datePicker = new DatePicker();
            VBox datePickerContainer = new VBox(datePicker);


            Button filterButton = new Button("Filter");
            filterButton.setOnAction(event -> {
                LocalDate lowerDate = datePicker.getValue();
                LocalDate upperDate = datePicker.getValue();

                if (lowerDate != null && upperDate != null) {

                    int lowerDateInt = dateToInt(lowerDate);
                    int upperDateInt = dateToInt(upperDate);


                    Book[] newList = BookTrack.getInstance().sortBooks(4, lowerDateInt, upperDateInt, 1);
                    contentPane.getChildren().clear();
                    loadBooks(contentPane, 0, 20, newList);
                    audiScroll.setContent(contentPane);
                }


                Stage stage = (Stage) filterButton.getScene().getWindow();
                stage.close();
            });

            datePickerContainer.getChildren().add(filterButton);

            VBox.setMargin(filterButton, new Insets(10, 10, 10, 10));
            datePickerContainer.setAlignment(Pos.CENTER_RIGHT);

            Scene datePickerScene = new Scene(datePickerContainer, 200, 200);
            Stage datePickerStage = new Stage();
            datePickerStage.setScene(datePickerScene);
            datePickerStage.initStyle(StageStyle.UNDECORATED);
            datePickerStage.show();
        });

        dateFilter.getItems().addAll(datePickerItem);
        ratingFilter.getItems().addAll(sliderItem);
        btnFilter.getMenus().addAll(ratingFilter);


        MenuButton btnUserMenu = null;
        MenuItem btnUserProf = new MenuItem("Account");
        MenuItem btnAccountMang = new MenuItem("Settings");
        MenuItem btnLogOut = new MenuItem("Log Out");
        MenuItem btnAdmin = new MenuItem("Admin");

        btnUserProf.setOnAction(actionEvent -> {
            sceneMain.setRoot(accountPage());
        });
        btnAdmin.setOnAction(actionEvent -> {
            sceneMain.setRoot(adminPage());
        });
        if(currentUser != null) {
            if (currentUser.getFirstName() == null) {
                Text nameOfUser = new Text(currentUser.getUsername());
                nameOfUser.setFont(Font.font("Open Sans", 25));
                btnUserMenu = new MenuButton();
                btnUserMenu.setGraphic(nameOfUser);
            }else{
                Text nameOfUser = new Text(currentUser.getFirstName() + " " + currentUser.getLastName());
                nameOfUser.setFont(Font.font("Open Sans", 25));
                btnUserMenu.setGraphic(nameOfUser);
                //btnUserMenu = new MenuButton(currentUser.getFirstName() + " " + currentUser.getLastName());
            }
            btnUserMenu.setPrefSize(200,80);
            btnUserMenu.getItems().addAll(btnUserProf, btnLogOut);
            if(currentUser.isAdmin()){
                btnUserMenu.getItems().add(btnAdmin);
            }
        }else{
            btnUserMenu = new MenuButton();
        }
        btnUserMenu.setContentDisplay(ContentDisplay.CENTER);

        btnLogOut.setOnAction(event -> {
            currentUser = null;
            sceneMain.setRoot(homePage());
        });


        btnUserProf.setStyle("-fx-pref-width: 190px;");
        btnAccountMang.setStyle("-fx-pref-width: 190px;");
        btnLogOut.setStyle("-fx-pref-width: 190px;");


        btnFilter.setPrefSize(150,80);
        btnFilterList.setPrefSize(150,80);
        btnNewest.setPrefSize(150,80);
        btnPopular.setPrefSize(150,80);
        btnRandom.setPrefSize(150,80);
        btnAvailable.setPrefSize(150,80);

        Button btnSignIn = new Button("Sign In");
        btnSignIn.setPrefSize(200,80);
        btnSignIn.setStyle(" -fx-font-size: 30px; -fx-font-weight: bold;");

        btnSignIn.setOnMouseClicked(event -> {
            sceneMain.setRoot(loginPage());
        });

        threePane = new BorderPane();
        threePane.setPadding(new Insets(10,10,10,10));
        threePane.setLeft(logo);
        threePane.setCenter(searchComboBox);
        threePane.setPrefHeight(45);
        if(currentUser != null){
            threePane.setRight(btnUserMenu);
        }else{
            threePane.setRight(btnSignIn);
        }

        filterBox.getChildren().addAll( btnNewest, btnPopular, btnRandom, btnAvailable, btnFilter);


        audiScroll.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
            } else {
                double scrollContentHeight = audiScroll.getContent().getBoundsInLocal().getHeight();
                double scrollViewportHeight = audiScroll.getViewportBounds().getHeight();
                double vvalue = audiScroll.getVvalue();

                if (vvalue >= 1.0 && (scrollContentHeight > scrollViewportHeight)) {
                    loadBooks(contentPane, contentPane.getChildren().size(), 10);
                }
            }
        });

        searchComboBox.setOnAction(event -> {
            String selectedItem = searchComboBox.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                String[] fields = selectedItem.split(" ");
                StringBuilder bookTitleBuilder = new StringBuilder();

                for (int i = 0; i < fields.length - 2; i++) {
                    bookTitleBuilder.append(fields[i]).append(" ");
                }

                String bookTitle = bookTitleBuilder.toString().trim();
                System.out.println(bookTitle);
                Book selectedBook = BookTrack.getInstance().getBookFromTitle(bookTitle);

                if (selectedBook != null) {
                    sceneMain.setRoot(bookPage(selectedBook));
                }
            }
        });


        btnAvailable.setOnAction(event -> {
            List<Book> availableBooks = BookTrack.getInstance().getBookList().stream()
                    .filter(Book::isAvailable)
                    .collect(Collectors.toList());

            Book[] bookArray = availableBooks.toArray(new Book[0]);

            contentPane.getChildren().clear();
            loadBooks(contentPane, 0, 20, bookArray);
            audiScroll.setContent(contentPane);
        });

        btnNewest.setOnAction(event -> {
            if(firstRun){
                sortedList = BookTrack.getInstance().sortBooks(4, -1, -1, 0);
            }
            firstRun = false;
            // Always reverse the array
            sortedList = reverseArray(sortedList);
            contentPane.getChildren().clear();
            loadBooks(contentPane, 0, 20, sortedList);
            audiScroll.setContent(contentPane);
        });

        btnRandom.setOnAction(event -> {
            Collections.shuffle(BookTrack.getInstance().getBookList());
            contentPane.getChildren().clear();
            loadBooks(contentPane, 0, 20);
            audiScroll.setContent(contentPane);
        });

        btnPopular.setOnAction(event -> {
            if(firstRunCopyNum){
                sortedCopyNumList = BookTrack.getInstance().sortBooks(6, -1, -1, 0);
            }
            firstRunCopyNum = false;
            // Always reverse the array
            sortedCopyNumList = reverseArray(sortedCopyNumList);
            Collections.sort(BookTrack.getInstance().getBookList(), Comparator.comparingInt(Book::getCopyNum));
            contentPane.getChildren().clear();
            loadBooks(contentPane, 0, 20);
            audiScroll.setContent(contentPane);
        });

        audiScroll.setContent(contentPane);
        VBox transBox = new VBox();
        transBox.getChildren().addAll(threePane, filterBox);
        pinPane.setTop(transBox);
        rootHome.getChildren().addAll(pinPane, audiScroll);

        return rootHome;
    }

    private Pane bookPage(Book book){
        inAccountPage = false;
        rootBookPage = new VBox();
        rootBookPage.setAlignment(Pos.CENTER);

        HBox filterBox = new HBox();
        filterBox.setPrefHeight(45);
        filterBox.setPadding(new Insets(10,10,10,10));
        filterBox.setSpacing(100);
        filterBox.setAlignment(Pos.CENTER);
        filterBox.setStyle("-fx-background-color: blue;");

        SplitPane meatPane = createBookCard(book);

        rootBookPage.getChildren().addAll(threePane,filterBox, meatPane);
        BorderPane mainePaine = new BorderPane();
        mainePaine.setTop(rootBookPage);

        return mainePaine;
    }

    private Pane accountPage(){
        inAccountPage = true;
        rootAcctPage = new VBox();
        BorderPane pinPane = new BorderPane();
        rootAcctPage.setAlignment(Pos.CENTER);

        HBox filterBox = new HBox();
        filterBox.setPrefHeight(45);
        filterBox.setPadding(new Insets(10,10,10,10));
        filterBox.setSpacing(100);
        filterBox.setAlignment(Pos.CENTER);
        filterBox.setStyle("-fx-background-color: blue;");

        ScrollPane audiScroll = new ScrollPane();
        audiScroll.setFitToWidth(true);
        audiScroll.setFitToHeight(true);

        FlowPane contentPane = new FlowPane(Orientation.HORIZONTAL, 10, 10);
        contentPane.setAlignment(Pos.CENTER);

        for(Book book : currentUser.getBorrowed()){
            SplitPane meatPane = createBookCard(book);
            contentPane.getChildren().add(meatPane);
        }
        audiScroll.setContent(contentPane);

        audiScroll.setOnScroll(event -> {
            if (event.getDeltaY() > 0) {
            } else {
                double scrollContentHeight = audiScroll.getContent().getBoundsInLocal().getHeight();
                double scrollViewportHeight = audiScroll.getViewportBounds().getHeight();
                double vvalue = audiScroll.getVvalue();

                if (vvalue >= 1.0 && (scrollContentHeight > scrollViewportHeight)) {
                    loadBooks(contentPane, contentPane.getChildren().size(), 10);
                }
            }
        });

        threePane.setPrefHeight(45);
        VBox transBox = new VBox();
        transBox.getChildren().addAll(threePane, filterBox);
        pinPane.setTop(transBox);
        rootAcctPage.getChildren().addAll(pinPane, audiScroll);
        return rootAcctPage;
    }

    private Pane adminPage(){

        rootAdmin = new VBox();
        BorderPane twoPane = new BorderPane();
        SplitPane userPane = new SplitPane();
        SplitPane bookPane = bookStatisticsPage();


        ImageView logo;
        Image logoImage = new Image(getClass().getResourceAsStream("BookImage/myLogo.png"));
        logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitHeight(50);

        HBox filterBox = new HBox();
        filterBox.setPrefHeight(60);
        filterBox.setPadding(new Insets(10,10,10,10));
        filterBox.setSpacing(100);
        filterBox.setAlignment(Pos.CENTER);
        filterBox.setStyle("-fx-background-color: blue;");

        MenuButton btnUserMenu = null;
        MenuItem btnUserProf = new MenuItem("Account");
        MenuItem btnAccountMang = new MenuItem("Settings");
        MenuItem btnLogOut = new MenuItem("Log Out");
        MenuItem btnAdmin = new MenuItem("Admin");

        Button btnMngBooks = new Button("Manage Books");
        Button btnMngUsers = new Button("Manage Users");
        Button btnAddBooks = new Button("Add Books");
        Button btnRemoveBooks = new Button("Remove Books");

        btnMngBooks.setPrefSize(150, 40);
        btnMngUsers.setPrefSize(150, 40);
        btnAddBooks.setPrefSize(150, 40);
        btnRemoveBooks.setPrefSize(150, 40);

        filterBox.getChildren().addAll(btnMngBooks, btnMngUsers, btnAddBooks, btnRemoveBooks);

        btnUserProf.setOnAction(actionEvent -> {
            sceneMain.setRoot(accountPage());
        });
        if(currentUser != null) {
            if (currentUser.getFirstName() == null) {
                Text nameOfUser = new Text(currentUser.getUsername());
                nameOfUser.setFont(Font.font("Open Sans", 25));
                btnUserMenu = new MenuButton();
                btnUserMenu.setGraphic(nameOfUser);
            }else{
                Text nameOfUser = new Text(currentUser.getUsername());
                nameOfUser.setFont(Font.font("Open Sans", 25));
                btnUserMenu.setGraphic(nameOfUser);
                //btnUserMenu = new MenuButton(currentUser.getFirstName() + " " + currentUser.getLastName());
            }
            btnUserMenu.setPrefSize(200,80);
            btnUserMenu.getItems().addAll(btnUserProf,  btnLogOut);
            if(currentUser.isAdmin()){
                btnUserMenu.getItems().add(btnAdmin);
            }
        }
        btnUserMenu.setContentDisplay(ContentDisplay.CENTER);

        btnLogOut.setOnAction(event -> {
            currentUser = null;
            sceneMain.setRoot(homePage());
        });

        btnUserProf.setStyle("-fx-pref-width: 190px;");
        btnAccountMang.setStyle("-fx-pref-width: 190px;");
        btnLogOut.setStyle("-fx-pref-width: 190px;");

        twoPane.setPadding(new Insets(10,10,10,10));
        twoPane.setLeft(logo);
        twoPane.setRight(btnUserMenu);

        TableView<User> userTable = new TableView<>();
        ObservableList<User> users = FXCollections.observableArrayList(UserTrack.getInstance().getUserList());

        TableColumn<User, String> usernameColumn = new TableColumn<>("username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        userTable.getColumns().addAll(usernameColumn, passwordColumn);
        userTable.setItems(users);


        ListView<String> borrowedBooksList = new ListView<>();

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                User selectedUser = userTable.getSelectionModel().getSelectedItem();
                ObservableList<String> borrowedBooks = FXCollections.observableArrayList();
                for (Book book : selectedUser.getBorrowed()) {
                    borrowedBooks.add(book.getTitle());
                }
                borrowedBooksList.setItems(borrowedBooks);
            }
        });


        btnAddBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<Book> dialog = new Dialog<>();
                dialog.setTitle("Add a New Book");


                TextField titleField = new TextField();
                titleField.setPromptText("Title");
                TextField authorField = new TextField();
                authorField.setPromptText("Author");
                TextField genreField = new TextField();
                genreField.setPromptText("Genre");
                TextField ratingField = new TextField();
                ratingField.setPromptText("Rating");
                TextField isbnField = new TextField();
                isbnField.setPromptText("ISBN");
                TextField pubDateField = new TextField();
                pubDateField.setPromptText("Date Published");
                TextField copiesField = new TextField();
                copiesField.setPromptText("Number of Copies");

                GridPane grid = new GridPane();
                grid.add(new Label("Title:"), 0, 0);
                grid.add(titleField, 1, 0);
                grid.add(new Label("Author:"), 0, 1);
                grid.add(authorField, 1, 1);
                grid.add(new Label("Genre:"), 0, 2);
                grid.add(genreField, 1, 2);
                grid.add(new Label("Rating:"), 0, 3);
                grid.add(ratingField, 1, 3);
                grid.add(new Label("ISBN:"), 0, 4);
                grid.add(isbnField, 1, 4);
                grid.add(new Label("Date Published:"), 0, 5);
                grid.add(pubDateField, 1, 5);
                grid.add(new Label("Number of Copies:"), 0, 6);
                grid.add(copiesField, 1, 6);

                dialog.getDialogPane().setContent(grid);

                ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButton) {
                        String pubDateString = pubDateField.getText();
                        int pubDate = 0;

                        try {
                            if (pubDateString.contains("-") || pubDateString.contains("/")) {
                                String pattern = pubDateString.contains("-") ? "yyyy-MM-dd" : "dd/MM/yyyy";
                                SimpleDateFormat format = new SimpleDateFormat(pattern);
                                Date date = format.parse(pubDateString);
                                pubDate = date.getYear() + 1900; // getYear() returns the year since 1900
                            } else {
                                pubDate = Integer.parseInt(pubDateString);
                            }
                        } catch (ParseException e) {
                            // Handle parse exception, e.g., show a dialog to user
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid Date Format");
                            alert.setHeaderText(null);
                            alert.setContentText("The provided date is in an invalid format. Please use yyyy-MM-dd or dd/MM/yyyy.");
                            alert.showAndWait();
                            return null;
                        }

                        Book newBook = new Book(
                                titleField.getText(),
                                authorField.getText(),
                                genreField.getText(),
                                Long.parseLong(isbnField.getText()),
                                pubDate,
                                Integer.parseInt(ratingField.getText()),
                                Integer.parseInt(copiesField.getText())
                        );
                        boolean isAdded = BookTrack.getInstance().addBook(newBook);
                        if (isAdded) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Book added successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Failure");
                            alert.setHeaderText(null);
                            alert.setContentText("Unable to add book!");
                            alert.showAndWait();
                        }
                        return newBook;
                    }
                    return null;
                });

                dialog.showAndWait();
            }
        });

        btnRemoveBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Remove a Book");

                TextField isbnField = new TextField();
                isbnField.setPromptText("ISBN");

                GridPane grid = new GridPane();
                grid.add(new Label("ISBN:"), 0, 0);
                grid.add(isbnField, 1, 0);

                dialog.getDialogPane().setContent(grid);

                ButtonType removeButton = new ButtonType("Remove", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(removeButton, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == removeButton) {

                        boolean isRemoved = BookTrack.getInstance().removeBook(Long.parseLong(isbnField.getText()));
                        if (isRemoved) {

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Book removed successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Failure");
                            alert.setHeaderText(null);
                            alert.setContentText("Book doesn't exist!");
                            alert.showAndWait();
                        }
                    }
                    return null;
                });

                dialog.showAndWait();
            }
        });

        logo.setOnMouseClicked(event -> {
            sceneMain.setRoot(homePage());
        });

        btnMngBooks.setOnAction(actionEvent -> {
            rootAdmin.getChildren().removeAll(userPane);
            rootAdmin.getChildren().add(bookPane);
        });
        btnMngUsers.setOnAction(actionEvent -> {
            rootAdmin.getChildren().removeAll(bookPane);
            rootAdmin.getChildren().add(userPane);
        });
        userPane.getItems().addAll(userTable, borrowedBooksList);

        rootAdmin.getChildren().addAll(twoPane, filterBox, bookPane);

        return rootAdmin;

    }

    private Pane loginPage() {
        GridPane PaneLoginPage = new GridPane();
        PaneLoginPage.setAlignment(Pos.CENTER);
        PaneLoginPage.setPadding(new Insets(20));
        PaneLoginPage.setHgap(10);
        PaneLoginPage.setVgap(10);

        Label lblUsername = new Label("Username");
        Label lblPassword = new Label("Password");
        Label lblConfirmPass = new Label("Confirm Password");
        TextField tfUsername = new TextField();
        TextField tfPassword = new TextField();
        TextField tfConfirmPass = new TextField();

        PaneLoginPage.add(lblUsername, 0, 0);
        PaneLoginPage.add(lblPassword, 0, 1);
        PaneLoginPage.add(tfUsername, 1, 0, 2, 1);
        PaneLoginPage.add(tfPassword, 1, 1, 2, 1);

        HBox HboxLogin = new HBox();
        HboxLogin.setSpacing(10);
        HboxLogin.setAlignment(Pos.CENTER_RIGHT);
        Button btnLogin = new Button("Login");
        Button btnSignUp = new Button("Sign up");
        Button btnCancelLogin = new Button("Cancel");
        Button btnSignUpConfirm = new Button("Sign up");
        btnLogin.setDisable(true);
        btnLogin.setPrefWidth(60);
        btnSignUp.setPrefWidth(60);
        HboxLogin.getChildren().addAll(btnCancelLogin, btnSignUp, btnLogin);
        PaneLoginPage.add(HboxLogin, 1, 2, 2, 1);

        //everything above here is main login screen
        class MyKeyEventHanlder implements EventHandler<KeyEvent> {
            public void handle(KeyEvent e) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String confirmPassword = tfConfirmPass.getText();
                btnLogin.setDisable(!validateLogin(username, password));
            }
        }
        tfUsername.setOnKeyTyped(new MyKeyEventHanlder());
        tfPassword.setOnKeyTyped(new MyKeyEventHanlder());
        tfConfirmPass.setOnKeyTyped(new MyKeyEventHanlder());

        btnCancelLogin.setOnAction(e->{
            sceneMain.setRoot(homePage());
        });

        btnLogin.setOnAction(e->{
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            boolean checkUserExist = UserTrack.getInstance().userLoginValidation(username, password);
            if (checkUserExist) {
                currentUser = UserTrack.getInstance().getUser(new User(username, password));
                sceneMain.setRoot(homePage());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("error-header");
                alert.setContentText("Failed to validate user login");
                alert.showAndWait();
            }
        });

        btnSignUp.setOnAction(e->{
            PaneLoginPage.add(lblConfirmPass, 0, 2);
            PaneLoginPage.add(tfConfirmPass, 1, 2, 2, 1);
            PaneLoginPage.getChildren().remove(HboxLogin);

            HBox HboxSignUp = new HBox();
            HboxSignUp.setSpacing(10);
            HboxSignUp.setAlignment(Pos.CENTER_RIGHT);
            Button btnCancel = new Button("Cancel");
            btnSignUpConfirm.setDisable(true);
            btnCancel.setPrefWidth(60);
            btnSignUpConfirm.setPrefWidth(60);
            HboxSignUp.getChildren().addAll(btnSignUpConfirm, btnCancel);
            PaneLoginPage.add(HboxSignUp, 1, 3, 2, 1);

            btnCancel.setOnAction(e1->{
                PaneLoginPage.getChildren().remove(lblConfirmPass);
                PaneLoginPage.getChildren().remove(tfConfirmPass);
                PaneLoginPage.getChildren().remove(HboxSignUp);
                PaneLoginPage.add(HboxLogin, 1, 2, 2, 1);
            });

            btnSignUpConfirm.setOnAction(e1->{
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String confirmPassword = tfConfirmPass.getText();
                if(password.equals(confirmPassword) && !UserTrack.getInstance().userExists(username)){
                    User user = new User(username, password);
                    UserTrack.getInstance().addUser(user);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("User sign up is validated");
                    alert.showAndWait();
                    currentUser = user;
                    sceneMain.setRoot(homePage());
                }else{
                    if(UserTrack.getInstance().userExists(username)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("error-header");
                        alert.setContentText("User already exists");
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("error-header");
                        alert.setContentText("Failed to validate user sign up");
                        alert.showAndWait();
                    }
                }
            });
        });

        tfUsername.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String confirmPassword = tfConfirmPass.getText();
                btnLogin.setDisable(!validateLogin(username, password));
                btnSignUpConfirm.setDisable(!validateSignUp(username, password, confirmPassword));
            }
        });

        tfPassword.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String confirmPassword = tfConfirmPass.getText();
                btnLogin.setDisable(!validateLogin(username, password));
                btnSignUpConfirm.setDisable(!validateSignUp(username, password, confirmPassword));
            }
        });

        tfConfirmPass.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String confirmPassword = tfConfirmPass.getText();
                btnSignUpConfirm.setDisable(!validateSignUp(username, password, confirmPassword));
            }
        });

        return PaneLoginPage;
    }

    private void loadBooks(FlowPane contentPane, int startIndex, int count) {
        ArrayList<Book> bookList = BookTrack.getInstance().getBookList();
        for (int i = startIndex; i < startIndex + count; i++) {
            if (i >= bookList.size()){
                break;
            }
            Book book = bookList.get(i);
            SplitPane splitPane = createBookCard(book);
            contentPane.getChildren().add(splitPane);
        }
    }

    private void loadBooks(FlowPane contentPane, int startIndex, int count, Book[] books) {
        for (int i = startIndex; i < startIndex + count; i++) {
            if (i >= books.length){
                break;
            }
            Book book = books[i];
            SplitPane splitPane = createBookCard(book);
            contentPane.getChildren().add(splitPane);
        }
    }

    private SplitPane createBookCard(Book book) {

        ImageView bookImage;

        try {
            Image image = new Image(getClass().getResourceAsStream("BookImage/" + book.getNameNoSpaces() + ".png"));
            if (image.isError()) {
                throw new Exception("Image can't be loaded");
            }
            bookImage = new ImageView(image);
        } catch (Exception e) {
            bookImage = new ImageView(new Image(getClass().getResourceAsStream("BookImage/default.png")));
        }

        BorderPane imagePane = new BorderPane();
        Label copyNum = new Label("Copies: " + book.getCopyNum());
        imagePane.setRight(copyNum);
        VBox meatOf = new VBox();
        meatOf.setPadding(new Insets(10, 10, 10, 10));

        BorderPane topPane = new BorderPane();
        BorderPane authorAndDate = new BorderPane();
        //authorAndDate.setSpacing(50);

        Button btnBorrow;
        Button btnReturn = new Button("Return");
        if(book.getCopyNum() < 1){
            btnBorrow = new Button("Unavailable");
            btnBorrow.setDisable(true);
            topPane.setRight(btnBorrow);
        }else{
            btnBorrow = new Button("Borrow");
            if(currentUser != null){
                if(currentUser.borrowedBook(book)) {
                    topPane.setRight(btnReturn);
                    btnReturn.setOnAction(event -> {
                        currentUser.returnBook(book);
                        copyNum.setText("Copies: " + book.getCopyNum());
                        imagePane.setRight(copyNum);
                        topPane.setRight(btnBorrow);
                    });
                }else{
                    topPane.setRight(btnBorrow);
                    btnBorrow.setOnAction(event -> {
                        currentUser.borrowBook(book);
                        copyNum.setText("Copies: " + book.getCopyNum());
                        imagePane.setRight(copyNum);
                        topPane.setRight(btnReturn);
                    });
                }
            }else{
                topPane.setRight(btnBorrow);
                btnBorrow.setOnAction(event -> {
                    if(currentUser == null){
                        sceneMain.setRoot(loginPage());
                    }else{
                        currentUser.borrowBook(book);
                        book.setCopyNum(book.getCopyNum() - 1);
                        copyNum.setText("Copies: " + book.getCopyNum());
                        imagePane.setRight(copyNum);
                    }
                });
            }
        }

        btnReturn.setOnMouseClicked(event -> {
            if(inAccountPage){
                currentUser.returnBook(book);
                copyNum.setText("Copies: " + book.getCopyNum());
                imagePane.setRight(copyNum);
                topPane.setRight(btnBorrow);
            }else{
                sceneMain.setRoot(accountPage());
            }
        });
        btnBorrow.setPrefSize(100, 50);
        btnReturn.setPrefSize(100, 50);

        Label titleLabel = new Label(book.getTitle());
        titleLabel.setMaxWidth(340);

        Label authorAndPDate = new Label(String.format("%-60s %-15s", " By: " + book.getAuthor(), book.getPubDateString()));

        Label authorLabel = new Label(" By: " + book.getAuthor());
        authorLabel.setMaxWidth(240);
        Label datePublishedLabel = new Label(book.getPubDateString());

        TextArea description = new TextArea(book.getDescription());
        description.setWrapText(true);
        description.setStyle("-fx-background-color: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        if(currentUser != null && currentUser.isAdmin()){
            description.setEditable(true);
            description.setFocusTraversable(true);
            description.setOnKeyReleased(event -> {
                book.setDescription(description.getText());
            });
        }
        description.setEditable(false);
        description.setFocusTraversable(false);

        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        authorAndDate.setLeft(authorLabel);
        authorAndDate.setCenter(datePublishedLabel);
        //authorAndDate.getChildren().addAll(authorLabel, datePublishedLabel);

        titleLabel.setOnMouseClicked(event -> {
            sceneMain.setRoot(bookPage(book));
        });

        //================================
        topPane.setLeft(titleLabel);

        ImageView ratingImage = new ImageView(new Image(getClass().getResourceAsStream("StarRating/StarRating" + book.getRatingString() + ".png")));
        ratingImage.setPreserveRatio(true);
        //ratingImage.setFitHeight(30);
        ratingImage.setFitWidth(120);
        imagePane.setLeft(ratingImage);


        meatOf.getChildren().addAll(topPane, authorAndPDate, imagePane, description);

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.setPrefHeight(200);
        splitPane.getItems().addAll(bookImage, meatOf);

        return splitPane;
    }

    private SplitPane bookStatisticsPage() {
        SplitPane splitPane = new SplitPane();

        TableView<Book> tableView = new TableView<>();
        ObservableList<Book> bookList = FXCollections.observableArrayList(BookTrack.getInstance().getBookList());
        tableView.setItems(FXCollections.observableArrayList(BookTrack.getInstance().getBookList()));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));

        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenre()));

        TableColumn<Book, String> pubDateColumn = new TableColumn<>("Publication Date");
        pubDateColumn.setCellValueFactory(new PropertyValueFactory<>("pubDateString"));
        pubDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPubDateString()));

        TableColumn<Book, Integer> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRating()));

        TableColumn<Book, Integer> copiesColumn = new TableColumn<>("Copies");
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copyNum"));
        copiesColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCopyNum()));

        TableColumn<Book, Long> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        isbnColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIsbn()));

        tableView.getColumns().addAll(titleColumn, authorColumn, genreColumn, pubDateColumn, ratingColumn, copiesColumn, isbnColumn);
        tableView.setItems(bookList);

        // Charts
        VBox chartsPane = new VBox();
        PieChart genreChart = createGenrePieChart();
        PieChart ratingChart = createRatingPieChart();
        BarChart<String, Number> pubYearChart = createPubYearBarChart();

        chartsPane.getChildren().addAll(genreChart, ratingChart, pubYearChart);

        splitPane.getItems().addAll(tableView, chartsPane);

        return splitPane;
    }

    private BarChart<String, Number> createPubYearBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        Map<String, Integer> pubYearCounts = new TreeMap<>();
        for (Book book : BookTrack.getInstance().getBookList()) {
            String pubYear = String.valueOf(book.getPubDate()).substring(0, 4);
            pubYearCounts.put(pubYear, pubYearCounts.getOrDefault(pubYear, 0) + 1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : pubYearCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);

        return barChart;
    }

    private PieChart createGenrePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Integer> genreCounts = new HashMap<>();

        for (Book book : BookTrack.getInstance().getBookList()) {
            genreCounts.put(book.getGenre(), genreCounts.getOrDefault(book.getGenre(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        return new PieChart(pieChartData);
    }

    private PieChart createRatingPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<Integer, Integer> ratingCounts = new HashMap<>();

        for (Book book : BookTrack.getInstance().getBookList()) {
            ratingCounts.put(book.getRating(), ratingCounts.getOrDefault(book.getRating(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
            pieChartData.add(new PieChart.Data(String.valueOf(entry.getKey()), entry.getValue()));
        }

        return new PieChart(pieChartData);
    }

    private boolean validateLogin(String username, String password) {
        return (username.length()>=4 && password.length()>=4);
    }

    private boolean validateSignUp(String username, String password, String confirmPassword) {
        return (username.length()>=4 && password.length()>=4 && confirmPassword.length()>=4);
    }

    private int dateToInt(LocalDate date) {
        return date.getYear() * 10000
                + date.getMonthValue() * 100
                + date.getDayOfMonth();
    }

    private Book[] reverseArray(Book[] books) {
        for (int i = 0; i < books.length / 2; i++) {
            Book temp = books[i];
            books[i] = books[books.length - 1 - i];
            books[books.length - 1 - i] = temp;
        }
        return books;
    }

}