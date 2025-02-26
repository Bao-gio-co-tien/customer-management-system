package CMS;


/**
 * Generated from IDL interface "CustomerInfoService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public interface CustomerInfoServiceOperations
{
	/* constants */
	/* operations  */
	CMS.CustomerInfo getCustomer(java.lang.String customerId);
	CMS.CustomerInfo[] searchCustomer(java.lang.String criteria);
	boolean addCustomer(CMS.CustomerInfo customer);
	CMS.CustomerInfo[] getAllCustomer();
}
