package ObjectPages;

import commonTests.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Home extends BaseTest {

    private WebElement HeaderRegion()
    {
        return driver.findElement(By.id("regionHeader"));
    }
    public WebElement MainRegion()
    {
        return driver.findElement(By.id("MainContent"));
    }


    private WebElement TenDayForView()
    {
        return HeaderRegion().findElement(By.xpath("div[3]")).findElement(By.linkText("10 Day"))
                .findElement(By.tagName("span"));
    }

    private WebElement getLocationSearchTxt()
    {
        return findElementByXpath(null, "//*[@id='LocationSearch_input' and @data-testid='searchModalInputBox']");
//        return driver.findElement(By.xpath("//*[@id='LocationSearch_input' and @data-testid='searchModalInputBox']"));
    }

    private WebElement LocationSearch_listbox()
    {
        return driver.findElement(By.id("LocationSearch_listbox"));
    }
    private WebElement SearchReulst_popUp()
    {
//        return findElementByXpath(LocationSearch_listbox1(), "..");
        return driver.findElement(By.xpath("//div[@role = 'alert' and contains(@class, 'SearchResults--open')]"));
    }
    private List<WebElement> searchedLocation()
    {
        return LocationSearch_listbox().findElements(By.tagName("div"));
    }

    public TenDayWeatherView getTenDayWeatherView()
    {
        return new TenDayWeatherView();
    }

    public void makeASearch(String searchPhrase){
        waitForVisibilityWithExplicitWait(TenDayForView());
        sendKeys(getLocationSearchTxt(),searchPhrase);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("LocationSearch_listbox-0")).click();

    }

    public void clickOnSuggestedSearchList(int index)
    {
        clickWithHelpOfJSExecution(searchedLocation().get(index));
    }

    public void clickOn10DayLink()
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", TenDayForView());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWeatherInfoOfADay(int indexOfDayInTheList)
    {
        getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).openDetailSession();
        List<String> info = new ArrayList<String>();
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDay());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayConditionsSummary());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayTemperature());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayPercentage());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayWind());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayHumidity());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDayUVindex());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDaySunrise());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getDaySunset());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightConditionsSummary());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightTemperature());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightPercentage());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightWind());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightHumidity());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getNightUVindex());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getMoonSunrise());
        info.add(getTenDayWeatherView().getWeatherDay(indexOfDayInTheList).getMoonSunset());
        return info;
    }

    public List<List<String>> getWeatherInfoOfAllDay()
    {
        List<List<String>> allInfo = new ArrayList<List<String>>();

        DetailSession detailSession = getTenDayWeatherView().getWeatherDay(0);
        int noOfDiv =  driver.findElements(By.cssSelector("#detailIndex0 > div > div")).size();

        if(noOfDiv < 4)
        {
            List<String> info = new ArrayList<String>();
            String dayPart = driver.findElement(By.xpath("//*[@id='detailIndex0']/div/div[1]/h3")).getText();
            if(dayPart.contains("Day"))
            {
                info.add(detailSession.getDay());
                info.add(detailSession.getDayConditionsSummary());
                info.add(detailSession.getDayTemperature());
                info.add(detailSession.getDayPercentage());
                info.add(detailSession.getDayWind());
                info.add(detailSession.getDayHumidity());
                info.add(detailSession.getDayUVindex());
                info.add(detailSession.getDaySunrise());
                info.add(detailSession.getDaySunset());
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");

            }
            else
            {
                info.add(detailSession.getDay());
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add("");
                info.add(detailSession.getDayConditionsSummary());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDayTemperature());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDayPercentage());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDayWind());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDayHumidity());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDayUVindex());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDaySunrise());
                info.add(getTenDayWeatherView().getWeatherDay(0).getDaySunset());
            }
            allInfo.add(info);
        }
        else
        {
            allInfo.add(getWeatherInfoOfADay(0));
        }

        //
        int size = getTenDayWeatherView().getListOfWeatherDay().size();
        for(int i=1; i< size; i++)
        {
            allInfo.add(getWeatherInfoOfADay(i));
        }
        return allInfo;
    }
}
