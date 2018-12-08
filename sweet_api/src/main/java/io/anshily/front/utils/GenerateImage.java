package io.anshily.front.utils;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

class myFontM extends FontMetrics{

    /**
     * Creates a new <code>FontMetrics</code> object for finding out
     * height and width information about the specified <code>Font</code>
     * and specific character glyphs in that <code>Font</code>.
     *
     * @param font the <code>Font</code>
     * @see Font
     */
    protected myFontM(Font font) {
        super(font);
    }
}
class StringUtil{
    static String[] stringSplit(String s,int l){
        String[] ss;
        int num = s.length()/l;
        if (s.length()%l == 0){
            ss = new String[num];
            for (int i = 0; i < num; i++){
                ss[i] = s.substring(i*l,(i+1)*l);
            }
        }else {
            ss = new String[num+1];
            for (int i = 0; i < num; i++){
                ss[i] = s.substring(i*l,(i+1)*l);
            }
            ss[num] = s.substring(num*l,s.length());
        }
        return ss;
    }
}
class MyImage extends Canvas{
    Image image ;

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0,0,this);
    }
}
public class GenerateImage{
    static String fontStyle = "宋体";
    static int titleFontSize = 50;//40
    static int contentFontSize = 40;//35
    static int titleX = 0,titleY = 0;
    static int contentX = 0, contentY = 0;
    static int titleLineSpace = 55,contentLineSpace = 45;//55,40
    static int titleLineSize = 14,contentLineSize = 18;//11,15
    static int max = 1200;
    static double scale = 1;
    static double rate = 1;
    public static void myDrawString(String str,int x,int y,double rate,Graphics g){
        String tempStr=new String();
        int orgStringWight=g.getFontMetrics().stringWidth(str);
        int orgStringLength=str.length();
        int tempx=x;
        int tempy=y;
        while(str.length()>0)
        {
            tempStr=str.substring(0, 1);
            str=str.substring(1, str.length());
            g.drawString(tempStr, tempx, tempy);
            tempx=(int)(tempx+(double)orgStringWight/(double)orgStringLength*rate);
        }

    }
    public static BufferedImage getImage(String titles,String contents,String erCode) {
        titles = titles.replaceAll("【","");
        titles = titles.replaceAll("】","");
//        titles=titles.replaceAll("","");
        String reg = "<img.*>";
        contents = contents.replaceAll(reg, "");

        //设置日期数组
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();
        String[] cs = contents.split("<br/>");
        for (int i = 0; i < cs.length; i++){
            sb.append(cs[i]);
        }
        System.out.println(titles);
        //分割字符串设置一行显示数字
        String[] title = StringUtil.stringSplit(""+titles+"", titleLineSize);
        String[] content = StringUtil.stringSplit(contents.replaceAll("<br/>",""), contentLineSize);
        File file = new File(MyImage.class.getResource("/public/imgs/bac.jpg").getPath());
        file.getPath();
        BufferedImage image = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data = format.format(new Date());
            image = ImageIO.read(file);
            Integer orgWidth = image.getWidth();
            Integer orgHeight = image.getHeight();

//            scale = 1;
            initArgument();
            System.out.println(orgHeight+"#####"+orgWidth);
            boolean flag = ifOutOf(titles,contents,scale);
              while (flag){
                   scale += 0.097;
                   titleLineSize += 1;
                   contentLineSize += 1;
                   flag = ifOutOf(titles,contents,scale);
               }
//            scale = 1.3;
            title = StringUtil.stringSplit(""+titles+"", titleLineSize);
              content = StringUtil.stringSplit(contents.replaceAll("<br/>",""), contentLineSize);
              for (String s : title){
                  System.out.println(s);
              }
            System.out.println("contentLineSize is::::"+contentLineSize);
            System.out.println("titleLineSize is::::"+titleLineSize);
            System.out.println("scale is::::"+scale);
            AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(scale, scale), null);
            image = op.filter(image,null);
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            Graphics g = image.createGraphics();
            //判断星期
            cal.setTime(new Date());
            int w = cal.get(Calendar.DAY_OF_WEEK)-1;
            if (w < 0){
                w = 0;
            }
            String weekDay = weekDays[w];

