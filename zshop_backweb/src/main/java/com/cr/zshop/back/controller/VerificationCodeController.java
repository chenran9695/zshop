package com.cr.zshop.back.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ��cr
 * @date ��Created in 2019/3/20 9:49
 * @modified By��
 * @version: 0.0.1
 */
@Service
@RequestMapping("/back/verificationCode")
public class VerificationCodeController {
    /**
    * @Param: [request, response]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/20
    */ 
    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");

        BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);
        Graphics g = bfi.getGraphics();
        g.fillRect(0, 0, 80, 25);

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r = new Random();
        int index;
        StringBuffer sb = new StringBuffer(); //�����ַ���
        for(int i=0; i<4; i++){
            index = r.nextInt(ch.length);
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            Font font = new Font("����", 30, 20);
            g.setFont(font);
            g.drawString(ch[index]+"", (i*20)+2, 23);
            sb.append(ch[index]);
        }

        int area = (int) (0.01 * 60 * 20);
        for(int i=0; i<area; ++i){
            int x = (int)(Math.random() * 80);
            int y = (int)(Math.random() * 25);
            bfi.setRGB(x, y, (int)(Math.random() * 255));
        }

        for (int i = 0; i < 3; i++) {
            int xstart = (int)(Math.random() * 80);
            int ystart = (int)(Math.random() * 25);
            int xend = (int)(Math.random() * 80);
            int yend = (int)(Math.random() * 25);
            g.setColor(interLine(1, 255));
            g.drawLine(xstart, ystart, xend, yend);
        }
        HttpSession session = request.getSession();
        session.setAttribute("verificationCode", sb.toString());
        ImageIO.write(bfi, "JPG", response.getOutputStream());  //д�������
    }
    /**
    * @Param: [code, session]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: cr
    * @Date: 2019/3/20
    */ 
    @RequestMapping("/check")
    @ResponseBody
    public Map<String,Object> checkVerificationCode(String verificationCode, HttpSession session){
        String code = (String)session.getAttribute("verificationCode");
        Map<String,Object> map = new HashMap<>();
        if(code.equalsIgnoreCase(verificationCode)){
            map.put("valid",true);
        }
        else{
            map.put("valid",false);
        }
        return map;
    }
    /**
    * @Param: [Low, High]
    * @return: java.awt.Color
    * @Author: cr
    * @Date: 2019/3/20
    */ 
    private static Color interLine(int Low, int High){
        if(Low > 255)
            Low = 255;
        if(High > 255)
            High = 255;
        if(Low < 0)
            Low = 0;
        if(High < 0)
            High = 0;
        int interval = High - Low;
        int r = Low + (int)(Math.random() * interval);
        int g = Low + (int)(Math.random() * interval);
        int b = Low + (int)(Math.random() * interval);
        return new Color(r, g, b);
    }
}
