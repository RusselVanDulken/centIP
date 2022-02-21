package util;

import model.InfoEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 7w1st22
 * @package_name util    创建新文件的包的名称
 * @date 2022/2/18	当前系统日期
 * @time 13:48	当前系统时间
 */
public class PreConUtil {

    /**
     * @param file 网卡名
     * 检查与其他网卡是否ip相同冲突
     */
//    public static String path = "src/main/resources/test/ifcfg-";
    public static String path = "/etc/sysconfig/network-scripts/ifcfg-";
    public static void Prevention(String file) throws IOException {
        String[] filename_all={"ge1","ge2","ge3"};
        //  path+file     src/main/resources/test/ifcfg-  ge1
        for(String checkfile:filename_all){
            if(!file.equals(checkfile)){
                String pathip= getProperty(path+file,"IPADDR");
                String otherip=getProperty(path+checkfile,"IPADDR");
                if(pathip.equals(otherip)){
                    Initialize(file);
                    break;
                }
            }
        }
    }

    /**
     * 网卡名与其他冲突 置为空
     * @param file 网卡名
     * @throws IOException
     */
    private static void Initialize(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path+file));
        String line ;
        StringBuilder infoStringBuffer = new StringBuilder();
        List<InfoEntity> InfoList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if(!line.substring(0, line.indexOf("=")).equals("IPADDR")) {
                InfoEntity newinfo = new InfoEntity(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=") + 1));
                InfoList.add(newinfo);
            }else{
                InfoEntity newinfo = new InfoEntity("IPADDR", "");
                InfoList.add(newinfo);
            }
        }
        for (InfoEntity Info : InfoList) {
            infoStringBuffer.append(Info.getName()).append("=").append(Info.getValue()).append("\r\n");
        }
        FileWriter fw=new FileWriter(path+file);
        fw.write(infoStringBuffer.toString());
        fw.close();
    }

    /**
     * @author 7w1st22
     * @package_name util    创建新文件的包的名称
     * @date 2022/2/18	当前系统日期
     * @time 13:48	当前系统时间
     * @result 返回查询属性结果 存在返回property的value 不存在则返回notexisted
     * @param property  属性值
     * @param confpath  绝对路径
     */
    public static String getProperty(String confpath,String property){
        BufferedReader br ;
        property=property.toUpperCase();
        String line ;
        String result = new String();
        try{
            br = new BufferedReader(new FileReader(confpath));
            while ((line = br.readLine()) != null) {
                if(line.substring(0, line.indexOf("=")).equals(property)){
                    result=line.substring(line.indexOf("=") + 1);
                    break;
                }else{
                    result = "notexisted";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
