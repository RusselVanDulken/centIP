import com.sun.glass.ui.Clipboard;
import model.InfoEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 7w1st22
 * @package_name PACKAGE_NAME    创建新文件的包的名称
 * @date 2022/2/16	当前系统日期
 * @time 10:34	当前系统时间
 */
public class test {

    public static void main(String[] args) throws IOException {

        String filePath = "src/main/resources/test/ifcfg-ens33.txt";
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<InfoEntity> InfoList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            InfoEntity newinfo = new InfoEntity();
            newinfo.setName(line.substring(0, line.indexOf("=")));
            if(line.substring(0, line.indexOf("=")).equals("IPADDR")){
                newinfo.setValue("0.1.1.1");
            }else{
                newinfo.setValue(line.substring(line.indexOf("=")+1));
            }
            InfoList.add(newinfo);
        }


        StringBuilder infoStringBuffer = new StringBuilder();
        for (InfoEntity Info : InfoList) {
            infoStringBuffer.append(Info.getName()).append("=").append(Info.getValue()).append("\r\n");
        }
//        System.out.println(infoStringBuffer.toString());

        FileWriter fw=new FileWriter("src/main/resources/test/ifcfg-ens33.txt");
        fw.write(infoStringBuffer.toString());
        fw.close();
    }
}
