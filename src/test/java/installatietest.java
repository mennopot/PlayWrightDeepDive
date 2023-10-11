import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

public class installatietest {

    @Test
    public void areYouReady() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();

        Page page = browser.newPage();
        page.navigate("https://www.google.com/");

        browser.close();
        playwright.close();
    }
}
