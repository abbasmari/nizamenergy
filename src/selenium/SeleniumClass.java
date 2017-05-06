package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class SeleniumClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		System.out.println("www.google.com");
		 WebDriver driver = new FirefoxDriver();

		 driver.get("http://www.jobserve.com/pk/en/Job-Search/");
		 driver.findElement(By.xpath(".//*[@id='ddcl-selInd']/span/span")).click();
		 driver.findElement(By.xpath(".//*[@id='ddcl-selInd-ddw']/div/div[2]/label")).click();
		 driver.findElement(By.xpath(".//*[@id='ddcl-selInd-ddw']/div/div[3]/label")).click();
		 System.out.println("&&&&&&&&&&& "+driver.getTitle());
		 Thread.sleep(5000l);
		 driver.quit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
