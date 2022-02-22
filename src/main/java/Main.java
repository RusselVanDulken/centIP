
import util.ChangeIPUtil;
import util.SetProperty;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/16	当前系统日期
 * @time 8:47	当前系统时间
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String windows = "windows";
        String linux = "linux";
        if (System.getProperties().getProperty("os.name").toLowerCase().contains(windows)) {
            System.out.println("当前为Windows系统");
        } else if (System.getProperties().getProperty("os.name").toLowerCase().contains(linux)){
//            System.out.println("请输入修改网卡名：(如：ge1)");
//            Scanner scfile = new Scanner(System.in);
//            String filename = scfile.next();
//            String res="IPADDR";
//            ChangeIPUtil.setProperty(filename,res,"192.168.103.178");
            SetProperty.setProperty("ge1","192.168.103.155","114.114.114.114","8.8.8.8","192.168.103.1","255.255.0.0");
        }
    }
}
