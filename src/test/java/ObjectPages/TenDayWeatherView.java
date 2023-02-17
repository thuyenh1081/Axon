package ObjectPages;

import commonTests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class TenDayWeatherView extends BaseTest {

    private List<DetailSession> ListOfWeatherDay = new ArrayList<DetailSession>();
    public WebElement TenDayWeatherView()
    {
        return this.driver.findElement(By.id("MainContent"))
                .findElement(By.xpath(".//section[@data-testid='DailyForecast']"));
    }

    public List<DetailSession> getListOfWeatherDay()
    {
        int size = getListOfWeatherRow().size();
        for(int i=0; i< size ;i++ )
        {
            ListOfWeatherDay.add(getWeatherDay(i));
        }

        return ListOfWeatherDay;
    }

    DetailSession getWeatherDay(int indexOfRow)
    {
        return new DetailSession(getListOfWeatherRow().get(indexOfRow).getAttribute("id"));
    }

    private List<WebElement > getListOfWeatherRow()
    {
        return TenDayWeatherView().findElement(By.xpath("div[2]")).findElements(By.tagName("details"));

    }
}
