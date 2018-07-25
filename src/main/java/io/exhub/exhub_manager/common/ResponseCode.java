package io.exhub.exhub_manager.common;

/**
 * 响应状态枚举
 * @date 2018/7/25
 * @author
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    THE_ASSET_CANNOT_WITHDRAW(210,"the asset cannot be withdrawed"),
    BALANCE_NOT_ENOUGH(211,"balance not enough"),
    DAILY_BALANCE_NOT_ENOUGH(212,"daily balance not enough"),
    NOT_OPEN_GOOGLE_VERIFY(207, "not open google verify"),
    OPEN_GOOGLE_VERIFY(208, "open google verify"),

    TOKEN_NOT_EXIST(401, "token not exist"),
    GOOGLE_FAILURE(418, "google verify failed"),
    GEETEST_FAILURE(419, "geetest failed"),
    REQUEST_PARAMS_TAMPER(421, "the request parameter were tampered"),
    PARAMETER_ERROR(422, "the request parameter is illegal"),
    PASSWORD_ERROR(423, "password error"),
    EMAIL_VALIDATECODE_ERROR(424, "email validate code error"),
    INVITATION_CODE_NOT_EXIST(425, "invitation code not exist"),
    ACCOUNT_NOT_EXIST(426, "account not exist"),
    SEND_EMAIL_FAILURE(427, "send email failed"),
    ACCOUNT_FROZEN(428, "account frozen"),
    TOKEN_VERIFY_FAILURE(429, "token verify failed"),
    UPLOAD_FILE_FAILURE(430, "upload file failed"),
    NO_DATA(431, "no data"),

    HTTPCLIENT_ERROR(500,"invoke httpClient error"),
    DB_ERROR(504,"database error");

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
