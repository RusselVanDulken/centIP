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
    static String path = "/etc/sysconfig/network-scripts/ifcfg-";

    /**
     * 临时系统ip 文件未修改
     * @param ner 网卡名
     * @param ip 需要修改的ip
     */
    public static void sysctlIP(String ner,String ip){
        try {
            Process p = Runtime.getRuntime().exec("ifconfig "+ ner + " "+ip+" netmask 255.255.255.0");  //调用Linux的相关命令

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

    /**
     * 对配置文件进行修改
     * @param filename 网卡名
     * @param resetname 修改属性名
     * @param resetvalue 修改值
     */
    public static void setProperty(String filename, String resetname,String resetvalue) {
        String line ;
        if (resetname.equals("IPADDR")){
            sysctlIP(filename,resetvalue);
        }
        try {
            List<InfoEntity> InfoList = new ArrayList<>();
            // 根据文件路径创建缓冲输入流
            BufferedReader br = new BufferedReader(new FileReader(path+filename));
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            if(PreConUtil.getProperty(path+filename,resetname).equals("notexisted")){
                while ((line = br.readLine()) != null) {
                    //不存在则添加
                    InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")),line.substring(line.indexOf("=") + 1));
                    InfoList.add(newinfo);
                }
                InfoEntity addinfo = new InfoEntity(resetname.toUpperCase(),resetvalue.toUpperCase());
                InfoList.add(addinfo);//新属性及内容添加进list
            }else{
                while ((line = br.readLine()) != null) {
                    InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")),line.substring(line.indexOf("=") + 1));
                    if (line.substring(0, line.indexOf("=")).equals(resetname.toUpperCase())) {
                        newinfo.setValue(resetvalue);//已存在原属性直接修改值
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
            FileWriter fw=new FileWriter(path+filename);
            fw.write(infoStringBuffer.toString());
            fw.close();
            PreConUtil.Prevention(filename);//检测是否冲突
            Runtime.getRuntime().exec("nmcli connection reload");
            Runtime.getRuntime().exec("nmcli connection up "+filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

