package tests.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamApp {
    AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void arabamSetUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PIXEL");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability("appPackage","com.dogan.arabam");//hangı uygulama uzerınde calısmak ıstıyorsak
        // o uygulamaa ait appPackage degeri yanı uygulamanın kimlik bilgisi
        desiredCapabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub") , desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void arabamTest() throws InterruptedException {
      //  driver.activateApp("com.dogan.arabam");
        // uygulamanın basarılı bir sekılde yuklendıgı doğrulanır
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        //uygulamanın basarılı bir sekılde acıldıgını dogrulayın
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());

        // alt menuden ilan ara butonuna tıkla
        driver.findElementByXPath("//*[@text='İlan Ara']").click();
        
        //kategori olarak otomobıl secılır
      //  driver.findElementByXPath("//*[@text='Otomobil']").click();// tıklama yapmak için klasık yontem
        Thread.sleep(2000);
        TouchAction action=new TouchAction<>(driver);
        action.press(PointOption.point(985,632)).release().perform();//tıklama yapmanın dıger yontemı
        Thread.sleep(1000);

        // arac olarak Volswagen secılır
      //  action.press(PointOption.point(519,1952))
            //    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
            //   .moveTo(PointOption.point(441,344))
            //   .release()
            //    .perform();

        for (int i = 0; i < 5; i++) {// 4 kere kaydırma yapması için yetmıyorsa 5 e cıkar
            action.press(PointOption.point(519,1952))//kaydırma yapmak ıcın ekranda belırlenecek ilk nokta
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))// baslangıc ıle bıtıs arasındakı hızı belırleen sure eger
                    // sure kısalırsa daha fazla yol kat edılır.yanı ekranda asagıya dogru daha hızlı bır hareket gerceklesır.
                    // eger sure uzarsa daha az yol katedılır yanı ekranda asagıa dogru daha yavas bır hareket gerceklesır
                    .moveTo(PointOption.point(441,344))
                    .release()// ekrandan parmak kaldırılır
                    .perform();
            Thread.sleep(500);
            
        }

        driver.findElementByXPath("//*[@text='Volkswagen']").click();

        //arac markası olarak passat secılır
        driver.findElementByXPath("//*[@text='Passat']").click();

        // 1.4 TSI BlueMotion secılır
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();
        // paket secımı yapılır
        Thread.sleep(1000);
        action.press(PointOption.point(506,562)).release().perform();
        // ucuzdan pahalıya sıralama yaparak fıltreleme yapılır
        Thread.sleep(2000);

        driver.findElementByXPath("/*[@text='Sıralama']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("/*[@text='Fiyat - Ucuzdan Pahalıya']").click();


        //gelen en ucuz aracın 500.000 tlden buyuk oldugu dogrulanır
      AndroidElement enUcuzAracFiyatiElementi=driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]");
     String aracinSonFiyati= enUcuzAracFiyatiElementi.getText();
        System.out.println(aracinSonFiyati);// 670.000 TL
       // aracınSonFiyatı=aracınSonFiyatı.replaceAll(".","").replaceAll(" TL","");
      //  System.out.println(aracınSonFiyatı);// 1.yol dınamik olmayan

        aracinSonFiyati=aracinSonFiyati.replaceAll("\\D","");
        System.out.println(aracinSonFiyati);// 2.yol dinamık olan

        Assert.assertTrue(Integer.parseInt(aracinSonFiyati)>500000);

    }

}
