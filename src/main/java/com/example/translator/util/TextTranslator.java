package com.example.translator.util;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.dto.TranslationResultsDTO;
import yandex.cloud.api.ai.translate.v2.TranslationServiceGrpc;
import yandex.cloud.api.ai.translate.v2.TranslationServiceGrpc.TranslationServiceBlockingStub;
import yandex.cloud.api.ai.translate.v2.TranslationServiceOuterClass;
import yandex.cloud.api.ai.translate.v2.TranslationServiceOuterClass.TranslateRequest;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.Auth;

import java.sql.Timestamp;
import java.time.Duration;

import static com.example.translator.constant.YandexCloudAuthDataKeeper.*;

public class TextTranslator {

    public static String translateText(String inputText, String sourceLanguage, String targetLanguage)
            throws RuntimeException {

        TranslationServiceBlockingStub service = configureTranslationService();
        TranslateRequest request = createTranslateRequest(inputText, sourceLanguage, targetLanguage);
        return implementRequest(request, service);
    }

    private static TranslationServiceBlockingStub configureTranslationService() {

        ServiceFactory factory = ServiceFactory.builder()
                .credentialProvider(Auth.iamTokenBuilder().token(IAM_TOKEN))
                .requestTimeout(Duration.ofSeconds(DURATION_TIME))
                .build();
        return factory.create(TranslationServiceBlockingStub.class, TranslationServiceGrpc::newBlockingStub);
    }

    private static TranslateRequest createTranslateRequest(String inputText, String sourceLanguage, String targetLanguage)
            throws RuntimeException {

        return TranslateRequest.newBuilder()
                .setSourceLanguageCode(sourceLanguage)
                .setTargetLanguageCode(targetLanguage)
                .setFormat(TranslateRequest.Format.PLAIN_TEXT)
                .addTexts(inputText)
                .setFolderId(FOLDER_ID)
                .build();
    }

    private static String implementRequest(TranslateRequest request, TranslationServiceBlockingStub service) {

        TranslationServiceOuterClass.TranslateResponse response = service.translate(request);
        return response.getTranslations(TRANSLATED_ELEMENT_INDEX).getText();
    }

    public static TranslationResultsDTO translateSingleWords(String inputText, String sourceLanguage,
                                                             String targetLanguage) {

        String[] inputWords = StringHandler.separateString(inputText.replaceAll("\\s+", "\u0020"));
        String[] translatedWords = new String[inputWords.length];
        for (int i = 0; i < inputWords.length; i++) {
            translatedWords[i] = TextTranslator.translateText(inputWords[i], sourceLanguage, targetLanguage);
        }

        return new TranslationResultsDTO(StringHandler.uniteString(translatedWords), inputWords, translatedWords);
    }

    public static String insertAllInfo(Long id, Timestamp time, String inputText, String translationRule,
                                       String ip, TranslationResultsDTO dto, TranslationDAOImpl dao) {
        dao.addRequestInfo(id, time, inputText, dto.getTranslatedText(),
                translationRule, ip);

        for (int i = 0; i < dto.getInputWords().length; i++) {
            dao.addTranslationInfo(id, dto.getInputWords()[i].replaceAll("[-+.^:*=',]",""),
                    dto.getTranslatedWords()[i].replaceAll("[-+.^:*=',]",""));
        }
        return dto.getTranslatedText();
    }
}
