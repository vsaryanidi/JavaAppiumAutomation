package tests.IOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends iOSTestCase {

    @Test
    public void testPassTroughWelcome() {

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
