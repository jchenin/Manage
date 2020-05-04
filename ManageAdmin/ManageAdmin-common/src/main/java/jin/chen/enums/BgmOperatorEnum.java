package jin.chen.enums;

public enum BgmOperatorEnum {
    ADD("1", "添加bgm"),
    DELETE("2", "删除bgm");

    public final String type;
    public final String value;

    BgmOperatorEnum(String type, String value){
        this.type = type;
        this.value = value;
    }
    public String getUserType() {
        return type;
    }

    public String getValue() {
        return value;
    }



    public static String getKey(String key){
        for (BgmOperatorEnum type : BgmOperatorEnum.values()){
            if(type.getUserType().equals(key)){
                return type.value;
            }
        }
        return null;
    }
}
