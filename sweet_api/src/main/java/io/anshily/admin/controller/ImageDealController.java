package io.anshily.admin.controller;

import io.anshily.base.utils.GetProxyImage;
import io.anshily.base.utils.ImageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
public class ImageDealController {

    @RequestMapping("/sys/picture")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {


//        public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {

            String url = request.getParameter("url");

            System.out.println(url);

            //获取图片信息
            try {
                ImageData image = GetProxyImage.getImage(url);

                System.out.println(image);

                if(image!=null){
                    //将获取到的图片返回
                    response.setContentType(image.getContentType());
                    //设置浏览器缓存
                    response.setHeader("Cache-Control", "max-age=31536000");
                    OutputStream out = response.getOutputStream();
                    out.write(image.getData());
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


//
//        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
//
//        request.setCharacterEncoding("utf-8");  //设置编码
//
//        String randomName = UUID.randomUUID().toString() + ".jpg";
//        String imageName = getDate() + "/" + randomName;
//        //服务器真正存放的路径
//        String fileName = Constants.PATH_IMAGE_PATH + imageName;
//        // 保存图片到本地
//        File file = new File(fileName);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//
//        OutputStream out = new FileOutputStream(file);
//        InputStream in = request.getPart("file").getInputStream();
//        int length = 0;
//        byte[] buf = new byte[1024];
////             in.read(buf); 每次读到的数据存放在buf 数组中
//        while ((length = in.read(buf)) != -1) {
////                //在buf数组中取出数据写到（输出流）磁盘上
//            out.write(buf, 0, length);
//        }
//        in.close();
//        out.close();
//
//        UserExtend userExtend = new UserExtend();
//        userExtend.setInfo(fileName);
//        user.setHeader(fileName);
//
//        user.setUsername(userExtend.getNickname());
//        user.setLast_update_time(new Date());
//
//        sysUserService.update(user);
//
//
//
//        userExtend.setUid(user.getId());
//        userExtend.setId(user.getId());
////        userExtendService.findbyUid();
//        try{
//            userExtendService.save(userExtend);
//        }catch (Exception e){
//            userExtendService.update(userExtend);
////            userExtendService.updateByUid(userExtend);
//        }
//
//
//        PrintWriter printWriter = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        HashMap<String, Object> res = new HashMap<String, Object>();
//
//
//
//        res.put("success", true);
//        res.put("url",fileName);
//
//
//        printWriter.write(JSON.toJSONString(res));
//        printWriter.flush();
//
//    }
}
