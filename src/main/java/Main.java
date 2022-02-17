import model.InfoEntity;
import util.ChangeIPUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/16	当前系统日期
 * @time 8:47	当前系统时间
 */
//src/main/resources/test/ifcfg-ens33
public class Main {
    public static void main(String[] args) throws IOException {
        String windows = "windows";
        String linux = "linux";
        if (System.getProperties().getProperty("os.name").toLowerCase().contains(linux)) {

            System.out.println("当前为Windows系统");

        } else if (System.getProperties().getProperty("os.name").toLowerCase().contains(windows)){
            System.out.println("请输入文件路径：(如：/etc/sysconfig/network-scripts/ifcfg-ens33)");
            Scanner scfile = new Scanner(System.in);
            String filename = scfile.next();
            String Linename;
            String Line;
            BufferedReader Br = new BufferedReader(new FileReader(filename));
            while ((Line = Br.readLine()) != null) {
                Linename = Line.substring(0, Line.indexOf("="));
                System.out.println(Linename);
            }
            System.out.println("请输入需要更改的选项数：");
            Scanner scval = new Scanner(System.in);
            int val = scval.nextInt();
            System.out.println("请输入需要更改的选项：");
            Scanner scname = new Scanner(System.in);
            String[] name = new String[val];
            for (int i = 0; i < name.length ; i++) {
                String b = scname.next();
                name[i] = b;
            }
//        String filename = "src/main/resources/ifcfg-ens33.txt";
            ChangeIPUtil.setIP(filename,name);
        }
    }
}
