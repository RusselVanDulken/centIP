import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/17	当前系统日期
 * @time 10:37	当前系统时间
 */
public class str {
    public static void main(String[] args) {
//        String filename = "src/main/resources/ifcfg-ens33";
//        System.out.println(filename.indexOf("/"));
//
//        String n="fsjakhdsjkahfjkdshalfhdsahjfhdsalfjds";
////        fsjakhdsjkahfjkdshalfhdsahjfhdsal fjds
//        n=n.substring(0,n.lastIndexOf("f"));
//        System.out.println(n);
        System.out.println("请输入需要更改的选项数：");
        Scanner scval = new Scanner(System.in);
        int val = scval.nextInt();
        System.out.println("请输入需要更改的选项：");
        Scanner scname = new Scanner(System.in);
        String name[] = new String[val];
        for (int i = 0; i < name.length-1 ; i++) {
            String b = scname.next();
            name[i] = b;
        }
        System.out.println(name[1]);
    }
}
