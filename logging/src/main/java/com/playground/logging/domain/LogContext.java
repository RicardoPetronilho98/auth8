package com.playground.logging.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class LogContext {

    private Identifiers identifiers;
    private LogPoint logPoint;

    private RequestInData requestInData;
    private RequestOutData requestOutData;
    private ResponseInData responseInData;
    private ResponseOutData responseOutData;

    private StopWatch internalTime;
    private StopWatch externalTime;
    private StopWatch totalTime;

    @Getter
    @ToString
    @RequiredArgsConstructor
    public enum LogPoint {
        REQUEST_IN("request-in"),
        REQUEST_OUT("request-out"),
        RESPONSE_IN("response-in"),
        RESPONSE_OUT("response-out");

        private final String value;
    }

    @Data
    @Builder
    public static class Identifiers {
        private String transactionId;
        private String parallelTransactionId;
        private String useCase;
    }

    @Data
    @Builder
    public static class RequestInData {
        private LocalDateTime timestamp;
        private String path;
        private Map<String, String> headers;
        private String payload;
    }

    @Data
    @Builder
    public static class RequestOutData {
        private LocalDateTime timestamp;
        private String uri;
        private Map<String, String> headers;
        private String payload;
    }

    @Data
    @Builder
    public static class ResponseInData {
        private LocalDateTime timestamp;
        private String uri;
        private Map<String, String> headers;
        private String payload;
        private HttpStatus status;
    }

    @Data
    @Builder
    public static class ResponseOutData {
        private LocalDateTime timestamp;
        private String path;
        private Map<String, String> headers;
        private String payload;
        private HttpStatus status;
    }

}
