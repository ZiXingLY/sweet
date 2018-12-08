package io.anshily.front.utils;

//import com.qy.front.service.QyUpordownService;
import io.anshily.front.service.FlashService;
import io.anshily.front.utils.utilsPOJO.Live;
//import com.qy.model.QyUpordown;
import io.anshily.model.Flash;
        import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

        import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JinseRequest {

    @Autowired
    private FlashService flashService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void requestNews() throws IOException, SQLException {
        logger.info("开始获取");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.net.URLEncoder.encode("xxxx","utf-8");
        Date getDate = new Date();
        String s = "http://api.coindog.com/live/list?access_key=9aa0f69035f2b768a559831900d55fb1&id=0&flag=down&limit=100";
//        URL url = new URL("http://api.coindog.com/live/list?id=0&flag=down&limit=100");
        URL url = new URL(s);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        if (connection.getResponseCode() == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String liner;
            StringBuffer buffer=new StringBuffer();
            while ((liner=br.readLine())!=null) {
                buffer.append(liner);
            }

            JSONObject object = JSONObject.fromObject(buffer.toString());
            JSONArray array = object.getJSONArray("list");
            for (int i = 0; i < array.size(); i++){
                JSONObject olive = array.getJSONObject(i);
                JSONArray livesArr = JSONArray.fromObject(olive.get("lives"));
                List<Live> ls = (List<Live>) JSONArray.toCollection(livesArr,Live.class);
                for (int j = 0 ; j < livesArr.size(); j++){
                    String title = "";
                    String content = "";
                    String imgs = "";
//                    System.out.print("12312313132");
                    Pattern pattern = Pattern.compile("([\\【].+[\\】])(.+)");
                    Matcher matcher = pattern.matcher(ls.get(j).getContent());
                    List<Flash> flashList = new ArrayList<>();
                    if (matcher.find()){
//                        System.out.println("title====="+matcher.group(1));
//                        System.out.println("content======="+matcher.group(2));
                        title = matcher.group(1);
                        content = matcher.group(2);
                        flashList = flashService.findByTitle(title);
                    }
                    if (flashList.size() > 0){
                        continue;
                    }
                    JSONObject li = (JSONObject) livesArr.get(j);
                    JSONArray images = (JSONArray) li.get("images");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int k = 0; k < images.size(); k++){
                        JSONObject img = images.getJSONObject(k);
//                        System.out.println("img======"+img.get("url"));
                        stringBuilder.append("<img src=\""+img.get("url").toString()+"\">");
                    }
                    imgs = stringBuilder.toString();
//                    PreparedStatement ps = conn.prepareStatement("insert into article(atitle,uid,acontent,addtime,readnumber,state,isbanner,category,author,source)" +
////                            "values (?,?,?,?,?,?,?,?,?,?)");
                    Flash flashToS = new Flash();
                    flashToS.setTitle(title);
                    flashToS.setContent(content+imgs);
                    flashToS.setAddtime(new Date());
                    flashToS.setReadnumber(1000);
                    flashService.save(flashToS);
                    System.out.println("save===========================");
//                    PreparedStatement udp = conn.prepareStatement("insert into qy_upordown (up, down, aid) (select 0,0,aid from article where atitle = ?)");
                }
            }
        }
    }
}

