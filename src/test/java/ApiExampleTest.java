import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiExampleTest {

    private Playwright playwright;

    private APIRequestContext request;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();

        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://jsonplaceholder.typicode.com")
        );
    }

    @Test
    public void retrievingAndVerifyingUserDetails() {

        APIResponse response = request.get("/users/1");

        assertEquals(200, response.status());

        assertEquals("application/json; charset=utf-8", response.headers().get("content-type"));
    }

    @Test
    public void postingContentToAnAPI() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", 1);
        data.put("title", "My new post title");
        data.put("body", "My awesome new post body");

        APIResponse response = request.post("/posts", RequestOptions.create().setData(data));

        System.out.println(new String(response.body()));

        assertEquals(201, response.status());
    }

    @AfterEach
    public void stopSession() {

        if (request != null) {
            request.dispose();
            request = null;
        }

        playwright.close();
    }
}
