import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/17	当前系统日期
 * @time 8:42	当前系统时间
 */
public class ping {
    public static void main(String[] args) {
        try {
            // systemctl restart network
            Process p = Runtime.getRuntime().exec("ping 192.168.103.1");  //调用Linux的相关命令

            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);      //创建IO管道，准备输出命令执行后的显示内容

            String line;
            while ((line = input.readLine()) != null) {     //按行打印输出内容
                System.out.println(line);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
