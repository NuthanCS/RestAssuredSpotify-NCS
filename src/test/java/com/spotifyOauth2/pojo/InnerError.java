package com.spotifyOauth2.pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

//@Getter
//@Setter
@Data
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InnerError {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
}
