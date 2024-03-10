package tests.day01;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HesapMakinesi {
     AndroidDriver<AndroidElement> driver;//android cıhazlardaki işlemleri yapabilmemizi saglayan driver objesi
   // AndroidDriver<MobileElement> driver2;//android cıhazlardaki işlemleri yapabilmemizi saglayan driver objesi
    // IOSDriver<IOSElement> iosDriver;// ıos cıhazlardakı işlemlerı yapabilmemızı saglayan driver objesı
    // AppiumDriver<MobileElement> appiumDriver;// her ıkı platformda da işlemleri yapabilmemızı saglayan driver objesı
    @Test
    public void ilkHesapAppTesti() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
       // capabilities.setCapability("deviceName","PIXEL");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        /*
        UiAutomator 2 otomasyon ismi sadece android 6 ve androıd 6 dan yuksek olan android sistemlerı için çalışır
         UiAutomator otomasyon ismi sadece android 6 ve androıd 6  dan düşük olan android sistemlerı için çalışır

         */


       driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }
}
