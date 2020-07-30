package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            ADD_TO_MY_EXISTING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_NAME}']"
            ;

    /*TEMPLATES METHODS*/
    private static String getMyListName (String list_name) {

        return ADD_TO_MY_EXISTING_LIST_TPL.replace("{LIST_NAME}", list_name);
    }
    /*TEMPLATES METHODS*/

    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,"Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
       this.swipeUpToFindElement(
               FOOTER_ELEMENT,
               "Cannot find the end of article",
               20
       );
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                20
        );


        System.out.println(driver.findElements(By.xpath("//*[@text='Add to reading list']")));

        this.waitForElementAndClick(
                OPTIONS_ADD_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle () {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticleToMyExistingList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                20
        );

        System.out.println(driver.findElements(By.xpath("//*[@text='Add to reading list']")));

        this.waitForElementAndClick(
                OPTIONS_ADD_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15);

        String list_name_xpath = getMyListName(name_of_folder);

        this.waitForElementAndClick(
                 list_name_xpath,
                "Cannot find folder " + name_of_folder + " in saved reading lists",
                5
        );

    }

    public void assertArticleTitlePresent () {

        this.assertElementPresent(
                TITLE,
                "Article title is not present in article page");

    }

}
