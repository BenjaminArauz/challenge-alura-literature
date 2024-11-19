# 📚 Book Catalog (Challenge alura literatura)

This project was developed during the **Literatura Challenge** for **[AluraLatam][2]**. In this challenge, I developed an interactive console-based application that retrieves and displays book data from an online API, giving users an engaging experience with book-related information in real-time.

## 🚀 Project Overview

This console application allows users to search, view, and filter books from the vast [Gutendex API](https://gutendex.com/). It offers users various ways to explore book information, including author details, publication years, and language filters.

### Key Features:
- **Book Search**: Search for books by title or keyword to access a broad selection.
- **Display Searched Books**: View the list of books retrieved from the last search.
- **Author Information**: Display a list of authors from the last search or retrieve specific details.
- **Publication Year Display**: Check a year associated with an author’s life.
- **Language Filter**: Filter books by language for a tailored search experience.

## 🛠️ Technologies Used:
- **Java 17**: The primary programming language for the project.
- **HttpClient**: Used to make HTTP requests to the Gutendex API.
- **Gson**: For parsing JSON data returned by the API.
- **Gutendex API**: Provides open-access book data from the Gutenberg Project.
- **Postgress SQL**: It is the database where all the information about the books is stored. 

## 💻 Project Structure

### 1. **Main.java**
This class represents the main entry point of the application. It provides an interactive menu for users to:
- Search for books by keyword.
- Display a list of the latest searched books.
- View authors from the latest search.
- Display the lifespan of an author.
- Filter books by language.

### 2. **Book.java**
This class structures the data for each book retrieved from the API, containing fields like title, author, language, and publication date.

### 3. **ApiService.java**
The class responsible for interacting with the API, making HTTP requests, and handling JSON responses.

### 4. **Author.java**
This class structures author data, allowing the application to display relevant author details when requested.

## 🌟 How to Run the Project

1. **Clone the repository:**

```bash
git clone https://github.com/YourUsername/Challenge-Alura-Book-Catalog.git bookcatalog
```

2. **Navigate to the project folder:**

```bash
cd bookcatalog
```

3. **Run the project:** You can run the ```BookCatalog.java``` file directly from your IDE or through the command line:

```bash
javac BookCatalog.java
java BookCatalog
```

## 🌐 API Credits
This project uses the [Gutendex API][1] to retrieve book data. Special thanks to their team for providing open-access information from the Gutenberg Project.

## 📌 Future Enhancements
- **Enhanced Filtering Options:** Add options to filter by more criteria (e.g., genre or publication year).
- **Book Recommendations:** Suggest books based on previous search history.
- **Extended Language Support:** Provide translations for user interaction in multiple languages.


[1]: https://gutendex.com/ "Gutendex-API"
[2]: https://www.aluracursos.com/ "Alura Cursos"