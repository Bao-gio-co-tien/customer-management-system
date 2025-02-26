package CMS;


/**
 * Generated from IDL interface "CustomerUpdateService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public interface CustomerUpdateServiceOperations
{
	/* constants */
	/* operations  */
	boolean updateCustomerInfo(java.lang.String customerId, CMS.CustomerInfo newInfo);
	boolean updateStatus(java.lang.String customerId, java.lang.String newStatus);
	void logCustomerChange(java.lang.String customerId, java.lang.String changeType);
}
