package dingshuangwu.graduation.graduationo2o.enums;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
public enum TableEnum {
    ROOT("dp_graduation_root"),
    USER("dp_graduation_user"),
    MANAGEMENT("dp_graduation_management"),
    PUBLISH("dp_graduation_publish"),
    APPLY("dp_graduation_apply"),
    JURISDICTION("dp_graduation_jurisdiction"),
    USERINFOMATION("dp_graduation_user_info"),
    MYRESUME("dp_graduation_my_resume"),
    REALNAMEAUTHENTICATION("dp_graduation_real_name_authentication"),
    APPLYFORMANAGEMENT("dp_graduation_apply_for_management"),
    ;

    private String tableName;

    TableEnum(String tableName) {
        this.tableName = tableName;
    }

    public String getValue() {
        return this.tableName;
    }
}
