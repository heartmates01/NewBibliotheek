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

// Maak een lijst met alle boeken en tijdschriften aan de hand van de Item class.
// Haal uit de lijst van boeken en tijdschriften alle items
// die zijn uitgeleend en gebruik voor de weergave de getOverviewText() methode.

// Schrijf de code die de juiste uiterleentermijn terug geeft via de Item class.

import java.time.LocalDate;

public abstract class Item {

  protected Item(long id, String title, int pages, LocalDate publicationDate) {
  }

  public abstract String getOverviewText();
}
