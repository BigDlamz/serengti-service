package za.co.serengti.merchants;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@ApplicationScoped
@Slf4j
public class FeedbackService {

    private final FeedbackRepository repository;
    private final FeedbackMapper convertor;

    @Inject
    public FeedbackService(FeedbackRepository repository, FeedbackMapper convertor) {
        this.repository = repository;
        this.convertor = convertor;
    }

    public void saveFeedback(FeedbackDTO saveFeedbackDTO) {

        log.info("Saving feedback for receiptId: {}", saveFeedbackDTO.getReceiptId());

        var feedback = convertor.toEntity(saveFeedbackDTO);

        repository.saveFeedback(feedback);
    }

    public List<FeedbackDTO> retrieveFeedback(Long merchantId) {

        return repository.retrieveAllFeedback(merchantId)
                .stream()
                .map(convertor::toDTO)
                .toList();
    }

    public Double getAverageRating(Long merchantId) {

        Double rating = repository.findAverageRating(merchantId);

        if (rating == null || rating.isNaN()) {
            return 0.0;
        }

        var symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        var df = new DecimalFormat("#.#", symbols);

        return Double.parseDouble(df.format(rating));
    }

    public boolean isRated(Long receiptId) {

        var isRated = repository.findFeedbackForReceipt(receiptId);

        return Objects.nonNull(isRated);

    }

    public List<FeedbackDTO> getLatestFeedback(Long merchantId) {

        return repository.findLatestFeedback(merchantId)
                .stream()
                .map(convertor::toDTO)
                .toList();

    }
}
