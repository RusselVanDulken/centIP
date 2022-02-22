package util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Type;

/**
 * @author 7w1st22
 * @package_name util    创建新文件的包的名称
 * @date 2022/2/22	当前系统日期
 * @time 14:12	当前系统时间
 */
public class SetProperty {
    //ip dns1 dns2 gateway prefix
    //nmcli c add con-name ens38 ifname ens38 type ethernet ip4 192.168.103.177 gw4 192.168.103.1  创建文件
    private static final String file = "nmcli c add ";// 创建文件 nmcli c add
    private static final String modifyip = "nmcli c mod ";// 修改
    private static final String activateip = "nmcli c up ";// 生效
    private static final String modifydns = "nmcli con modify";// 配置dns
    private static final String activatecon = "nmcli con up ";// 激活con
    private static final String deleatefile = "nmcli con delete ";// 激活con
    private static final String TYPE = " Ethernet ";
    private static final String[] fileset = {"ge1","ge2","ge3","ge4","ge5"};

    public static void setProperty(String filename, String IPADDR,String DNS1,String DNS2,String GATEWAY,String MASK)throws IOException {
        Integer PREFIX = exchange(MASK);
        deleateFile(fileset);
        Runtime.getRuntime().exec(file+" type "+TYPE+" con-name "+filename+" ifname "+filename+" ip4 "+IPADDR+"/"+PREFIX+" gw4 "+GATEWAY);
        Runtime.getRuntime().exec(activateip+" "+filename);
        Runtime.getRuntime().exec(modifydns+" "+filename+" ipv4.dns "+DNS1);
        Runtime.getRuntime().exec(modifydns+" "+filename+" +ipv4.dns "+DNS2);
        Runtime.getRuntime().exec(activatecon+" "+filename);
        Runtime.getRuntime().exec("nmcli connection reload");
        Runtime.getRuntime().exec("nmcli connection up "+filename);
    }

    public static void deleateFile(String[] fileset) throws IOException {
        for(String filename:fileset){
            Runtime.getRuntime().exec(deleatefile+filename);
        }
    }



    public static int exchange(String netmask) {
        String data[] = netmask.split("\\.");
        int len = 0;
        for (String n : data) {
            len += (8 - Math.log(256 - Integer.valueOf(n)) / Math.log(2));
        }
        return len;
    }
}