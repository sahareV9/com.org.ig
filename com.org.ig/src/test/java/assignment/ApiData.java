package assignment;

public class ApiData {
	int status;
	EmployeeData employeeData;
	String message;
	String headerContentType;
	String headerServer;
	/**
	 * @return the headerContentType
	 */
	public String getHeaderContentType() {
		return headerContentType;
	}
	/**
	 * @param headerContentType the headerContentType to set
	 */
	public void setHeaderContentType(String headerContentType) {
		this.headerContentType = headerContentType;
	}
	/**
	 * @return the headerServer
	 */
	public String getHeaderServer() {
		return headerServer;
	}
	/**
	 * @param headerServer the headerServer to set
	 */
	public void setHeaderServer(String headerServer) {
		this.headerServer = headerServer;
	}
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the employeeData
	 */
	public EmployeeData getEmployeeData() {
		return employeeData;
	}
	/**
	 * @param employeeData the employeeData to set
	 */
	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
