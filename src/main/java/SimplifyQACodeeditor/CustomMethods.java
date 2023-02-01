package SimplifyQACodeeditor;

import java.util.ArrayList;

import com.simplifyqa.customMethod.SqaAutowired;
import com.simplifyqa.sqadrivers.webdriver;

public class CustomMethods {

	public boolean checktillpmexists (String PMvalue){

        try {

            String NextbuttonID = webdriver.getElementId("xpath", "//button[@aria-label='Go to next page']");
            boolean Nextbutton = webdriver.isElementenabled(NextbuttonID);
            boolean flag=true;
            while(flag){

                int count = webdriver.findElements("xpath", "//tr[@class='mx-name-index-1']//div[text()='"+PMvalue+"']").length();

                if (count==1) {

                    flag=false;
                    Thread.sleep(200);
                    webdriver.click("xpath", "(//div[text()='"+PMvalue+"'])[1]");
                    
                    
                } else{
                    webdriver.click("xpath", "//button[@aria-label='Go to next page']");
                }

            }  
            return true;    
        } catch (Exception e) {
           System.out.println("PM is Not Present in any page");
           return false;
        }
        
      
    }
    public boolean ValidateLevel2TaskErrorMessage(){

      try {

    int BCount = webdriver.findElements("xpath", "//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default']").length();

    for (int i = 1; i <=BCount; i++) {
        String id = webdriver.getElementId("xpath", "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])["+i+"]");
        String Value = webdriver.getElementtext(id);
        ArrayList<String> Dropdowninputs = new ArrayList<String>();
        Dropdowninputs.add(Value);
        webdriver.click("xpath", "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])[1]");
        webdriver.click();
        Thread.sleep(4000);
        webdriver.selectByVisibleText(Dropdowninputs.get(i));
        Thread.sleep(2000);
        int ErrorMsgCount = webdriver.findElements("xpath", "//div[@class='alert alert-danger mx-validation-message']").length();

        if (ErrorMsgCount==1) {
            Thread.sleep(200);
            System.out.println("Error Message Validation passed for "+ i +" value");   
        } else{
            System.out.println("Error Message Validation failed for "+ i +" value");
            return false;            
        }
    }
      return true;    
      } catch (Exception e) {
         return false;
      }   
    }
}

