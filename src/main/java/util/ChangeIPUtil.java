package util;

import model.InfoEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/15	当前系统日期
 * @time 17:26	当前系统时间
 * @filename 绝对路径
 */
public class ChangeIPUtil{
    public static void setIP(String filename, String resetname) throws IOException {
        //  操作系统 System.getProperties().getProperty("os.name");
        boolean caught = false;
        BufferedReader br = null;
        String line = null;
        try {
            List<InfoEntity> InfoList = new ArrayList<>();
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filename));
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")));
                if(line.substring(0, line.indexOf("=")).equals(resetname.toUpperCase())){
                    System.out.println("请输入改动后的值：");
                    newinfo.setValue(new Scanner(System.in).next());
//                    Runtime.getRuntime().exec("systemctl restart network");  //调用Linux的相关命令
                    caught = true;
                }else{
                    newinfo.setValue(line.substring(line.indexOf("=")+1));
                }
                InfoList.add(newinfo);
            }
            if(!caught){
                System.out.println("核对更改选项输入是否正确");
            }
            StringBuilder infoStringBuffer = new StringBuilder();
            for (InfoEntity Info : InfoList) {
                infoStringBuffer.append(Info.getName()).append("=").append(Info.getValue()).append("\r\n");
            }
//               System.out.println(infoStringBuffer.toString());
            FileWriter fw=new FileWriter(filename);
            fw.write(infoStringBuffer.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

