package lab5_2;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;
import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LibrarySteps {
	static final Logger log = getLogger(lookup().lookupClass());

    private Library library  = new Library();
    private List<String> result = new ArrayList<>();

	public LocalDateTime iso8601Date(String Day, ){}

    @Given(".+book with the title '(.+)', written by '(.+)', published in (.+)")
	public void addNewBook(final String title, final String author, @Format("dd MMMMM yyyy") final Date published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

	@When("^the customer searches for books published between (\\d+) and (\\d+)$")
	public void setSearchParameters(@Format("yyyy") final Date from, @Format("yyyy") final Date to) {
		result = library.findBooks(from, to);
	}

	@Then("(\\d+) books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}

	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

}