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
 * @path /etc/sysconfig/network-scripts/ifcfg-
 * @filename ge1 ge2 ge3
 */
public class ChangeIPUtil{
    static String path = "src/main/resources/test/ifcfg-";
    public static void setProperty(String filename, String resetname,String resetvalue) {
        BufferedReader br ;
        String line ;
        try {

            List<InfoEntity> InfoList = new ArrayList<>();
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(path+filename));
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            if(PreConUtil.getProperty(path+filename,resetname).equals("notexisted")){
                while ((line = br.readLine()) != null) {
                    InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")),line.substring(line.indexOf("=") + 1));
                    InfoList.add(newinfo);
                }
                InfoEntity addinfo = new InfoEntity(resetname.toUpperCase(),resetvalue.toUpperCase());
                InfoList.add(addinfo);//新属性及内容添加进list
            }else{
                while ((line = br.readLine()) != null) {
                    InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")),line.substring(line.indexOf("=") + 1));
                    if (line.substring(0, line.indexOf("=")).equals(resetname.toUpperCase())) {
                        newinfo.setValue(resetvalue);//原属性修改值
                    }
                    InfoList.add(newinfo);
                }
            }
//            for(String check_caught: resetname){
//                boolean caught = false;
//                for(InfoEntity fail: InfoList){
//                    check_caught = check_caught.toUpperCase();
//                    if (fail.getName().equals(check_caught)){
//                        caught = true;
//                        break;
//                    }
//                }
//                if(!caught)System.out.println(check_caught+"不存在,检查输入是否有误");
//            }
            StringBuilder infoStringBuffer = new StringBuilder();
            for (InfoEntity Info : InfoList) {
                infoStringBuffer.append(Info.getName()).append("=").append(Info.getValue()).append("\r\n");
            }
//               System.out.println(infoStringBuffer.toString());
            FileWriter fw=new FileWriter(path+filename);
            fw.write(infoStringBuffer.toString());
            fw.close();
            PreConUtil.Prevention(filename);// Runtime.getRuntime().exec("systemctl restart network");
            String uuid = PreConUtil.getProperty(path+filename,"UUID");
            Runtime.getRuntime().exec("nmcli con up "+uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

