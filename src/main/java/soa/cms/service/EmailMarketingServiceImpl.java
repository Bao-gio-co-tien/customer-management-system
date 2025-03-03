package soa.cms.service;

import CMS.EmailCampaign;
import CMS.EmailMarketingServicePOA;
import org.springframework.stereotype.Service;

@Service
public class EmailMarketingServiceImpl extends EmailMarketingServicePOA{
    @Override
    public boolean createCampaign(EmailCampaign campaign) {
        return true;
    }

    @Override
    public boolean sendCampaign(String campaignId) {
        return true;
    }

    @Override
    public String[] getCustomerSegment(String segmentCriteria) {
        return new String[0];
    }

    @Override
    public EmailCampaign getCampaign(String campaignId) {
        return new EmailCampaign();
    }
}