            g.setFont(new Font(fontStyle, Font.PLAIN, 30));
            g.setColor(new Color(58,118,208));
            //画日期
            g.drawString(weekDay,width/5,new Double((395*scale)).intValue());
            g.drawString(data,width/5,new Double((435*scale)).intValue());
//            g.setColor(new Color(77,77,77));
            g.setColor(new Color(50,50,50));
            g.setFont(new Font(fontStyle, Font.BOLD, titleFontSize));
            //画tilte，如果一行就从中间开始画
            if (title.length == 1)
            {
                for (int i = 0; i < title.length; i++) {
//                    g.drawString(title[i], (width-(62*title[i].length())+101)/2, 650 + i * 62);
                    g.drawString(title[i], (width-(62*title[i].length())+101)/2, new Double((505*scale)).intValue() + i * titleLineSpace);
// System.out.println((width-(62*title[i].length()))/2);
                    if (i > 5) break;
                }
            }else {
                for (int i = 0; i < title.length; i++) {
                    g.drawString(title[i], (width / 6)-50, new Double((505*scale)).intValue() + i * titleLineSpace);
                    if (i > 50){
                        g.drawString("...",(width / 6)-50,  new Double((505*scale)).intValue() + i * titleLineSpace);
//                        System.out.println(650 + i * 65);
                        break;
                    }
                }
            }

            g.setFont(new Font(fontStyle, Font.PLAIN, contentFontSize));
            g.setColor(new Color(77,77,77));
            for (int i = 0; i < content.length; i++) {
                if ( (709+(title.length*62) + i * 46)>=13000){
                    g.drawString("...", (width / 6)-50, new Double((520*scale)).intValue()+(title.length*titleLineSpace) + i * contentLineSpace);
//                    System.out.println(709+(title.length*62) + i * 46);
                    break;
                }else {
                    g.drawString(content[i], (width / 6)-50, new Double((520*scale)).intValue()+(title.length*titleLineSpace) + i * contentLineSpace);
                }
            }

//            Frame frame = new Frame();
//            MyImage canvas = new MyImage();
//            canvas.setImage(image);
//            System.out.println(MyImage.class.getResource(erCode).getPath());
//            BufferedImage image1 = ImageIO.read(new File(erCode));

//            frame.setSize(new Dimension(900,900));
//            frame.add(canvas,BorderLayout.CENTER);
//            g.drawImage(image1.getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING), 0, 0, null);
//            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    //判断是否越界
    static boolean ifOutOf(String title,String content, Double scale){
        int titleRowNum = StringUtil.stringSplit(title,titleLineSize).length;
        int contentRowNum = StringUtil.stringSplit(content,contentLineSize).length;
        System.out.println(titleRowNum+"###"+contentRowNum+"###"+scale);
        return (titleRowNum*titleLineSpace + contentRowNum*contentLineSpace)>((max/2)*scale);
    }
    //根据长度分割
    static private String[] splitLongStringIntoStringArray(String longString,
                                                    FontMetrics font, int width) {
        if (font.stringWidth(longString) <= width) {
            return new String[] {longString};
        } else {
            int stringLength = longString.length();
            char[] chars = longString.toCharArray();
            Vector v = new Vector();
            int offset = 0, length = 1;
            while (offset + length < stringLength) {
                if (font.charsWidth(chars, offset, length) < width) {
                    length++;
                } else {
                    v.addElement(longString.substring(offset, offset + length));
                    offset = offset + length;
                    length = 1;
                }
            }
            if (offset < stringLength) {
                v.addElement(longString.substring(offset));
            }
            String[] strings = new String[v.size()];
            v.copyInto(strings);
            return strings;
        }
    }
    static void initArgument(){
         titleFontSize = 49;//40
         contentFontSize = 40;//35
         titleX = 0;titleY = 0;
         contentX = 0; contentY = 0;
         titleLineSpace = 55;contentLineSpace = 45;//55,40
        titleLineSize = 12;contentLineSize = 15;//11,15
         max = 1200;
        scale = 0.97;
    }
}
