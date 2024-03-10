package tests.day03;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Driver;
import utils.ResuableMethods;

import java.io.File;
import java.io.IOException;

public class AllCurrencyConverterApp {

    AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();


    @Test
    public void allCurrencyTest() throws InterruptedException, IOException {
        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));
// uygulamanin acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='CURRENCY CONVERTER']").isDisplayed());
// cevirmek istedigimiz para birimi zloty olarak secilir
        //435,347
        ResuableMethods.koordinatTiklamaMethodu(435,347,1500);
        ResuableMethods.scrollWithUiScrollable("Polish Zloty");



// cevirelecek olan para birimi Tl olarak secilir
        ResuableMethods.koordinatTiklamaMethodu(424,482,1500);
        ResuableMethods.scrollWithUiScrollable("Turkish Lira");
// cevrilen tutar screenShot olarak kaydedilir
        ResuableMethods.scrollWithUiScrollable("1");
        ResuableMethods.scrollWithUiScrollable("5");
        ResuableMethods.scrollWithUiScrollable("3");
        ResuableMethods.scrollWithUiScrollable("8");
    /*
        File file=driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("ExchangeRateForZlotyToTl.jpg"));

     */
        ResuableMethods.getScreenshot("nevzat");

// Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        AndroidElement exchangeResult= driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB");
        String sonucText=exchangeResult.getText();
// kullaniciya sms olarak bildirilir
        driver.sendSMS("555555555555555","Cevirmek istediginiz para biriminizin sonucu = "+sonucText);
    }
}