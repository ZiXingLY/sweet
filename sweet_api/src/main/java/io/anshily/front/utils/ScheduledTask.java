package io.anshily.front.utils;

//import com.qy.front.service.QyUpordownService;
import org.springframework.stereotype.Component;

/**
 * Created by lightClouds917
 * Date 2018/8/23
 * Description:定时任务
 */
@Component
public class ScheduledTask {
//
//    @Autowired
//    private JinseRequest jinseRequest;
//
//    @Autowired
//    private ArticleService articleService;
//
//    @Autowired
//    private FlashService flashService;
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    /**
//     * 每间隔五分钟秒输出时间
//     */
//    @Scheduled(initialDelay=100,fixedRate = 300000)
//    public void logTime() throws IOException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        logger.info("定时任务，现在时间："+format.format(System.currentTimeMillis()));
//    }
//    @Scheduled(initialDelay=200,fixedRate = 300000)
//    public void logName(){
//        try {
//            jinseRequest.requestNews();
//        } catch (IOException e) {
//            logger.info("io异常");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            logger.info("sql异常");
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Scheduled(initialDelay=0,fixedRate = 1800000)
//    public void addReadNumber(){
//        logger.info("阅读量增加500");
//        System.out.println(articleService.addReadNumber());
//        System.out.println(flashService.addReadNumber());
//    }
}
