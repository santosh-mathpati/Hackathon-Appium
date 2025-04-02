import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Point;


public class apiDemoapp2 {
    @Test
    public void LaunchApk() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp("C:\\Users\\USER\\Downloads\\ApiDemos-debug.apk");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");
        Thread.sleep(3000);
        //Long press:
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        System.out.println("Clicked on view button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]\n")));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Expandable Lists\"]")).click();
        System.out.println("Clicked on expandable list");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
        System.out.println("Clicked on custom adapter");
        Thread.sleep(3000);
        WebElement catNamesElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Cat Names\"]"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(catNamesElement)
                .pause(Duration.ofSeconds(2))
                .release()
                .perform();
        System.out.println("Long pressed on cat names");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Sample action\"]")).click();
        System.out.println("Clicked on sample action");
        Thread.sleep(3000);
        for (int i = 0; i < 3; i++) {
            driver.navigate().back();
            Thread.sleep(1000); // Optional: Add a short delay to prevent issues
        }
        //Scroll
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        System.out.println("Clicked on view button");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"WebView3\"))"));
        System.out.println("Scrolled to webview3");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).flingBackward().scrollIntoView(new UiSelector().text(\"Animation\"))")).click();
        System.out.println("Scrolled to animation and clicked on Animation");
        Thread.sleep(3000);
        driver.navigate().back();
        driver.navigate().back();


        //Swipe
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        System.out.println("Clicked on view button");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Gallery']")).click();
        System.out.println("Clicked on Gallery button");
        Thread.sleep(3000);
        // Click on Photos
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
        // Perform horizontal scroll
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollForward(5)"
        ));
        System.out.println("Scrolled horizontally in Photos");
        Thread.sleep(3000);
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();

        // Drag and Drop:
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        System.out.println("Clicked on view button");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
        System.out.println("Clicked on drag and drop button");
        Thread.sleep(3000);
        WebElement source = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
        WebElement destination = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_2\"]"));
        Point sourceElementCenter = getCenter(source);
        Point targetElementCenter = getCenter(destination);

        //PointerInput class to create a sequence of actions
        //that move the pointer to the center of the element,
        //press down on the element, and then release the element.


        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        //Sequence object, which is a list of actions that will be performed on the device

        Sequence sequence = new Sequence(finger1, 1)

                //move finger to the starting position
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))


                //finger coming down to contact with screen
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))

                .addAction(new Pause(finger1, Duration.ofMillis(588)))

                //move finger to the end position
                .addAction(finger1.createPointerMove(Duration.ofMillis(588), PointerInput.Origin.viewport(), targetElementCenter))

                //move the finger up
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        driver.perform(Arrays.asList(sequence));
        Thread.sleep(2000);
        System.out.println("Drag and drop action completed");
        driver.quit();


    }
    private static Point getCenter(WebElement element) {
        //get location of the element
        Point location = element.getLocation();

        //get dimension (height & width of the element)
        Dimension size = element.getSize();

        //center point
        Point center = new Point(location.x + size.width / 2, location.y + size.height / 2);

        return center;


    }

    //tap
    @Test
    public void LaunchApk1() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp("C:\\Users\\USER\\Downloads\\ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until "Animation" element is visible and click on it
        var animationElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='Animation']")));

        animationElement.click(); // Click on Animation button
        System.out.println("Clicked on Animation button");

        // Wait for "View Flip" element
        var viewFlipElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='View Flip']")));

        // Tap on View Flip using coordinates
        tapOnElement(driver, viewFlipElement);
        System.out.println("Tapped on View Flip button");
        Thread.sleep(2000);
        driver.findElement(AppiumBy.accessibilityId("Flip")).click();
        System.out.println("Clicked on Flip button");
        Thread.sleep(3000);


        driver.quit();
    }

    // Simple function to tap on an element using its center coordinates
    private void tapOnElement(AndroidDriver driver, org.openqa.selenium.WebElement element) {
        Point location = element.getLocation();
        int x = location.getX() + (element.getSize().getWidth() / 2);
        int y = location.getY() + (element.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    //Alert Handling Automation
    //Handling Simple Alert:
    @Test
    public void LaunchApk2() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp("C:\\Users\\USER\\Downloads\\ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"App\"]")).click();
        System.out.println("Clicked on app button");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")).click();
        System.out.println("Clicked on alert dialogs button");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with a message")).click();
        System.out.println("Clicked on OK Cancel dialog with a message");
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();
        System.out.println("Alert accepted");
        Thread.sleep(3000);

        //Handling single choice alert:
        //clicking on single choice list button
        driver.findElement(AppiumBy.accessibilityId("Single choice list")).click();
        System.out.println("Clicked on single choice list");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var streetViewOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.CheckedTextView[@text='Street view']")));

        streetViewOption.click();
        System.out.println("Selected 'Street view' radio button");

    // Click "OK" to confirm the selection
        var okButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.Button[@text='OK']")));

        okButton.click();
        System.out.println("Clicked OK button");
        Thread.sleep(3000);
        //Click on Text Entry Dialog
        driver.findElement(AppiumBy.accessibilityId("Text Entry dialog")).click();
        System.out.println("Clicked on Text Entry dialog");
        Thread.sleep(3000);
        WebElement username = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/username_edit\"]"));
        username.sendKeys("santosh");
        System.out.println("Entered username");
        Thread.sleep(3000);
        WebElement password = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/password_edit\"]"));
        password.sendKeys("hshs@122");
        System.out.println("Entered password");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
        System.out.println("Clicked on OK button");
        Thread.sleep(3000);
        //Click on Repeat Alarm
        driver.findElement(AppiumBy.accessibilityId("Repeat alarm")).click();
        System.out.println("Clicked on Repeat alarm");
        Thread.sleep(3000);
        String[] checkDays = {"Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday"};
        String[] uncheckDays = {"Every Saturday", "Every Sunday"};

// Check Monday to Friday
        for (String day : checkDays) {
            WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='" + day + "']"));
            if (element.getAttribute("checked").equals("false")) { // If not checked, click to check
                element.click();
                System.out.println("Checked: " + day);
            }
        }

// Uncheck Saturday and Sunday
        for (String day : uncheckDays) {
            WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.CheckedTextView[@text='" + day + "']"));
            if (element.getAttribute("checked").equals("true")) { // If checked, click to uncheck
                element.click();
                System.out.println("Unchecked: " + day);
            }
        }

// Click "OK" button to confirm selection
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
        System.out.println("Clicked OK button");
        driver.quit();

    }


}