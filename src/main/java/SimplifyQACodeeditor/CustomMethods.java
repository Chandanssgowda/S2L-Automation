package SimplifyQACodeeditor;

import java.util.ArrayList;

import com.google.gson.JsonObject;
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
      webdriver.waituntilelementexist("xpath", "//div[@class='mx-progress mx-progress-empty']");
      int BCount = webdriver.findElements("xpath", "//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default']").length();
      ArrayList<String> Dropdowninputs = new ArrayList<String>();
      for (int i = 1; i <=BCount; i++) {
          String id = webdriver.getElementId("xpath", "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])["+i+"]");
          String Value = webdriver.getElementproperty(id,"innerText");
          Dropdowninputs.add(Value);
       }
          webdriver.click("xpath", "(//button[@class='btn mx-button mx-name-actionButton43 limit-content-list milestone-sc-list btn-default'])[1]");
         // webdriver.click("xpath", "//select[contains(@id,'441.ProjectPlan.EditScoreCardMilestone_Level2Task.referenceSelector1.14')]");
          Thread.sleep(200);
          
          for(int j = 1; j<Dropdowninputs.size(); j++){
          webdriver.selectitemfromdropdown(Dropdowninputs.get(j));
          Thread.sleep(500);
          int ErrorMsgCount = webdriver.findElements("xpath", "//div[@class='alert alert-danger mx-validation-message']").length();
          
          if (ErrorMsgCount==1) {
              
              Thread.sleep(500);
              System.out.println("Error Message Validation passed for "+ Dropdowninputs.get(j) +" value");   
          } else{
              System.out.println("Error Message Validation failed for "+ Dropdowninputs.get(j) +" value");
              return false;            
          }
         
        }
        return true;
    }    
         catch (Exception e) {
           return false;
        }
           
      
}

}
