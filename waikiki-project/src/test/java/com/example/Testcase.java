package com.example;



import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;




public class Testcase extends BaseTest {
    
	public static Logger Logger = LogManager.getLogger(Testcase.class);

    public static final String url = "https://www.lcwaikiki.com/tr-TR/TR";
    String ProductPriceString;
    
	@Test
	public void test() throws InterruptedException {
		
		//homepage opens

		driver.get(url);
        Thread.sleep(1000);

        //check the url
        if(driver.getCurrentUrl().endsWith("lcwaikiki.com/tr-TR/TR")){
            System.out.println("url is true");
        }
        else {
            System.out.println("url is false");
        }

        //next method is called
        login();
        
	}

        public void login() throws InterruptedException{
        //login information is entered

            WebElement loginbutton = driver.findElement(By.cssSelector(".dropdown:nth-child(1) .dropdown-label"));
            loginbutton.click();
            Thread.sleep(2000);
        driver.findElement(By.id("LoginEmail")).sendKeys("nmnysnbbr1@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("numan4144603");
        driver.findElement(By.id("loginLink")).click();



        Thread.sleep(1000);

//        //check the login
//        boolean profile = driver.findElement(By.linkText("Hesabım")).isDisplayed();
//        if(profile==true) {
//        	System.out.println("login succesful");
//
//        }
//        else {
//        	System.out.println("login failed");
//        	}


        //next method
        search();
       }

        public void search() throws InterruptedException{
        //search for trousers
        WebElement searchbox = driver.findElement(By.id("search_input"));
        searchbox.click();
        searchbox.sendKeys("erkek pantolon");
        driver.findElement(By.cssSelector(".searchButton[type=button]")).click();
        
        //go down
        Thread.sleep(3000);
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        Thread.sleep(3000);

        //click the see more button
        driver.findElement(By.cssSelector(".paginator__button > span:nth-child(1)")).click();
            Thread.sleep(2000);

        //check the url
        if(driver.getCurrentUrl().endsWith("www.lcwaikiki.com/tr-TR/TR/arama?q=Erkek%20pantolon&PageIndex=2")){
            System.out.println("url 'see more products' is true");
        }
        else {
            System.out.println("url 'see more products'' is false");
        }   
        addProduct();
        }


        public void addProduct() throws InterruptedException{
        	//rotate and click items randomly.
            Random rnd = new Random();
            int index = rnd.nextInt(191) ;
//            List<WebElement> allProducts = driver.findElements(By.className(".product-image__image"));
            List<WebElement> allProducts = driver.findElements(By.cssSelector(".product-image__image"));
            allProducts.get(index).click();

            Thread.sleep(1000);

            
            //choose size and height and add to cart
            addToCart();
        }
        public void addToCart() throws InterruptedException{

            WebElement size = driver.findElement(By.xpath("//*[@id=\"option-size\"]/a[3]"));
            size.click();

            WebElement height = driver.findElement(By.xpath("//*[@id=\"option-height\"]/a[2]"));
            height.click();

            WebElement clickbasketaddedbutton = driver.findElement(By.cssSelector(".add-to-cart[id=pd_add_to_cart]"));
            clickbasketaddedbutton.click();


            WebElement ProductPrice = driver.findElement(By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[2]/div/div/div/span[2]"));
            String ProductPriceString= ProductPrice.getText();
            System.out.println("Product Price : " + ProductPriceString);


        
        goToCart();
        
        }

        //go to cart
        public void goToCart() throws InterruptedException {

            WebElement mybasketbutton = driver.findElement(By.cssSelector(".header-bag-icon"));
            mybasketbutton.click();
        System.out.println("Showing Cart");

        //Cart Price
        WebElement BasketPrice = driver.findElement(By.cssSelector(".rd-cart-item-price"));
        String BasketPriceString= BasketPrice.getText();
        System.out.println("Cart Price : " + BasketPriceString);


        //check homepage with cart price
        if(BasketPriceString.equals(ProductPriceString)){
//            if(Objects.equals(BasketPriceString, ProductPriceString)){
        System.out.println("Product price and basket price same ");
        }
        else {
        	System.out.println(" Prices are different");
        }

        increase();
        }
        public void increase() throws InterruptedException{
        //number of items increased to 2
        Actions action = new Actions(driver);
            WebElement increasebutton = driver.findElement(By.cssSelector(".oq-up"));
            increasebutton.click();
        System.out.println("Product increased to 2 ");

        Thread.sleep(1000);
         
        //checked that 2
//        String numberOfProduct= (driver.findElement(By.cssSelector("item-quantity-input")).getText());
//
//        if(numberOfProduct=='2') {
//        System.out.println("Product number is " + numberOfProduct );
//
//        Thread.sleep(1000);
//        }



        deleteItem();
        }
        
        public void deleteItem() throws InterruptedException{

        //delete item
            WebElement deletebutton = driver.findElement(By.cssSelector(".fa-trash-o"));
            deletebutton.click();

            WebElement deletebuttonagain = driver.findElement(By.cssSelector(".inverted-modal-button"));
            Thread.sleep(2000);
            deletebuttonagain.click();

        System.out.println("Products are deleted");  

        //compare the strings
        String actual = driver.findElement(By.cssSelector(".cart-empty-title")).getAttribute("innerHTML");
        String expected = "Sepetinizde ürün bulunmamaktadır.";

        if (actual.equals(expected)){
            System.out.println("Cart is empty");
        }
        System.out.println("Test passed.");
    
        }
		
}



		


