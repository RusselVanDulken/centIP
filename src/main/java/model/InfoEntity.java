package model;

/**
 * @author 7w1st22
 * @package_name model    创建新文件的包的名称
 * @date 2022/2/16	当前系统日期
 * @time 9:58	当前系统时间
 */
public class InfoEntity {
    private String value;
    private String name;

    public InfoEntity() { }
    public InfoEntity(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
