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
    public static void setIP(String filename, String[] resetname,String resetvalue) throws IOException {
        BufferedReader br = null;
        String line = null;
        try {
            List<InfoEntity> InfoList = new ArrayList<>();
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filename));
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")),line.substring(line.indexOf("=") + 1));
                for (String check:resetname) {
                    if (line.substring(0, line.indexOf("=")).equals(check.toUpperCase())) {
//                        System.out.println("请输入" + check.toUpperCase() + "改动后的值：");
//                        resetvalue = new Scanner(System.in).next();
                        newinfo.setValue(resetvalue);
                    }
                }
                InfoList.add(newinfo);
            }
            for(String check: resetname){
                boolean caught = false;
                for(InfoEntity fail: InfoList){
                    check = check.toUpperCase();
                    if (fail.getName().equals(check)){
                        caught = true;
                        break;
                    }
                }
                if(!caught)System.out.println(check+"不存在,检查输入是否有误");
            }
            StringBuilder infoStringBuffer = new StringBuilder();
            for (InfoEntity Info : InfoList) {
                infoStringBuffer.append(Info.getName()).append("=").append(Info.getValue()).append("\r\n");
            }
//               System.out.println(infoStringBuffer.toString());
            FileWriter fw=new FileWriter(filename);
            fw.write(infoStringBuffer.toString());
            fw.close();
            // Runtime.getRuntime().exec("systemctl restart network");  //调用Linux的相关命令
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

