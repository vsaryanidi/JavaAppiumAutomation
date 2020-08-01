package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase {

    @Test
    public void testPassTroughWelcome() {

        if (this.Platform.isAndroid()) {
            return;
        }

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForForNewWayExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPrefferedLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollected();
        welcomePageObject.clickGetStartedButton();
    }
}