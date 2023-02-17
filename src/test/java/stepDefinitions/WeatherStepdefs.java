package stepDefinitions;
import ObjectPages.Home;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.TestUtils;

import java.io.IOException;
import java.util.List;

public class WeatherStepdefs{

    Home home = new Home();

    @Given("The site is opened")
    public void theSiteHttpsWeatherComIsOpened() {
        home.openURL();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("Click on 10 day link")
    public void clickOn10DayLink() {
        home.clickOn10DayLink();
    }

    @Then("Written the weather info into a file")
    public void writtenTheWeatherInfoIntoAFile() throws IOException {
        TestUtils.writeExcel(TestUtils.excelFileFolder
                ,"weatherInfo.xlsx","10days",3, home.getWeatherInfoOfAllDay());
    }

    @And("Choose the suggested item at index")
    public void chooseTheSuggestedItemAtIndex(DataTable table) {
        List<String> list = table.asList(String.class);
        home.clickOnSuggestedSearchList(Integer.parseInt(list.get(0)));
    }

    @And("Making a search with keyword is {string}")
    public void makingASearchWithKeywordIs(String searchPhrase) {
        home.makeASearch(searchPhrase);
    }
    @And("Making a search with keyword is {string} new")
    public void makingASearchWithKeywordIsnew(String searchPhrase) {
        home.makeASearch(searchPhrase);
    }
    @Then("Total of days that listed with weather information is {int}")
    public void totalOfDaysThatListedWithWeatherInformationIs(int arg0) {
        int actual = home.getTenDayWeatherView().getListOfWeatherDay().size();
        Assert.assertEquals(actual, 10, "List of detail weather day is " + actual+ " but expected is " + 10);
    }

    @And("Click on ArrownDown icon of the {string} day")
    public void clickOnArrownDownIconOfTheDay(String arg0) {
        int index= Integer.parseInt(arg0.substring(0,arg0.length()-2));
        home.getTenDayWeatherView().getListOfWeatherDay().get(index-1).clickOnArrowIcon();
    }

    @And("Click on ArrownUp icon of the {string} day")
    public void clickOnArrownUpIconOfTheDay(String arg0) {
        int index= Integer.parseInt(arg0.substring(0,arg0.length()-2));
        home.getTenDayWeatherView().getListOfWeatherDay().get(index-1).clickOnArrowIcon();
    }

    @Then("The detail weather of the {string} day is shown")
    public void theDetailWeatherOfTheDayIsShown(String arg0) {
        int index= Integer.parseInt(arg0.substring(0,arg0.length()-2));
        boolean actual = home.getTenDayWeatherView().getListOfWeatherDay().get(index-1).Summary().getAttribute("class")
                .contains("ShowOpenSummaryContainer");
        Assert.assertEquals(actual, true, "Detail weather is not shown");
    }

    @Then("The detail weather of the {string} day is hidden")
    public void theDetailWeatherOfTheDayIsHidden(String arg0) {
        int index= Integer.parseInt(arg0.substring(0,arg0.length()-2));
        boolean actual = home.getTenDayWeatherView().getListOfWeatherDay().get(index-1).Summary().getAttribute("class")
                .contains("ShowOpenSummaryContainer");
        Assert.assertEquals(actual, false, "Detail weather is not hidden");
    }

    @Then("The weather info is changed with title is not {string}")
    public void theWeatherInfoIsChangedWithTitleIsNotFor(String arg0) {
        boolean actual = !home.MainRegion().findElement(By.xpath("div[2]/main/div[1]/section/h1")).getText().contains(arg0);
        Assert.assertEquals(actual, true, "The display is not changed for new location");
    }

}
