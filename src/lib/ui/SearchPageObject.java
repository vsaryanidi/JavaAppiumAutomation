package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_LINE = "org.wikipedia:id/search_src_text",
            SEARCH_LIST_ITEM_TITLE = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_BY_NAME_AND_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']/following::android.widget.TextView[@text='{DESCRIPTION}']"
                    ;

    public SearchPageObject (AppiumDriver driver){

        super(driver);
    }

    /*TEMPLATES METHODS*/

    private static String getResultSearchElement (String substring) {

        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public static String getTitleAndDescriptionOfElement (String title, String description) {

        return SEARCH_BY_NAME_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    /*TEMPLATES METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element",5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear () {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear () {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }


    public void typeSearchLine(String search_line) {

        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 10);
    }

    public void waitForSearchResult (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring " + substring);
    }

    public void waitForSearchResult () {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),"Cannot find search result with substring " + SEARCH_RESULT_ELEMENT);
    }

    public void clickByArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request " + SEARCH_RESULT_ELEMENT,
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel () {

        this.waitForElementPresent(
                        By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                        "Cannot find empty result label by request " + SEARCH_EMPTY_RESULT_ELEMENT,
                        15);
    }

    public void assertThereIsNoResultOfSearch () {

        this.assertsElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results"
        );
    }

    public void assertTextInSearchLine (String search_text) {

        this.assertElementHasText(
                By.id(SEARCH_LINE),
                search_text,
                "You see the unexpected value of the text attribute"
        );
    }

    public void clearTextFromSearchLine () {

        this.waitForElementAndClear(
                By.id(SEARCH_LINE),
                "You see the unexpected value of the text attribute",
                5
        );
    }

    public void checkContainTextInSearchResult (String search_word) {

        this.getElements(
                By.xpath(SEARCH_LIST_ITEM_TITLE),
                search_word);

    }

    public void waitForElementByTitleAndDescription(String title, String description) {

        String xpath_with_title_and_description = getTitleAndDescriptionOfElement(title,description);
        this.waitForElementPresent(By.xpath(xpath_with_title_and_description),"Cannot find result with title " + title + " and description " + description, 10);

    }
}
