package SimplifyQACodeeditor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.simplifyqa.sqadrivers.webdriver;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.simplifyqa.Utility.InitializeDependence;
import com.simplifyqa.method.GeneralMethod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.support.Color;

public class CustomMethods {
    GeneralMethod gm = new GeneralMethod();

    public boolean checktillpmexists(String PMvalue) {

        try {

            String NextbuttonID = webdriver.getElementId("xpath", "//button[@aria-label='Go to next page']");
            boolean Nextbutton = webdriver.isElementenabled(NextbuttonID);
            boolean flag = true;
            while (flag) {

                int count = webdriver
                        .findElements("xpath", "//tr[@class='mx-name-index-1']//div[text()='" + PMvalue + "']")
                        .length();

                if (count == 1) {

                    flag = false;
                    Thread.sleep(200);
                    webdriver.click("xpath", "(//div[text()='" + PMvalue + "'])[1]");

                } else {
                    webdriver.click("xpath", "//button[@aria-label='Go to next page']");
                }

            }
            return true;
        } catch (Exception e) {
            System.out.println("PM is Not Present in any page");
            return false;
        }

    }

    public boolean ValidateLevel2TaskErrorMessage() {

        try {

            Boolean LoadMoreCount = true;

            while (LoadMoreCount) {

                int LoadmoreCount = webdriver
                        .findElements("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span").length();

                if (LoadmoreCount == 1) {
                    Thread.sleep(500);
                    webdriver.scrollIntoView("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span");
                    Thread.sleep(500);
                    webdriver.click("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span");
                    Thread.sleep(500);

                } else {
                    LoadMoreCount = false;
                    System.out.println("No load More Button in the screen");
                }

            }

            int ScoreCardCount = webdriver.findElements("xpath",
                    "//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default']")
                    .length();
            ArrayList<String> ScoreCardsValues = new ArrayList<String>();
            for (int i = 0; i < ScoreCardCount; i++) {
                String ScoreCards = webdriver.getElementId("xpath",
                        "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])["
                                + (i + 1) + "]");
                String ScoreCardsValues1 = webdriver.getElementproperty(ScoreCards, "innerText");
                ScoreCardsValues.add(i, ScoreCardsValues1);
            }

            webdriver.click("xpath",
                    "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'limit-content-list milestone-sc-list btn-default')])[1]");
            Thread.sleep(2000);

            for (int i = 1; i <= ScoreCardsValues.size(); i++) {

                webdriver.selectitemfromdropdown(ScoreCardsValues.get(i));
                Thread.sleep(2000);
                int ErrorMsgCount = webdriver
                        .findElements("xpath",
                                "//div[text()='This Scorecard Milestone is already associated to the other Task.']")
                        .length();
                if (ErrorMsgCount == 1) {
                    System.out.println("Error Message Validation is passed for:" + i + "value");

                } else {
                    System.out.println("Error Message Validation is failed for:" + i + "value");
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean ProjectOngoingtoCompletedStatus() {
        try {

            Boolean LoadMoreCount = true;

            while (LoadMoreCount) {

                int LoadmoreCount = webdriver
                        .findElements("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span").length();

                if (LoadmoreCount == 1) {
                    Thread.sleep(500);
                    webdriver.scrollIntoView("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span");
                    Thread.sleep(500);
                    webdriver.click("xpath", "//button[@class='btn mx-button mx-listview-loadMore']/span");
                    Thread.sleep(500);

                } else {
                    LoadMoreCount = false;
                    System.out.println("No load More Button in the screen");
                }

            }

            int UpArrowIcon = webdriver
                    .findElements("xpath", "//img[@class='mx-image mx-name-image24 uparrow1 img-responsive']").length();

            for (int i = 1; i <= UpArrowIcon; i++) {
                Thread.sleep(1000);
                webdriver.click("xpath", "(//img[@class='mx-image mx-name-image24 uparrow1 img-responsive'])[1]");
                System.out.println("Hiding" + i + "Subtask");
                Thread.sleep(1000);
            }
            int ApplicableIcon = webdriver.findElements("xpath",
                    "//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'switchtoggle btn-default')]")
                    .length();
            for (int i = 1; i <= ApplicableIcon; i++) {
                webdriver.click("xpath",
                        "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'switchtoggle')])["
                                + i + "]");
                Thread.sleep(2000);
                webdriver.click("xpath", "//button[text()='Yes']");
                Thread.sleep(2000);
                System.out.println("Disabling applicable button for" + i + "task");
            }
            webdriver.clickusingjs("xpath",
                    "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'switchtoggle')])[1]");
            Thread.sleep(1000);
            webdriver.click("xpath", "//button[text()='Yes']");
            Thread.sleep(1000);
            webdriver.click("xpath",
                    "(//img[contains(@class,'mx-image mx-name-image') and contains(@class,'img-responsive') and contains(@class,'uparrow')])[1]");
            Thread.sleep(2000);
            int ActualStartDateCount = webdriver.findElements("xpath",
                    "//div[contains(@class,'mx-name-container') and contains(@class,'space-list column5 cont-font row-center')]//div[contains(@class,'datewidth')]")
                    .length();

            if (ActualStartDateCount == 0) {
                return false;
            } else {
                for (int i = 2; i <= ActualStartDateCount; i++) {
                    webdriver.click("xpath",
                            "(//div[contains(@class,'mx-name-container') and contains(@class,'space-list column5 cont-font row-center')]//div[contains(@class,'datewidth')])["
                                    + i + "]");
                    Thread.sleep(1000);
                    webdriver.click("xpath", "//span[text()='Actual start date']/../..//input");
                    Thread.sleep(1000);
                    webdriver.click("xpath", "(//div [@class='datepicker-days']//tr//td[@class='day'])[1]");
                    Thread.sleep(1000);
                    webdriver.click("xpath", "//div/button[text()='Change date']");
                }

            }

            int ActualFinishDateCount = webdriver.findElements("xpath",
                    "//div[contains(@class,'mx-name-container') and contains(@class,'space-list column6 cont-font row-center')]//div[contains(@class,'datewidth')]")
                    .length();

            if (ActualFinishDateCount == 0) {
                return false;

            } else {

                for (int i = 2; i <= ActualFinishDateCount; i++) {
                    webdriver.click("xpath", "(//div[@class='mx-name-container624 datewidth'])[1]");
                    Thread.sleep(2000);
                    webdriver.click("xpath", "(//span[text()='Actual finish date']/../..//input)[last()]");
                    Thread.sleep(2000);
                    webdriver.click("xpath", "(//div [@class='datepicker-days']//tr//td[@class='day'])[last()]");
                    Thread.sleep(2000);
                    webdriver.click("xpath", "//div/button[text()='Change date']");
                    Thread.sleep(2000);
                    webdriver.click("xpath", "//div/button[text()='Complete task']");
                    Thread.sleep(2000);
                }

            }

            webdriver.click("xpath", "//td/button[text()='Calculate']");

            return true;
        }

        catch (Exception e) {

            return false;

        }

    }

    public boolean ValidateFilingTaskEndDate(String FilingFinishDate, String FilingLabelTaskFinishDate,
            String FilingphasegateEndDate, String DossierPreparationEndDate) {
        try {
            boolean Contentstatus = true;

            while (Contentstatus) {

                int FilingTaskCount = webdriver.findElements("xpath",
                        "//span[contains(@class,'mx-text mx-name-text') and contains(@class,'text-bold level2taskname1 milestone') and contains(text(),'Filing')]")
                        .length();
                int FilingLabelCount = webdriver.findElements("xpath",
                        "//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,' milestone-Label limit-content btn-sm btn-default') and text()='Filing']")
                        .length();
                if (FilingTaskCount == 1) {
                    Contentstatus = false;
                    if (FilingLabelCount == 1) {

                        webdriver.readtextandstore("xpath",
                                "//span[text()='Filing']//..//..//..//..//button[@class='btn mx-button mx-name-actionButton65 date-content text-bold date-contentM1 btn-default']",
                                FilingFinishDate);
                        webdriver.readtextandstore("xpath",
                                "//span[text()='Dosiser submission to Agency']//..//..//..//..//..//span[@class='mx-text mx-name-text25 date-content date-contentL']",
                                FilingLabelTaskFinishDate);
                        if (FilingFinishDate.equalsIgnoreCase(FilingLabelTaskFinishDate)) {
                            System.out
                                    .println("Filing Finish date is equal to Filing Label Task attached's Finish Date");
                            return true;

                        } else {
                            System.out.println(
                                    "Filing Finish date is not equal to Filing Label Task attached's Finish Date");

                        }

                    } else {
                        webdriver.readtextandstore("xpath",
                                "//span[text()='Filing phase gate ']//..//..//..//..//..//span[@class='mx-text mx-name-text25 date-content date-contentL']",
                                FilingphasegateEndDate);
                        webdriver.readtextandstore("xpath",
                                "//span[text()='Dossier Preparation']//..//..//..//..//..//span[@class='mx-text mx-name-text25 date-content date-contentL']",
                                DossierPreparationEndDate);
                        if (FilingphasegateEndDate.equalsIgnoreCase(DossierPreparationEndDate)) {

                            System.out.println(
                                    "Filing phase gate's Finish Date is equal to Dossier Preparation Finish Date");
                            return true;

                        } else {
                            System.out.println(
                                    "Filing phase gate's Finish Date is not equal to Dossier Preparation Finish Date");

                        }

                    }

                } else {

                    webdriver.click("xpath", "//button[text()='Load more...']");

                }

            }
            return true;
        }

        catch (Exception e) {
            return false;

        }

    }

    public boolean validatescorecarddropdownvalue() {
        try {

            ArrayList<String> ScoreCardDrpdwnValues = new ArrayList<String>();
            boolean LMflag = true;
            while (LMflag) {
                int LoadmoreCount = webdriver
                        .findElements("xpath", "//button[@class='btn mx-button mx-listview-loadMore']").length();
                if (LoadmoreCount == 1) {
                    Thread.sleep(1000);
                    webdriver.scrollIntoView("xpath", "//button[@class='btn mx-button mx-listview-loadMore']");
                    webdriver.click("xpath", "//button[@class='btn mx-button mx-listview-loadMore']");
                } else {
                    int ScoreCardCount = webdriver.findElements("xpath",
                            "//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default']")
                            .length();
                    ArrayList<String> ScoreCardsValues = new ArrayList<String>();
                    for (int i = 0; i < ScoreCardCount; i++) {
                        String ScoreCards = webdriver.getElementId("xpath",
                                "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])["
                                        + (i + 1) + "]");
                        String ScoreCardsValues1 = webdriver.getElementproperty(ScoreCards, "innerText");
                        ScoreCardsValues.add(i, ScoreCardsValues1);
                    }
                    Thread.sleep(2000);
                    webdriver.click("xpath",
                            "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'limit-content-list milestone-sc-list btn-default')])[1]");
                    Thread.sleep(2000);
                    int ScoreDropdownCount = webdriver.findElements("xpath",
                            "//select[contains(@id,'ProjectPlan.EditScoreCardMilestone_Level2Task.referenceSelector')]/option")
                            .length();
                    for (int i = 0; i < ScoreDropdownCount; i++) {
                        String ScoreCardDropdownValues = webdriver.getElementId("xpath",
                                "(//select[contains(@id,'ProjectPlan.EditScoreCardMilestone_Level2Task.referenceSelector')]/option)["
                                        + (i + 1) + "]");
                        String ScoreCardDropdownValues1 = webdriver.getElementproperty(ScoreCardDropdownValues,
                                "innerText");
                        ScoreCardDrpdwnValues.add(i, ScoreCardDropdownValues1);
                    }

                    ArrayList<String> UnionList = new ArrayList<String>(ScoreCardsValues);

                    UnionList.addAll(ScoreCardDrpdwnValues);

                    System.out.println("Unionlist:" + UnionList);

                    ArrayList<String> IterationList = new ArrayList<String>(ScoreCardsValues);

                    IterationList.retainAll(ScoreCardDrpdwnValues);

                    System.out.println("IterationList:" + IterationList);

                    ArrayList<String> FinalList = new ArrayList<String>(UnionList);

                    FinalList.removeAll(IterationList);

                    System.out.println("FinalList:" + FinalList);

                    for (int i = 0; i < FinalList.size(); i++) {

                        webdriver.selectitemfromdropdown(FinalList.get(i));
                        Thread.sleep(2000);
                        int ErrorMsgCount = webdriver.findElements("xpath",
                                "//div[text()='This Scorecard Milestone is already associated to the other Task.']")
                                .length();
                        if (ErrorMsgCount == 0) {
                            System.out.println("Validation is passed for:" + i + "value");

                        } else {
                            System.out.println("Validation is failed for:" + i + "value");
                            LMflag = false;
                            return false;
                        }

                    }
                    LMflag = false;
                }
            }

            return true;
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean validatescorecarddropdownisEditable() {
        try {

            ArrayList<String> ScoreCardDrpdwnValues = new ArrayList<String>();
            boolean LMflag = true;
            while (LMflag) {
                int LoadmoreCount = webdriver
                        .findElements("xpath", "//button[@class='btn mx-button mx-listview-loadMore']").length();
                if (LoadmoreCount == 1) {
                    webdriver.scrollIntoView("xpath", "//button[@class='btn mx-button mx-listview-loadMore']");
                    webdriver.click("xpath", "//button[@class='btn mx-button mx-listview-loadMore']");
                } else {
                    int ScoreCardCount = webdriver.findElements("xpath",
                            "//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default']")
                            .length();
                    ArrayList<String> ScoreCardsValues = new ArrayList<String>();
                    for (int i = 0; i < ScoreCardCount; i++) {
                        String ScoreCards = webdriver.getElementId("xpath",
                                "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])["
                                        + (i + 1) + "]");
                        String ScoreCardsValues1 = webdriver.getElementproperty(ScoreCards, "innerText");
                        ScoreCardsValues.add(i, ScoreCardsValues1);
                    }
                    Thread.sleep(2000);
                    webdriver.click("xpath",
                            "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'limit-content-list milestone-sc-list btn-default')])[1]");
                    int ScoreDropdownCount = webdriver.findElements("xpath",
                            "//select[contains(@id,'ProjectPlan.EditScoreCardMilestone_Level2Task.referenceSelector')]/option")
                            .length();
                    for (int i = 0; i < ScoreDropdownCount; i++) {
                        String ScoreCardDropdownValues = webdriver.getElementId("xpath",
                                "(//select[contains(@id,'ProjectPlan.EditScoreCardMilestone_Level2Task.referenceSelector')]/option)["
                                        + (i + 1) + "]");
                        String ScoreCardDropdownValues1 = webdriver.getElementproperty(ScoreCardDropdownValues,
                                "innerText");
                        ScoreCardDrpdwnValues.add(i, ScoreCardDropdownValues1);
                    }
                    ArrayList<String> union = new ArrayList<String>(ScoreCardsValues);
                    union.addAll(ScoreCardDrpdwnValues);

                    ArrayList<String> intersection = new ArrayList<String>(ScoreCardsValues);
                    intersection.retainAll(ScoreCardDrpdwnValues);

                    union.removeAll(intersection);

                    for (String n : union) {
                        webdriver.selectitemfromdropdown(n);
                        int ErrorMsgCount = webdriver.findElements("xpath",
                                "//div[text()='This Scorecard Milestone is already associated to the other Task.']")
                                .length();
                        if (ErrorMsgCount == 0) {
                            webdriver.click("xpath", "//button[text()='Save']");
                            Thread.sleep(2000);
                            int FirstScoreCardCount = webdriver.findElements("xpath",
                                    "(//button[contains(@class,'btn mx-button mx-name-actionButton') and contains(@class,'limit-content-list milestone-sc-list btn-default')])[1]")
                                    .length();
                            if (FirstScoreCardCount == 1) {
                                System.out.println("ScoreCard Dropdown is editable");
                                LMflag = false;
                                return true;
                            } else {
                                System.out.println("ScoreCard Dropdown is editable");
                                LMflag = false;
                                return false;
                            }

                        } else {
                            System.out.println("Error Message is present so that method is failed");
                            LMflag = false;
                            return false;
                        }
                    }
                    return true;
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean getdateDifference(String startDate, String EndDate, String Store) {
        try {

            SimpleDateFormat obj = new SimpleDateFormat("dd MMM yy");
            Date date1 = obj.parse(startDate);
            Date date2 = obj.parse(EndDate);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);
            int numberOfDays = 0;
            while (cal1.before(cal2)) {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    numberOfDays++;
                }
                cal1.add(Calendar.DATE, 1);
            }
            System.out.println("Exclude saturday and sunday total Number of days :" + numberOfDays);
            String val = String.valueOf(numberOfDays);
            int[] array = { 2 };
            String[] value = gm.runtimeparameter(array);
            for (int i = 0; i < value.length; i++) {
                Store = value[i];
                webdriver.storeruntime(Store, val);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean dropdownvaluevalidation(String valuetoenter) throws InterruptedException {

        boolean bStatus = false;

        try {

            JSONArray tabs = new JSONArray();

            tabs = webdriver.getWindowhandles();

            if (tabs.length() > 1)

                webdriver.checkTab(webdriver.getCurrentObject(), tabs);

            if (webdriver.checkFrame(webdriver.getCurrentObject())) {

                JSONObject idnamevalue = webdriver.uniqueElement(webdriver.getCurrentObject());

                String identifiername = idnamevalue.getString("identifiername");

                String value = idnamevalue.getString("value");

                webdriver.click(identifiername, value);

                Thread.sleep(400);

                String xpath2 = value + "//option";

                int l2 = webdriver.findElements("xpath", xpath2).length();

                List<String> list1 = new ArrayList();

                String testdata[] = valuetoenter.split(",");

                List<String> list2 = Arrays.asList(testdata);

                System.out.println("Input Values:" + list2);

                for (int i = 0; i < l2; i++) {

                    String x = "(" + xpath2 + ")[" + (i + 1) + "]";

                    String elementid = webdriver.getElementId("xpath", x);

                    String s = webdriver.getElementproperty(elementid, "innerText");

                    list1.add(s);

                }

                System.out.println("Expected Values:" + list1);

                for (int i = 0; i < list1.size(); i++) {

                    for (int j = 0; j < list2.size(); j++) {

                        if (list1.toString().contentEquals(list2.toString())) {

                            System.out.println("Validation is passed for" + (j + 1) + "Value");

                        } else {

                            return false;

                        }
                        // return true;

                    }
                    return true;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

            webdriver.logger.error("dropdownvaluevalidation Exception: " + e.getMessage());

            bStatus = false;

        }

        return bStatus;

    }

    public boolean CheckSorting(String ReplaceHeaderValue) {

        List<String> list1 = new LinkedList();
        List<String> list2 = new LinkedList();
        List<String> list3 = new LinkedList();
        List<String> list4 = new LinkedList();

        try {
            String locatorvalue = "", locatorKey = "";
            for (JsonNode attr : webdriver.getCurrentObject().getAttributes()) {
                if (attr.get("name").asText().toLowerCase().equals("xpath")) {
                    locatorKey = attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                }
                if (attr.get("name").asText().toLowerCase().equals("id")) {
                    locatorKey = attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                }
                if (attr.get("name").asText().toLowerCase().equals("class")) {
                    locatorKey = attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                }
            }

            Boolean StatusFlag = true;
            while (StatusFlag) {

                int RowCount = webdriver.findElements(locatorKey, locatorvalue).length(); // tbody[@dojoattachpoint='gridBodyNode']/tr/td[5]

                for (int i = 1; i <= RowCount; i++) {

                    String x = "(" + locatorvalue + ")[" + (i) + "]";

                    String id = webdriver.getElementId("xpath", x);

                    String Rvalues = webdriver.getElementproperty(id, "innerText");

                    list1.add(Rvalues);
                }

                int FrwrdPageButton = webdriver
                        .findElements("xpath",
                                "//button[@aria-label='Go to next page']/span[@class='glyphicon glyphicon-forward']")
                        .length();

                if (FrwrdPageButton == 1) {
                    webdriver.click("xpath",
                            "//button[@aria-label='Go to next page']/span[@class='glyphicon glyphicon-forward']");
                } else {
                    StatusFlag = false;
                }
            }
            webdriver.click("xpath",
                    "//div[@class='mx-datagrid-head-caption' and contains(text(),'" + ReplaceHeaderValue + "')]");
            Thread.sleep(400);
            int UpArrowIcon = webdriver.findElements("xpath",
                    "//button[contains(@class,'btn_border') and text()='Open Project']/../../..//th//div[text()='"
                            + ReplaceHeaderValue + "']/..//span[text()='▲']")
                    .length();

            if (UpArrowIcon == 1) {

                int RowCount = webdriver.findElements(locatorKey, locatorvalue).length();
                // List<String> list2 = new LinkedList();
                for (int i = 1; i <= RowCount; i++) {

                    String x = "(" + locatorvalue + ")[" + (i) + "]";

                    String id = webdriver.getElementId("xpath", x);

                    String Rvalues = webdriver.getElementproperty(id, "innerText");

                    list2.add(Rvalues);
                }
            } else {
                return false;
            }

            webdriver.click("xpath",
                    "//button[@aria-label='Go to first page']//span[@class='glyphicon glyphicon-step-backward']");

            Boolean DStatusFlag = true;
            while (DStatusFlag) {

                int RowCount = webdriver.findElements(locatorKey, locatorvalue).length(); // tbody[@dojoattachpoint='gridBodyNode']/tr/td[5]
                // List<String> list3 = new LinkedList();

                for (int i = 1; i <= RowCount; i++) {

                    String x = "(" + locatorvalue + ")[" + (i) + "]";

                    String id = webdriver.getElementId("xpath", x);

                    String Rvalues = webdriver.getElementproperty(id, "innerText");

                    list3.add(Rvalues);
                }

                int FrwrdPageButton = webdriver
                        .findElements("xpath",
                                "//button[@aria-label='Go to next page']/span[@class='glyphicon glyphicon-forward']")
                        .length();

                if (FrwrdPageButton == 1) {
                    webdriver.click("xpath",
                            "//button[@aria-label='Go to next page']/span[@class='glyphicon glyphicon-forward']");
                } else {
                    StatusFlag = false;
                }
            }
            webdriver.click("xpath",
                    "//div[@class='mx-datagrid-head-caption' and contains(text(),'" + ReplaceHeaderValue + "')]");
            Thread.sleep(400);
            int BackwardArrowIcon = webdriver
                    .findElements("xpath", "//div[text()='" + ReplaceHeaderValue + "']/..//span[text()='▼']").length();

            if (BackwardArrowIcon == 1) {

                int RowCount = webdriver.findElements(locatorKey, locatorvalue).length();
                // List<String> list4 = new LinkedList();
                for (int i = 1; i <= RowCount; i++) {

                    String x = "(" + locatorvalue + ")[" + (i) + "]";

                    String id = webdriver.getElementId("xpath", x);

                    String Rvalues = webdriver.getElementproperty(id, "innerText");

                    list4.add(Rvalues);
                }
            } else {
                return false;
            }

            Collections.sort(list1);

            System.out.println("Expected Ascending order values are :" + list1);

            Collections.sort(list3, Collections.reverseOrder());

            System.out.println("Expected Ascending order values are :" + list3);

            if (list1.equals(list2)) {
                System.out.println("Ascending order is working as expected");
            } else {
                System.out.println("Ascending order is not working as expected");
                return false;
            }

            if (list3.equals(list4)) {
                System.out.println("Decending order is working as expected");
            } else {
                System.out.println("Decending order is not working as expected");
                return false;
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean ValidatePredecessorCalculation(String Date1, String Date2) throws Exception {

        try {

            SimpleDateFormat obj = new SimpleDateFormat("dd MMM yy");
            Date date1 = obj.parse(Date1);
            Date date2 = obj.parse(Date2);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);
            int numberOfDays = 0;
            while (cal1.before(cal2)) {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    numberOfDays++;
                }
                cal1.add(Calendar.DATE, 1);
            }
            System.out.println("Exclude saturday and sunday total Number of days :" + numberOfDays);
            if (numberOfDays == 1) {
                System.out.println("Predecessor Calculation is as Expected");
                return true;

            } else {
                System.out.println("Predecessor Calculation is not as Expected");
                return false;

            }

        } catch (Exception e) {
            return false;
        }

    }

    public boolean Dynamicsplitandstore(String InputValue, String Delimit, String Index, String SplitedValue) {

        try {

            String[] InputValue_stringList = InputValue.split(" ");

            String s = "";

            if (Integer.parseInt(Index) == 1) {
                s = InputValue_stringList[0] + " " + InputValue_stringList[1] + " " + InputValue_stringList[2];
                webdriver.storeruntime(SplitedValue, s);
            } else if (Integer.parseInt(Index) == 2) {
                s = InputValue_stringList[3] + " " + InputValue_stringList[4] + " " + InputValue_stringList[5];
                webdriver.storeruntime(SplitedValue, s);
            }

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean ConvertDateFormat(String inputdate, String inputdateformat, String Expecteddateformat,
            String Output) {

        try {

            LocalDate date = LocalDate.parse(inputdate, DateTimeFormatter.ofPattern(inputdateformat));
            System.out.println(date);
            String newDate = date.format(DateTimeFormatter.ofPattern(Expecteddateformat));
            System.out.println(newDate);
            webdriver.storeruntime(Output, newDate);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean EnterActualStartandFinishDate() {

        try {
            int ActualStartDateCount = webdriver.findElements("xpath",
                    "//div[contains(@class,'mx-name-container') and contains(@class,'space-list column5 cont-font row-center')]//div[contains(@class,'datewidth')]")
                    .length();
            for (int i = 2; i <= ActualStartDateCount; i++) {
                webdriver.click("xpath",
                        "(//div[contains(@class,'mx-name-container') and contains(@class,'space-list column5 cont-font row-center')]//div[contains(@class,'datewidth')])["
                                + i + "]");
                Thread.sleep(1000);
                webdriver.click("xpath", "//span[text()='Actual start date']/../..//input");
                Thread.sleep(1000);
                webdriver.click("xpath", "(//div [@class='datepicker-days']//tr//td[@class='day'])[1]");
                Thread.sleep(1000);
                webdriver.click("xpath", "//div/button[text()='Change date']");
            }
            int ActualFinishDateCount = webdriver.findElements("xpath",
                    "//div[contains(@class,'mx-name-container') and contains(@class,'space-list column6 cont-font row-center')]//div[contains(@class,'datewidth')]")
                    .length();

            for (int i = 2; i <= ActualFinishDateCount; i++) {
                webdriver.click("xpath", "(//div[@class='mx-name-container624 datewidth'])[1]");
                Thread.sleep(1000);
                webdriver.click("xpath", "(//span[text()='Actual finish date']/../..//input)[last()]");
                Thread.sleep(1000);
                webdriver.click("xpath", "(//div [@class='datepicker-days']//tr//td[@class='day'])[last()]");
                Thread.sleep(1000);
                webdriver.click("xpath", "//div/button[text()='Change date']");
                Thread.sleep(1000);
                webdriver.click("xpath", "//div/button[text()='Complete task']");
            }
            webdriver.click("xpath", "//td/button[text()='Calculate']");
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean ProjectDelayCalculation() {

        try {

            int UpArrowCount = webdriver.findElements("xpath",
                    "//img[contains(@class,'mx-image mx-name-image') and contains(@class,'img-responsive') and contains(@class,'uparrow')]")
                    .length();

            for (int i = 2; i <= UpArrowCount; i++) {
                webdriver.click("xpath",
                        "(//img[contains(@class,'mx-image mx-name-image') and contains(@class,'img-responsive') and contains(@class,'uparrow')])["
                                + i + "]");
                Thread.sleep(1000);
            }

            int ActualDurationCount = webdriver.findElements("xpath",
                    "(//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[11]//div//span[contains(@class,'cont-font')]")
                    .length();
            Thread.sleep(500);
            int PlannedDurationCount = webdriver.findElements("xpath",
                    "(//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[10]//span[contains(@class,'cont-font')]")
                    .length();
            Thread.sleep(500);
            int VCount = webdriver.findElements("xpath",
                    "(//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[12]//div//span[contains(@class,'cont-font')]")
                    .length();
            Thread.sleep(500);

            if (ActualDurationCount == 0 && PlannedDurationCount == 0 && VCount == 0) {

                System.out.println("Predecessor Calculation for 1st milestone subtask is not working");
                return false;

            } else {
                for (int i = 1; i <= ActualDurationCount; i++) {

                    String aid = webdriver.getElementId("xpath",
                            "((//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[11]//div//span[contains(@class,'cont-font')])["
                                    + i + "]");
                    String Avalue = webdriver.getElementproperty(aid, "innerText");
                    int A = Integer.valueOf(Avalue);

                    String pid = webdriver.getElementId("xpath",
                            "((//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[10]//span[contains(@class,'cont-font')])["
                                    + i + "]");
                    String Pvalue = webdriver.getElementproperty(pid, "innerText");
                    int P = Integer.valueOf(Pvalue);

                    int C = A - P;

                    System.out.println("Value of A-P is:" + C);

                    String vid = webdriver.getElementId("xpath",
                            "((//div[contains(@class,'mx-dataview-content')]/div/ul/li)[1]/div//div[12]//div//span[contains(@class,'cont-font')])["
                                    + i + "]");
                    String Vvalue = webdriver.getElementproperty(vid, "innerText");
                    int V = Integer.valueOf(Vvalue);

                    if (C == V) {

                        System.out.println(
                                "Predecessor Calculations for 1st milestone's subtasks are working as expected for " + i
                                        + "th Value");

                    } else {
                        System.out.println(
                                "Predecessor Calculations for 1st milestone's subtasks are not working as expected for "
                                        + i + "th Value");
                        return false;
                    }
                }

            }
            return true;
        } catch (

        Exception e) {
            return false;
        }
    }

    public boolean ColorValidation(String input) {
        try {

            String Gray = "#b1b1b1";
            String Blue = "#2d81ff";
            String Green = "#48ab4c";
            String Yellow = "#ffc42e";

            String S = webdriver.getElementId();
            String V = webdriver.getElementcssvalue(S, "background-color");
            String B = Color.fromString(V).asHex();

            System.out.println("Colour of the Web Element is" + B);
            if (B.equalsIgnoreCase(Gray) && B.equalsIgnoreCase(input)) {
                System.out.println("Colour of the Web Element is equal to Gray colour");
                // return true;
            } else if (B.equalsIgnoreCase(Blue) && B.equalsIgnoreCase(input)) {
                System.out.println("Colour of the Web Element is equal to Blue colour");
                // return true;
            } else if (B.equalsIgnoreCase(Green) && B.equalsIgnoreCase(input)) {
                System.out.println("Colour of the Web Element is equal to Green colour");
                // return true;
            } else if (B.equalsIgnoreCase(Yellow) && B.equalsIgnoreCase(input)) {
                System.out.println("Colour of the Web Element is equal to Yellow colour");
                // return true;
            } else {
                System.out.println("Colour of the Web Element is not equal to input colour");
                // return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean DynamicReadTextandStore(String Predecessor, String DateType, String StoreDate) {

        try {

            int Datecount = webdriver
                    .findElements("xpath", "(//div/span[text()='" + Predecessor
                            + "  ']//..//..//..//..//..//..//div[contains(@class,'datewidth')])[" + DateType + "]")
                    .length();

            if (Datecount == 1) {
                webdriver.readtextandstore("xpath",
                        "(//div/span[text()='" + Predecessor
                                + "  ']//..//..//..//..//..//..//div[contains(@class,'datewidth')])[" + DateType + "]",
                        StoreDate);
                // return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }



    GeneralMethod gn = new GeneralMethod();

    public boolean separateDate(String runtimeval, String seperator, String runTimeParam) {
		boolean bstatus = false;
		String finalDate = "";
		try {
         
			int index = 0;
         
           try {
            // Getting runtime data with provided input value.
            String replacevalue2 = "";
            replacevalue2 = gn.getfromruntimefortestdata(runtimeval);
            if (replacevalue2.isEmpty()) {
                runtimeval = runtimeval;
            } else {
                // If runtime data not available then assgining input data
                runtimeval = replacevalue2;
            }
        } catch (Exception e) {
            // If runtime data not available then assgining input data.
            runtimeval = runtimeval;
        }
            webdriver.getCurrentObject().setAttributes(InitializeDependence.findattrReplace(webdriver.getCurrentObject().getAttributes(), runtimeval));
		 
		 	String ele1=webdriver.getElementId();
			String ele2 = webdriver.getElementproperty(ele1, "innerText");

			String[] stringList = ele2.split(" ");
			// String a1 = stringList[index];
			// 06 Feb 23 16 Aug 2023
			String a1 = "";
			if (Integer.parseInt(seperator) == 1) {
				a1 = stringList[0] + " " + stringList[1] + " " + stringList[2];
               // bstatus =true;
			} else if (Integer.parseInt(seperator) == 2) {
				a1 = stringList[3] + " " + stringList[4] + " " + stringList[5];
                
			}
			System.out.println("==" + a1 + "==");
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd MMM yy");
			Date date = originalFormat.parse(a1);
			SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM yyyy");
			finalDate = newFormat.format(date);
			System.out.println(date + "  --> " + finalDate);
			
		 gn.storeruntime(runTimeParam, finalDate);
         bstatus =true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bstatus;
	}
	
	public boolean getAndStoreRuntime(String getruntimevalue,  String sendRunTimeValue) {
		boolean bstatus = false;
		
		try {
			String valuetoINsert=gn.getfromruntimefortestdata(getruntimevalue);
            if(valuetoINsert.isEmpty()) {
				valuetoINsert = getruntimevalue;
			}
			webdriver.getCurrentObject().setAttributes(InitializeDependence.findattrReplace(webdriver.getCurrentObject().getAttributes(), valuetoINsert));
			String ele1=webdriver.getElementId();
			String ele2 = webdriver.getElementproperty(ele1, "innerText");
			System.out.println("ele2 --> "+ele2);
			gn.storeruntime(sendRunTimeValue, ele2);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bstatus;
	}

    public boolean mousehoveranelement(String getruntimevalue) {
        try {
          
            try {
                // Getting runtime data with provided input value.
                String replacevalue2 = "";
                replacevalue2 = gn.getfromruntimefortestdata(getruntimevalue);
                if (replacevalue2.isEmpty()) {
                    getruntimevalue = getruntimevalue;
                } else {
                    // If runtime data not available then assgining input data
                    getruntimevalue = replacevalue2;
                }
            } catch (Exception e) {
                // If runtime data not available then assgining input data.
                getruntimevalue = getruntimevalue;
            }
			String locatorvalue="",locatorKey="";
            for (JsonNode attr : webdriver.getCurrentObject().getAttributes()) {
                if (attr.get("name").asText().toLowerCase().equals("xpath")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                } if (attr.get("name").asText().toLowerCase().equals("id")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                } if (attr.get("name").asText().toLowerCase().equals("class")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                }
            }
            locatorvalue=locatorvalue.replace("#replace", getruntimevalue);
           return webdriver.movetoelement(locatorKey, locatorvalue);
        
        } catch (Exception e) {
            e.printStackTrace();
            webdriver.logger.info("mousehoveranelement Exception:" + e.getMessage());
            return false;
        }
    }

    public boolean elementposition(String valueToSearch, String sendRunTimeValue){
        try
        {
            JSONObject res;
            String locatorvalue="",locatorKey="";
            for (JsonNode attr : webdriver.getCurrentObject().getAttributes()) {
                if (attr.get("name").asText().toLowerCase().equals("xpath")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                } if (attr.get("name").asText().toLowerCase().equals("id")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                } if (attr.get("name").asText().toLowerCase().equals("class")) {
                    locatorKey=attr.get("name").asText();
                    locatorvalue = attr.get("value").asText();
                    break;
                }
            }
            try {
                // Getting runtime data with provided input value.
                String replacevalue2 = "";
                replacevalue2 = gn.getfromruntimefortestdata(valueToSearch);
                if (replacevalue2.isEmpty()) {
                    valueToSearch = valueToSearch;
                } else {
                    // If runtime data not available then assgining input data
                    valueToSearch = replacevalue2;
                }
            } catch (Exception e) {
                // If runtime data not available then assgining input data.
                valueToSearch = valueToSearch;
            }
            int  length = webdriver.findElements(locatorKey, locatorvalue).length();
            int position =0;
            for(int i=0;i<length;i++){

            locatorvalue="("+locatorvalue+")["+(i+1)+"]";
            res = webdriver.executeScript2(
                "function getElementByXpath(path) {return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue};var a = getElementByXpath("
                        + webdriver.escape(locatorvalue) + ");return a.innerHTML;");
                       String  elementValue= res.get("value").toString();
                if(elementValue.trim().equalsIgnoreCase(valueToSearch)){
                    position=i;
                    break;
                }
            }
            return gn.storeruntime(sendRunTimeValue, String.valueOf((position+1)));        
        }
            catch(Exception e){
            e.printStackTrace();
            return false;
        }
       
    }



}
