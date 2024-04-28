package za.co.serengti.merchants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MerchantServiceImpl implements MerchantService {


    private final FeedbackService feedbackService;
    private final SpecialService specialService;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper convertor;

    @Inject
    public MerchantServiceImpl(FeedbackService feedbackService, SpecialService specialService, MerchantRepository merchantRepository, MerchantMapper convertor) {
        this.feedbackService = feedbackService;
        this.specialService = specialService;
        this.merchantRepository = merchantRepository;
        this.convertor = convertor;
    }

    @Override
    public MerchantDTO find(Long merchantId) {

        var entity = merchantRepository.findById(merchantId);
        return convertor.toDTO(entity);

    }

    @Override
    public List<SpecialDTO> retrieveSpecials(Long merchantId){

        return specialService.retrieveSpecials(merchantId);

    }

    @Override
    public List<FeedbackDTO> retrieveFeedback(Long storeId){

        return feedbackService.find(storeId);

    }

    @Override
    public void saveFeedback(FeedbackDTO feedbackDTO){

        feedbackService.save(feedbackDTO);

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
