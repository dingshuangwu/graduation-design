package dingshuangwu.graduation.graduationo2o.enums;

public enum JurisdictionEnum {
    YES(true),
    NO(false),
    ;

    private Boolean value;

    JurisdictionEnum(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }
}
