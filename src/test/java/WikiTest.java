import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WikiTest {
    @BeforeAll
    public static void beforeAll()
    {
        Configuration.baseUrl = "https://github.com"; // открываем страницу Selenide в GitHub (абсолютный путь)
        Configuration.pageLoadStrategy = "eager"; // команда для того, чтобы селенид не ждал загрузки всех картинок и тяжелых элементов. Только html.
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
    }
    @Test
    void wikiTestSelenide() {
        open("/selenide/selenide"); // открываем страницу Selenide в GitHub (относительный путь)
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
//        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));

        $(".wiki-rightbar").$("[class=\"Link--muted js-wiki-more-pages-link btn-link mx-auto f6\"]").click();

        $$("ul.filterable-active div a").findBy(text("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("""
                Using JUnit5 extend test class:
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }
}