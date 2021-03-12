/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 上午11:36
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.OrderUser;
import com.gq.entity.TheMac;
import com.gq.entity.WorkOrder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputText {

    public static List<OrderUser> readTheOrder(String filePath)
    {
        File readFile = new File(filePath);
        BufferedReader reader = null;
        List<OrderUser> result = new ArrayList<>();

        try
        {
            reader = new BufferedReader(new FileReader(readFile));
            String temp = null;
            while ((temp = reader.readLine()) != null)
            {
                String[] order = temp.split("\t");
                result.add(new OrderUser(order[0],order[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static List<WorkOrder> readWorkOrder(String filePath)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        File readFile = new File(filePath);
        BufferedReader reader = null;
        List<WorkOrder> result = new ArrayList<>();

        try{
            reader = new BufferedReader(new FileReader(readFile));
            String temp = null;
            while ((temp = reader.readLine()) != null)
            {
                int tempNum = 0;
                String[] split = temp.split("\t");
                WorkOrder order = new WorkOrder();

                order.setId(split[tempNum++]);
                order.setCreateDate(sdf.parse(split[tempNum++]));
                order.setDeclarer(split[tempNum++]);
                order.setDeclarerLocation(split[tempNum++]);
                order.setDeclareType(split[tempNum++]);
                order.setPhone(split[tempNum++]);
                order.setCallInTime(sdf.parse(split[tempNum++]));
                order.setAcceptedBy(split[tempNum++]);
                order.setAcceptanceTime(sdf.parse(split[tempNum++]));
                order.setEventTitle(split[tempNum++]);
                order.setEventClassification(split[tempNum++]);
                order.setEventNature(split[tempNum++]);
                order.setEventLevel(split[tempNum++]);
                order.setEventDescription(split[tempNum++]);
                order.setSolution(split[tempNum++]);
                order.setSolver(split[tempNum++]);
                order.setPlanTime(sdf.parse(split[tempNum++]));
                order.setCondition(split[tempNum++]);

                result.add(order);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static List<TheMac> readTheMAC(String filePath)
    {
        File readFile = new File(filePath);
        BufferedReader reader = null;
        List<TheMac> result = new ArrayList<>();

        try
        {
            reader = new BufferedReader(new FileReader(readFile));
            String temp = null;
            while ((temp = reader.readLine()) != null)
            {
                String[] order = temp.split("\t");
                if (order.length<=2)
                    result.add(new TheMac(order[0],order[1],null));
                else if (order.length<=3)
                    result.add(new TheMac(order[0],order[1],order[2]));
                else if (order.length>3) {
                    String m = "";
                    for (int i = 2; i < order.length; i++)
                    {
                        m = order[i] + " ";
                    }
                    result.add(new TheMac(order[0],order[1],m));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String outputText(String outPath, Map<OrderUser,TheMac> macMap)
    {
       File writerFile = new File(outPath);
       OutputStream writer = null;
        try {
            writer = new FileOutputStream(writerFile);
            for (Map.Entry<OrderUser,TheMac> entry : macMap.entrySet())
            {
                OrderUser key = entry.getKey();
                String user = key.getId() + "\t" + key.getUsername();
                TheMac value = entry.getValue();
                String theMac = value.getUser() + "\t" + value.getLocation() + "\t" + value.getTheMAC();
                byte[] u = user.getBytes();
                byte[] m = theMac.getBytes();
                writer.write(u);
                writer.write('\r');
                writer.write(m);
                writer.write('\r');
                writer.write('\r');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "写入失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "写入失败";
        }finally {
            try {
                if (writer == null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "写入失败";
            }
        }
        return "写入完成";
    }
}
