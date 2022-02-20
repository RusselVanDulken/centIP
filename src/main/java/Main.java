
import util.ChangeIPUtil;
import java.io.BufferedReader;
import java.io.FileReader;
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
        if (System.getProperties().getProperty("os.name").toLowerCase().contains(linux)) {

            System.out.println("当前为Windows系统");

        } else if (System.getProperties().getProperty("os.name").toLowerCase().contains(windows)){
            System.out.println("请输入修改网卡名：(如：ge1)");
            Scanner scfile = new Scanner(System.in);
            String filename = scfile.next();
            String Linename;
            String Line;
            BufferedReader Br = new BufferedReader(new FileReader("src/main/resources/test/ifcfg-"+filename));
            while ((Line = Br.readLine()) != null) {
                Linename = Line.substring(0, Line.indexOf("="));
                System.out.println(Linename);
            }
            String res="DNS1";

            ChangeIPUtil.setProperty(filename,res,"222");


        }
    }
}
