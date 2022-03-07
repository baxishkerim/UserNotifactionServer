package com.example.useremailservice.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject<T> {


    private Status status;

    @JsonProperty("data")
    private ResponseData<T> responseData;

    private String message;

    private Integer code;

    /**
     * Create a success {@code ResponseObject} with a status code and response payload.
     *
     * @param status       the response status
     * @param responseData the response payload
     */
    public ResponseObject(Status status, ResponseData<T> responseData) {
        this(status, responseData, 0);
    }


    /**
     * Create a success {@code ResponseObject} with a status code and response payload.
     *
     * @param status       the response status
     * @param responseData the response payload
     * @param code         the response status code
     */
    public ResponseObject(Status status, ResponseData<T> responseData, Integer code) {
        this.status = status;
        this.responseData = responseData;
        this.code = code;
    }

    /**
     * Create an error {@code ResponseObject} with a status code and error message.
     *
     * @param status  the response status code
     * @param message the response error message
     * @param code    the response status code
     */
    public ResponseObject(Status status, String message, Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


    /**
     * Create a success builder with the status set to {@linkplain Status#success SUCCESS}.
     *
     * @return SuccessDataBuilder the created builder
     */
    public static SuccessDataBuilder success() {
        return new ResponseDataBuilder(Status.success);
    }

    /**
     * Create a fail builder with the status set to {@linkplain Status#fail FAIL}.
     *
     * @return FailDataBuilder the created builder
     */
    public static FailDataBuilder fail() {
        return new ResponseDataBuilder(Status.fail);
    }

    /**
     * Create an error builder with the status set to {@linkplain Status#error ERROR}.
     *
     * @return ErrorDataBuilder the created builder
     */
    public static ErrorDataBuilder error() {
        return new ResponseDataBuilder(Status.error);
    }

    /**
     * Create a success ResponseObject with the status set to {@linkplain Status#success SUCCESS} and given payload
     *
     * @param payload response payload data
     * @return ResponseObject
     */
    public static <T> ResponseObject<T> data(T payload) {
        return new ResponseObject<>(Status.success, new ResponseData<>(null, payload));
    }

    /**
     * Internal public interface with success ResponseObject parameters
     */
    public interface SuccessDataBuilder extends DefaultDataBuilder {

        SuccessDataBuilder title(String title);

        <T> ResponseObject<T> data(T payload);
    }

    /**
     * Internal public interface with fail ResponseObject parameters
     */
    public interface FailDataBuilder extends DefaultDataBuilder {

        FailDataBuilder exception(Class<? extends Throwable> throwable);

        FailDataBuilder description(String desc);

    }

    /**
     * Internal public interface with error ResponseObject parameters
     */
    public interface ErrorDataBuilder extends DefaultDataBuilder {

        ErrorDataBuilder message(String message);
    }


    /**
     * Internal public interface with default ResponseObject methods
     */
    public interface DefaultDataBuilder {

        DefaultDataBuilder code(Integer code);

        <T> ResponseObject<T> build();

    }

    /**
     * Internal private builder class
     */
    private static class ResponseDataBuilder implements SuccessDataBuilder, FailDataBuilder, ErrorDataBuilder {

        private final Status status;
        private String title;
        private String desc;
        private String message;
        private Class<? extends Throwable> exception;
        private Integer code;

        public ResponseDataBuilder(Status status) {
            this.status = status;
        }

        @Override
        public SuccessDataBuilder title(String title) {
            Assert.notNull(title, "Title must not be null!");
            this.title = title;
            return this;
        }


        @Override
        public <D> ResponseObject<D> data(@Nullable D payload) {
            return new ResponseObject<>(this.status, new ResponseData<>(this.title, payload));
        }


        @Override
        public FailDataBuilder exception(Class<? extends Throwable> throwable) {
            Assert.notNull(throwable, "Throwable class must not be null!");
            this.exception = throwable;
            return this;
        }

        @Override
        public FailDataBuilder description(String desc) {
            Assert.notNull(desc, "Fail description must not be null!");
            this.desc = desc;
            return this;
        }

        @Override
        public ErrorDataBuilder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public DefaultDataBuilder code(Integer code) {
            this.code = code;
            return this;
        }


        @Override
        @SuppressWarnings("unchecked")
        public ResponseObject<?> build() {
            switch (this.status) {
                case success:
                    return data(null);
                case fail:
                    return new ResponseObject<>(
                            Status.fail,
                            new ResponseData<>(this.exception, this.desc),
                            this.code
                    );
                case error:
                    return new ResponseObject<>(
                            Status.error,
                            this.message,
                            this.code
                    );
                default:
                    throw new RuntimeException("I dont know how this exception can be thrown");
            }
        }


    }


}
