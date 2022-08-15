package serialization.jackson.sensitive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：zeqi
 * @Date: Created in 16:19 8/5/18.
 * @Description:
 */
@Data
public class UserDetailResponse {

    private Long useId;

    @ApiModelProperty(value = "用户编号")
    private String useNo;

    @ApiModelProperty(value = "用户姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String useName;

    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户性别")
    private String sex;

    @ApiModelProperty(value = "用户年龄")
    private int age;

    @ApiModelProperty(value = "用户籍贯")
    private String nativePlace;

    @SensitiveInfo(SensitiveType.ID_CARD)
    @ApiModelProperty(value = "用户身份证号")
    private String idCard;

    @ApiModelProperty(value = "用户身份证号")
    private String borrowingLevel;
}