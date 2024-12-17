package nl.heartmates01.library;

// Als aanvulling daarop maak je ook een abstracte Item class.
// Book en Magazine zullen deze class Item overerven.
// Zorg dus dat alle velden die Magazine en Book gemeen hebben in de Item class staan.

//- id (long)
//- title (String)
//- pages (int)
//- publicationDate (LocalDate)

// De abstracte class Item krijgt de abstract method String getOverviewText(),
// de subclasses die deze method implementeren geven een String terug met daarin:

// Voor Book: "Isbn: {isbn} - Title: {title} - Author: {author}"
// Voor DailyMagazine: "Issn: {issn} - Title: {title} - Issue: {issueNumber}"
// Voor WeeklyMagazine: "Issn: {issn} - Title: {title} - Week: {weekNumber}"
// Voor MonthlyMagazine: "Issn: {issn} - Title: {title} - Month: {monthNumber}"

// 07/Polymorphism

// Maak een lijst met alle boeken en tijdschriften aan de hand van de Item class.

// Haal uit de lijst van boeken en tijdschriften alle items
// die zijn uitgeleend en gebruik voor de weergave de getOverviewText() methode.

// Schrijf de code die de juiste uitleentermijn terug geeft via de Item class.

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Stream;

import static nl.heartmates01.book.BookRepository.allBooks;
import static nl.heartmates01.magazine.MagazineRepository.allMags;

public abstract class Item {

  List<Item> allItems = Stream.concat(allBooks.stream(), allMags.stream()).toList();

  protected Item(int id, String title, int pages, boolean borrowed, int borrowTime,
      LocalDate publicationDate) {
  }

  public abstract String getOverviewText();

  protected abstract void borrow();

  protected abstract void returnn();

}
