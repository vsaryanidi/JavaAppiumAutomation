package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String

    STEP_LEARN_MORE_LINK="//XCUIElementTypeStaticText[@name=\"Learn more about Wikipedia\"]",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT="New ways to explore",
    STEP_ADD_OR_PREFERRED_LANG_LINK="//XCUIElementTypeStaticText[@name=\"Add or edit preferred languages\"]",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED="//XCUIElementTypeStaticText[@name=\"Learn more about data collected\"]",
    NEXT_LINK="//XCUIElementTypeStaticText[@name=\"Next\"]",
    GET_STARTED_BUTTON="//XCUIElementTypeStaticText[@name=\"Get started\"]"
    ;

    public WelcomePageObject(AppiumDriver driver) {

        super(driver);
    }


    public void waitForLearnMoreLink() {

        this.waitForElementPresent(
                By.xpath(STEP_LEARN_MORE_LINK),
                "Cannot find 'Learn more about Wikipedia' link",
                10);

        System.out.println(STEP_LEARN_MORE_LINK);

    }


    public void clickNextButton() {

        int size = driver.findElements(By.xpath(NEXT_LINK)).size();
        System.out.println(size);

        this.waitForElementAndClick(
                By.xpath(NEXT_LINK),
                "Cannot find and click button 'Next'",
                10);

    }

    public void waitForForNewWayExploreText() {

        this.waitForElementPresent(
                By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT),
                "Cannot find 'New ways to explore' link",
                10);

    }

    public void waitForAddOrEditPrefferedLangText() {

        this.waitForElementPresent(
                By.xpath(STEP_ADD_OR_PREFERRED_LANG_LINK),
                "Cannot find 'Add or edit preferred languages' link",
                10);

    }

    public void waitForLearnMoreAboutDataCollected() {

        this.waitForElementPresent(
                By.xpath(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED),
                "Cannot find 'Learn more about data collected' link",
                10);

    }

    public void clickGetStartedButton() {

        this.waitForElementAndClick(
                By.xpath(GET_STARTED_BUTTON),
                "Cannot find and click button 'Get started'",
                10);

    }
}