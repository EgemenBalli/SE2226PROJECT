import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class FitYemek {
    ChromeOptions chromeOptions;
    ChromeDriver driver;

    WebElement textBoxYas;
    WebElement textBoxBoy;
    WebElement textBoxKilo;
    WebElement kalori;
    WebElement protein;
    WebElement karbonhidrat;
    WebElement yag;

    public FitYemek(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
    }

    void connect(){
        driver.get("https://fityemek.com/kalori-ihtiyacini-hesapla/");
        textBoxYas = driver.findElement(By.id("fieldname4_1"));
        textBoxBoy = driver.findElement(By.id("fieldname5_1"));
        textBoxKilo = driver.findElement(By.id("fieldname6_1"));
        kalori = driver.findElement(By.id("fieldname1_1"));
        protein = driver.findElement(By.id("fieldname10_1"));
        karbonhidrat = driver.findElement(By.id("fieldname11_1"));
        yag = driver.findElement(By.id("fieldname12_1"));
    }

    public String yasInput(String yas){
        textBoxYas.clear();
        textBoxYas.sendKeys(yas);
        kalori.click();

        if(yas.equals("")){
            return driver.findElement(By.id("fieldname4_1-error")).getText();
        }else{
                int yasAsInteger = Integer.parseInt(yas);

                if (yasAsInteger < 1 || yasAsInteger > 100){
                    return driver.findElement(By.id("fieldname4_1-error")).getText();
                }
        }

        return "No Errors Occured.";
    }

    public String boyInput(String boy){
        textBoxBoy.clear();
        textBoxBoy.sendKeys(boy);
        kalori.click();

        if(boy.equals("")){
            return driver.findElement(By.id("fieldname5_1-error")).getText();
        }else{
            int boyAsInteger = Integer.parseInt(boy);

            if (boyAsInteger < 10 || boyAsInteger > 300){
                return driver.findElement(By.id("fieldname5_1-error")).getText();
            }
        }

        return "No Errors Occured.";
    }

    public String kiloInput(String kilo){
        textBoxKilo.clear();
        textBoxKilo.sendKeys(kilo);
        kalori.click();

        if(kilo.equals("")){
            return driver.findElement(By.id("fieldname6_1-error")).getText();
        }else{
            int kiloAsInteger = Integer.parseInt(kilo);

            if (kiloAsInteger < 10 || kiloAsInteger > 300){
                return driver.findElement(By.id("fieldname6_1-error")).getText();
            }
        }

        return "No Errors Occured.";
    }

    String calculateKalori(String cinsiyet, String yas, String boy, String kilo, String hareketSeviyesi, String hedef){
        new Actions(driver).scrollToElement(textBoxYas).perform();

        if (cinsiyet.equals("Erkek") && !driver.findElement(By.id("fieldname3_1_rb0")).isSelected()){
            driver.findElement(By.id("fieldname3_1_rb0")).click();
        }else if (cinsiyet.equals("Kadin") && !driver.findElement(By.id("fieldname3_1_rb1")).isSelected()){
            driver.findElement(By.id("fieldname3_1_rb1")).click();
        }

        textBoxYas.clear();
        textBoxYas.sendKeys(yas);
        textBoxBoy.clear();
        textBoxBoy.sendKeys(boy);
        textBoxKilo.clear();
        textBoxKilo.sendKeys(kilo);

        switch (hareketSeviyesi){
            case "Sedanter":
                if(!driver.findElement(By.id("fieldname7_1_rb0")).isSelected()){
                    driver.findElement(By.id("fieldname7_1_rb0")).click();
                }
                break;
            case "Az hareketli":
                if(!driver.findElement(By.id("fieldname7_1_rb1")).isSelected()){
                    driver.findElement(By.id("fieldname7_1_rb1")).click();
                }
                break;
            case "Orta derece hareketli":
                if(!driver.findElement(By.id("fieldname7_1_rb2")).isSelected()){
                    driver.findElement(By.id("fieldname7_1_rb2")).click();
                }
                break;
            case "Cok hareketli":
                if(!driver.findElement(By.id("fieldname7_1_rb3")).isSelected()){
                    driver.findElement(By.id("fieldname7_1_rb3")).click();
                }
                break;
            case "Asiri hareketli":
                if(!driver.findElement(By.id("fieldname7_1_rb4")).isSelected()){
                    driver.findElement(By.id("fieldname7_1_rb4")).click();
                }
                break;
            default:
                break;
        }

        switch (hedef){
            case "Zayiflamak":
                if(!driver.findElement(By.id("fieldname8_1_rb0")).isSelected()){
                    driver.findElement(By.id("fieldname8_1_rb0")).click();
                }
                break;
            case "Kilo almak":
                if(!driver.findElement(By.id("fieldname8_1_rb1")).isSelected()){
                    driver.findElement(By.id("fieldname8_1_rb1")).click();
                }
                break;
            case "Hizli kilo almak":
                if(!driver.findElement(By.id("fieldname8_1_rb2")).isSelected()){
                    driver.findElement(By.id("fieldname8_1_rb2")).click();
                }
                break;
            case "Kilomu korumak":
                if(!driver.findElement(By.id("fieldname8_1_rb3")).isSelected()){
                    driver.findElement(By.id("fieldname8_1_rb3")).click();
                }
                break;
            default:
                break;
        }

        return "Kalori: " + kalori.getAttribute("value") + "   Protein: " + protein.getAttribute("value") + "   Karbonhidrat: " + karbonhidrat.getAttribute("value") + "   Yag: " + yag.getAttribute("value");
    }

    void quit(){
        driver.quit();
    }
}
