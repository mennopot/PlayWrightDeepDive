import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GuiLoginExampleTest {

    @Test
    public void aFirstPlaywrightTest() {

        // Create a new Playwright session
        Playwright playwright = Playwright.create();

        // Start a new Chromium browser
//        Browser browser = playwright.chromium().launch();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Create a new Page
        Page page = browser.newPage();

        // Navigate to https://www.saucedemo.com
        page.navigate("https://www.saucedemo.com/");

        page.getByPlaceholder("Username").fill("standard_user");
        page.getByPlaceholder("Password").fill("secret_sauce");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        // Check that the element with text 'Products' is visible
        // See https://playwright.dev/java/docs/locators#locate-by-text for a hint.
        assertThat(page.getByText("Products")).isVisible();

        // Close the browser and the Playwright session
        browser.close();
        playwright.close();
    }
}
