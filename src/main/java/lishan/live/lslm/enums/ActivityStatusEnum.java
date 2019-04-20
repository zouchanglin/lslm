package lishan.live.lslm.enums;

public enum ActivityStatusEnum implements CodeEnum {
    UN_AUDITED(0, "未审核"),
    PASS_AUDITED(1, "通过审核"),
    NOT_PASS_AUDITED(2, "未通过审核");

    private Integer code;
    private String message;
    ActivityStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return this.code;
    }
}
