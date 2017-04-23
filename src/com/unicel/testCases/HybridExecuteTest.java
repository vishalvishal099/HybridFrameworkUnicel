package com.unicel.testCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.unicel.excelExportAndFileIO.ReadExcelFile;
import com.unicel.model.TestArtifact;
import com.unicel.operation.BaseFunction;
import com.unicel.operation.ReadObject;
import com.unicel.operation.UIOperation;
import com.unicel.reporter.MyCustomListener;
import com.unicel.util.MultiFileReaderUtility;

@Listeners(MyCustomListener.class)
public class HybridExecuteTest {
	WebDriver driver;

	@Test(dataProvider = "hybridData")
	public void testCasesExe(String testcaseName, String keyword,String objectName, String objectType, String value,String RunableMode) throws Exception {
		driver = BaseFunction.getDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(driver);
		/*
		 * System.out.println("row data is : " + testcaseName + " " + keyword +
		 * " " + objectName + " " + objectType + " " + value + " " +
		 * RunableMode);
		 */// 
		operation.perform(allObjects, keyword, objectName, objectType, value);
	}

	/*@Test
	public void testPDFReportOne() {
		driver = BaseFunction.getDriver();
		driver.get("http://google.com");
		Assert.assertTrue(false);
	}

	@Test
	public void testPDFReporTwo() {
		BaseFunction.getDriver();
		driver.get("http://in.yahoo.com");
		Assert.assertTrue(false);
	}
*/
	@Test
	public void testPDFReportThree() {
		driver = BaseFunction.getDriver();
		driver.get("http://gmail.com");
		Assert.assertTrue(false);
	}

	@AfterTest
	public void quit() {
		driver = BaseFunction.getDriver();
		driver.quit();
		Assert.assertTrue(true);

	}

	@DataProvider(name = "hybridData")
	public Object[][] getDataFromDataprovider() throws IOException {
		Object[][] newObject = null;
		try {
			Object[][] object = null;
			newObject = null;
			int counter = 0;
			boolean prevTrue = false;

			ReadExcelFile file = new ReadExcelFile();
			
			
			List<TestArtifact> testArtifactsLst = MultiFileReaderUtility.ReadMultiFiles();
			
			
			for(TestArtifact testArtifact : testArtifactsLst){
				
				/*
				String fileName = MultiFileReaderUtility.readFileName();
				
				String sheetName = MultiFileReaderUtility.readSheetName();
				*/
				
				String fileName = testArtifact.getFileName();
				
				String sheetName = testArtifact.getSheetName();
				
				if(fileName == null ||sheetName == null) {
					System.exit(0);
				}
				System.out.println("File name and sheet name are  : ***  " + fileName +  " **** " + sheetName);
				Sheet testSheet = file.readExcel(fileName, sheetName);
				int rowCount = testSheet.getLastRowNum() - testSheet.getFirstRowNum();
				object = new Object[rowCount][6];
				counter = 0;
				for (int i = 0; i < rowCount; i++) {
					prevTrue = false;
					Row row = testSheet.getRow(i + 1);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						// System.out.println(row.getCell(5).toString());
						if (row.getCell(5) != null && row.getCell(5).toString().equals("Y")) {
							prevTrue = true;
							object[counter][j] = row.getCell(j).toString();
						}
					}
					if (prevTrue) {
						counter++;
					}
				}
				newObject = new Object[counter][6];

				for (int i = 0; i < counter; i++) {

					for (int j = 0; j < 6; j++) {
						newObject[i][j] = object[i][j];
					}
				}
				//you call your hybrid
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newObject;
	}

/*	public void lernanotherSheet(){
	
}*/
	
	/*
	 * Object[][] filtertedData = new Object[object.length][6];
	 * 
	 * for(int i=0;i<object.length;i++){ for(int j=0;j<6;j++){
	 * System.out.println("data is : "+object[i][j].toString()); }
	 */

	@AfterSuite
	public void tearDown() {
		sendPDFReportByGMail("vishalvishal099@yahoo.com", "SAInath@2014","vishal@uniceltech.com", "PDF Report", "");
	}

	private static void sendPDFReportByGMail(String from, String pass,
			String to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.mail.yahoo.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			// Set from address
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set subject
			message.setSubject(subject);
			message.setText(body);

			BodyPart objMessageBodyPart = new MimeBodyPart();

			objMessageBodyPart.setText("Please Find The Attached Report File!");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(objMessageBodyPart);

			objMessageBodyPart = new MimeBodyPart();

			// Set path to the pdf report file
			String filename = System.getProperty("user.dir")
					+ "\\Default test.pdf";
			// Create data source to attach the file in mail
			DataSource source = new FileDataSource(filename);
			objMessageBodyPart.setDataHandler(new DataHandler(source));

			objMessageBodyPart.setFileName(filename);

			multipart.addBodyPart(objMessageBodyPart);

			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public Object[][] transformer(Object[][] data) {

		System.out.println("bvmjbsv");
		Object[][] filtertedData = new Object[data.length][data.length];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.println("data is : " + data[i][j].toString());
			}
		}

		return filtertedData;
	}

}
