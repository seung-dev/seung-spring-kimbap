package seung.spring.boot.conf.web.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.util.SLinkedHashMap;

@ApiModel(value = "Response model", description = "응답 객체")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "hiddenBuilder")
@Getter
@Setter
public class SResponse {

    @ApiModelProperty(
            dataType = "String"
            , value = "오류코드"
            , notes = "0000: 성공, XXXX: 기타 오류"
            , example = "0000"
            )
    private String error_code;
    
    @ApiModelProperty(
            dataType = "String"
            , value = "오류메시지"
            , notes = ""
            , example = "Required String parameter 'col01' is not present"
            )
    private String error_message;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "오청내용"
            , notes = "요청내용중 확인이 필요한 필드 반환"
            , example = "{\"request_code\": \"81967e1b-82b6-452e-808c-3bf544c3e10c\"}"
            )
    private SLinkedHashMap request;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "결과 내용"
            , notes = ""
            , example = "{}"
            )
    private SLinkedHashMap data;
    
    public static SResponseBuilder builder() {
        return hiddenBuilder()
                .error_code("")
                .error_message("")
                .request(new SLinkedHashMap())
                .data(new SLinkedHashMap())
                ;
    }
    
    public static SResponseBuilder builder(SLinkedHashMap request) {
        return hiddenBuilder()
                .error_code("")
                .error_message("")
                .request(request)
                .data(new SLinkedHashMap())
                ;
    }
    
    @SuppressWarnings("unchecked")
    public void putData(Object key, Object value) {
        this.data.put(key, value);
    }
    
}
