package CMS;


/**
 * Generated from IDL interface "CustomerCareService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public interface CustomerCareServiceOperations
{
	/* constants */
	/* operations  */
	java.lang.String createTicket(CMS.CustomerCare ticket);
	boolean updateTicket(java.lang.String ticketId, CMS.CustomerCare updateTicket);
	CMS.CustomerCare getTicketInfo(java.lang.String ticketId);
	CMS.CustomerCare[] getCustomerTicket(java.lang.String customerId);
}
