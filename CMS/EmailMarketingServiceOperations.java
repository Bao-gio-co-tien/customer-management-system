package CMS;


/**
 * Generated from IDL interface "EmailMarketingService".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at Feb 27, 2025, 12:43:41 AM
 */

public interface EmailMarketingServiceOperations
{
	/* constants */
	/* operations  */
	boolean createCampaign(CMS.EmailCampaign campaign);
	boolean sendCampaign(java.lang.String campaignId);
	java.lang.String[] getCustomerSegment(java.lang.String segmentCriteria);
	CMS.EmailCampaign getCampaign(java.lang.String campaignId);
}
