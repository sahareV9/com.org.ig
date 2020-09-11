package assignment;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTest {

	public ApiData apiTest() {
		String apiUrl, headerContentType, headerServer;
		apiUrl = "http://demo4032024.mockable.io/apitest";
		RestAssured.baseURI = apiUrl;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);
		ApiData apiData = dataParcing(response);
		headerContentType = response.getHeader("Content-Type");
		headerServer = response.getHeader("Server");
		apiData.setHeaderContentType(headerContentType);
		apiData.setHeaderServer(headerServer);
		return apiData;
	}

	@Test
	/**
	 * @param response
	 */
	private void checkStatusCode() {
		int statusCode = apiTest().getStatus();
		Assert.assertEquals(statusCode, 200);
		return;
	}

	@Test
	/**
	 * @param response
	 */
	private void checkResponseHeader() {
		String headerContentType, headerServer;
		ApiData apiData = apiTest();
		headerContentType = apiData.getHeaderContentType();
		headerServer = apiData.getHeaderServer();
		Assert.assertEquals(headerContentType, "application/json; charset=UTF-8");
		Assert.assertEquals(headerServer, "Google Frontend");
		return;
	}

	@Test
	/**
	 * @param response
	 */
	private void checkResponseBody() {
		int status, age;
		String role, dob, message;
		ApiData apiData = apiTest();
		status = apiData.getStatus();
		age = apiData.employeeData.getAge();
		role = apiData.employeeData.getRole();
		dob = apiData.employeeData.getDob();
		message = apiData.getMessage();
		Assert.assertEquals(status, 200);
		Assert.assertEquals(age, 25);
		Assert.assertEquals(role, "QA Automation Developer");
		Assert.assertEquals(dob, "25-02-1994");
		Assert.assertEquals(message, "data retrieved successful");
		return;
	}

	@Test
	/**
	 * @param response
	 */
	private void checkResponseBodyForCompanyParameter() throws AssertionError {
			String company = apiTest().employeeData.getCompany();
			Assert.assertEquals(company, "ABC Infotech");
			return;
	}

	/**
	 * @param response
	 * @throws NumberF ormatException
	 */
	private ApiData dataParcing(Response response) throws NumberFormatException {
		ApiData apiData = new ApiData();
		apiData.employeeData = new EmployeeData();
		try {
			JSONParser jsonParser = new JSONParser();
			Object apiDataObject = jsonParser.parse(response.getBody().asString());
			JSONObject jsonObject = (JSONObject) apiDataObject;
			JSONArray jsonArray = (JSONArray) jsonObject.get("employeeData");
			@SuppressWarnings("unchecked")
			Iterator<Object> employeeData = jsonArray.iterator();
			JSONObject employee = (JSONObject) employeeData.next();
			int status = Integer.parseInt(jsonObject.get("status").toString());
			String message = jsonObject.get("message").toString();
			int employeeAge = Integer.parseInt(employee.get("age").toString());
			String employeeRole = employee.get("role").toString();
			String employeeDob = employee.get("dob").toString();
			String employeeCompany = employee.get("company").toString();
			apiData.setStatus(status);
			apiData.setMessage(message);
			apiData.employeeData.setAge(employeeAge);
			apiData.employeeData.setRole(employeeRole);
			apiData.employeeData.setDob(employeeDob);
			apiData.employeeData.setCompany(employeeCompany);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiData;
	}

}
