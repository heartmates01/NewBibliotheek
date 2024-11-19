package nl.heartmates01;

// We gaan een simpel Bibliotheek Systeem maken. We hebben nodig:

// Een package system met daarin de classes Library en Book.
// Een package main met daarin de class Main waarin we de applicatie starten met de main method.

// Zorg er met access-modifiers voor dat de class Main niet direct kan bij de class Book,
// maar dat de class Library daar nog wel bij kan en diens methods kan gebruiken.

// De class Book heeft de volgende members (velden)
// LETOP: Andere classes mogen niet direct bij deze members kunnen:

// id (long)
// title (String)
// author (String)
// pages (int)
// isbn (String)
// borrowed (boolean)

// De class Book heeft de volgende methods:

// long getId()
// boolean hasBeenBorrowed()
// Deze method geeft de waarde van borrowed terug.

// String getTitleWithAuthor()
// Deze method geeft de titel en de auteur van het boek terug als een String.

// String toString()
// Deze method geeft alle informatie van het boek terug als een String
// (tip: gebruik een String template """).'

// void borrowBook()
// Deze method zet de borrowed waarde op true.

// void returnBook()
// Deze method zet de borrowed waarde op false.

// De class Library heeft de volgende members (velden)
// LETOP: andere classes mogen niet direct by deze members kunnen:

// books (List)

// De class Library heeft de volgende methods:

// void addBook(String title, String author, int pages, String isbn)
// deze method maakt een nieuw Book object aan en voegt deze toe aan de books List.

// void removeBook(long id)
// Deze method zoekt het boek op in de books List en verwijderd deze uit de List.

// String showBook(long id)
// Deze method zoekt het boek op in de books List en geeft de informatie van het boek terug als een String.

// void borrowBook(long id)
// Deze method zoekt het boek op in de books List en zet de borrowed waarde op true.

// void returnBook(long id)
// Deze method zoekt het boek op in de books List en zet de borrowed waarde op false.

// String showBooks()
// Deze method geeft alle boeken in de books List terug als een String, per book de Titel en de Auteur.

// String showBorrowedBooks()
// Deze method geeft alle geleende boeken in de books List terug als een String,
// per book de Titel en de Auteur.

// String showAvailableBooks()
// Deze method geeft alle beschikbare boeken in de books List terug als een String,
// per book de Titel en de Auteur.

// Laat in de class Main zien dat je:

// Boeken kunt toevoegen
// Boeken kunt verwijderen
// Boeken kunt tonen
// Een lijst van boeken kunt tonen
// Een boek kunt lenen
// Een boek kunt terugbrengen
// Een lijst van geleende boeken kunt tonen
// Een lijst van beschikbare boeken kunt tonen

// Als dat lukt, mag je hier zoals in voorgaande opdrachten een menu voor maken,
// waarbij een gebruiker dit zelf allemaal via de console kan doen.

// Het belangrijkste is dat je kunt laten zien dat je weet hoe je met packages en encapsulation kan werken.

import java.util.Scanner;
import java.util.regex.Pattern;
import nl.heartmates01.book.BookController;
import nl.heartmates01.magazine.MagazineController;


public class Main {

  public static void main(String[] args) {

    while (true) {
      System.out.println("""
           \s
           Library Management System
           \s
           1. Manage Books
           2. Manage Magazines
           3. Exit
          """);

      String userFirstChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-3]"),
          "Choose a valid option");

      switch (userFirstChoice) {
        case "1":
          BookController.showBookMenu();
          break;
        case "2":
          MagazineController.showMagazineMenu();
          break;
        case "3":
          return;
      }
    }
  }

  static Scanner scanner = new Scanner(System.in);

  public static String userInput(String question, Pattern pattern, String errorMessage) {
    System.out.println(question);

    if (pattern != null) {
      while (!scanner.hasNext(pattern)) {
        System.out.println(errorMessage);
        scanner.next();
      }
    }
    return scanner.nextLine();
  }
}
