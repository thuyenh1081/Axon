package ObjectPages;

import commonTests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class DetailSession extends BaseTest {
    private String idOfparent;
    DetailSession(String idOfDetailSecction)
    {
        idOfparent = idOfDetailSecction;
    }

    public WebElement DetailSession()
    {
        return driver.findElement(By.id(idOfparent));
    }

    public WebElement Summary()
    {
        return findElementByXpath(DetailSession(), "summary");
    }
    public WebElement DropIcon()
    {
        return findElementByCssSelector(DetailSession(), "summary > div > svg");
    }
    public WebElement Day()
    {
        return findElementByXpath(DetailSession(), "div/div[1]/h3/span");
    }
    public WebElement DayTemperature()
    {
        return findElementByXpath(DetailSession(), "div/div[1]/div/div[1]/span");
    }
    public WebElement DayPercentage()
    {
        return findElementByXpath(DetailSession(), "div/div[1]/div/div[3]/div[1]/span");
    }
    public WebElement DayWind()
    {
        return findElementByXpath(DetailSession(), "div/div[1]/div/div[3]/div[2]/span");
    }

   public WebElement DayConditionsSummary()
   {
       return findElementByCssSelector(DetailSession(), "div > div:nth-child(1) > p");
   }

    public WebElement DayHumidity()
    {
        return findElementByXpath(DetailSession(), "div/div[2]/ul/li[1]/div/span[2]");
    }
    public WebElement DayUVindex()
    {
        return findElementByXpath(DetailSession(), "div/div[2]/ul/li[2]/div/span[2]");
    }
    public WebElement DaySunrise()
    {
        return findElementByXpath(DetailSession(), "div/div[2]/ul/li[3]/div/span[2]");
    }
    public WebElement DaySunset()
    {
        return findElementByXpath(DetailSession(), "div/div[2]/ul/li[4]/div/span[2]");
    }

    public WebElement NightTemperature()
    {
        return findElementByXpath(DetailSession(), "div/div[3]/div/div[1]/span");
    }
    public WebElement NightPercentage()
    {
        return findElementByXpath(DetailSession(), "div/div[3]/div/div[3]/div[1]/span");
    }
    public WebElement NightWind()
    {
        return findElementByXpath(DetailSession(), "div/div[3]/div/div[3]/div[2]/span");
    }
    public WebElement NightConditionsSummary()
    {
        return findElementByCssSelector(DetailSession(), "div > div:nth-child(3) > p");
    }

    public WebElement NightHumidity()
    {
        return findElementByXpath(DetailSession(), "div/div[4]/ul/li[1]/div/span[2]");
    }
    public WebElement NightUVindex()
    {
        return findElementByXpath(DetailSession(), "div/div[4]/ul/li[2]/div/span[2]");
    }
    public WebElement MoonSunrise()
    {
        return findElementByXpath(DetailSession(), "div/div[4]/ul/li[3]/div/span[2]");
    }
    public WebElement MoonSunset()
    {
        return findElementByXpath(DetailSession(), "div/div[4]/ul/li[4]/div/span[2]");
    }
    public String getDay()
    {
        return  getText(Day());
    }
    public String getDayTemperature()
    {
        return  getText(DayTemperature());
    }
    public String getDayPercentage()
    {
        return  getText(DayPercentage());
    }
    public String getDayWind()
    {
        return  getText(DayWind());
    }
    public String getDayConditionsSummary()
    {
        return  getText(DayConditionsSummary());
    }
    public String getDayHumidity()
    {
        return  getText(DayHumidity());
    }
    public String getDayUVindex()
    {
        return  getText(DayUVindex());
    }
    public String getDaySunrise()
    {
        return  getText(DaySunrise());
    }
    public String getDaySunset()
    {
        return  getText(DaySunset());
    }
    public String getNightTemperature()
    {
        return  getText(NightTemperature());
    }
    public String getNightPercentage()
    {
        return  getText(NightPercentage());
    }
    public String getNightWind()
    {
        return  getText(NightWind());
    }
    public String getNightConditionsSummary()
    {
        return  getText(NightConditionsSummary());
    }
    public String getNightHumidity()
    {
        return  getText(NightHumidity());
    }
    public String getNightUVindex()
    {
        return  getText(NightUVindex());
    }
    public String getMoonSunrise()
    {
        return  getText(MoonSunrise());
    }
    public String getMoonSunset()
    {
        return  getText(MoonSunset());
    }

    public void clickOnArrowIcon()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", DropIcon());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DropIcon().click();
    }

    public void openDetailSession()
    {
        if(Summary().getAttribute("class").toString().contains("positionShowOpenSummaryContainer") == false)
        {
            clickOnArrowIcon();
        }

    }
}
