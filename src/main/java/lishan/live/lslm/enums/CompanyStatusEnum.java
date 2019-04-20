package lishan.live.lslm.enums;

/**
 * @ClassName CompanyStatusEnum
 * @Description
 * @Author tim
 * @Date 2019-04-15 16:42
 * @Version 1.0
 */
public enum CompanyStatusEnum implements CodeEnum {
    UN_AUDITED(0, "未审核"),
    PASS_AUDITED(1, "通过审核"),
    NOT_PASS_AUDITED(2, "未通过审核");

    private Integer code;
    private String message;

    CompanyStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
