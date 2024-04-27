package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MerchantService implements MerchantFacade {

    private final MerchantInternalService merchantInternalService;
    private final FeedbackService feedbackService;
    private final SpecialService specialService;

    @Inject
    public MerchantService(FeedbackService feedbackService, SpecialService specialService, MerchantInternalService merchantInternalService) {
        this.feedbackService = feedbackService;
        this.specialService = specialService;
        this.merchantInternalService = merchantInternalService;

    }

    @Override
    public MerchantDTO find(Long merchantId) {

        return merchantInternalService.find(merchantId);

    }

    @Override
    public List<SpecialDTO> retrieveSpecials(Long merchantId){

        return specialService.retrieveSpecials(merchantId);

    }

    @Override
    public List<FeedbackDTO> retrieveFeedback(Long storeId){

        return feedbackService.retrieveFeedback(storeId);

    }

    @Override
    public void saveFeedback(FeedbackDTO feedbackDTO){

        feedbackService.saveFeedback(feedbackDTO);

    }

    @Override
    public Double retrieveAverageRating(Long merchantId){

        return feedbackService.getAverageRating(merchantId);

    }

    @Override
    public boolean isRated(Long receiptId){

        return feedbackService.isRated(receiptId);

    }

    @Override
    public List<FeedbackDTO> retrieveLatestFeedback(Long storeId){

        return feedbackService.getLatestFeedback(storeId);

    }

}
