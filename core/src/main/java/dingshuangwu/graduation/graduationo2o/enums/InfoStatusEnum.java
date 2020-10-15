package dingshuangwu.graduation.graduationo2o.enums;

public enum InfoStatusEnum {
    NORMAL("normal"),
    DELETED("deleted"),
    UPDATED("updated"),
    DEFAULT("default"),
    APPLYING("applying"),
    REJECT("reject"),
    VERIFIED("verified"),
    ;
    private String value;

    InfoStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
