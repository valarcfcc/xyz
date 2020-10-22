package com.valarcfcc.xyz;

import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.constant.Season;
import com.valarcfcc.xyz.utils.BeanUtils;
import com.valarcfcc.xyz.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.valarcfcc.xyz.utils.Common.println;
@SpringBootTest
@Slf4j
public class setTest {
    @Test
    public void toStringTest(){

        User user = new User();
        user.setAge(1);
        user.setId(1l);
        user.setName("111");
        BeanUtils.readAttributeValue(user);
    }
    @Test
    public void outTest(){
        Integer as = 1;
        println("s{a}");
        int i = 12;
        i= (i < 10) ? i * 10 : i * 100;
        log.info("i={}",i);
    }
    @Test
    public void UseSeason(){
        long seq = 2l;
        String seqStr = StringUtils.getSequence(seq);
        println(seqStr);
    }
    public void doSomething(){
        for(Season s : Season.values()){
            System.out.println(getChineseSeason(s));//这是正常的场景
        }
        //System.out.println(getChineseSeason(5));
        //此处已经是编译不通过了，这就保证了类型安全
    }
    public String getChineseSeason(Season season){
        StringBuffer result = new StringBuffer();
        switch(season){
            case SPRING :
                result.append("[中文：春天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            case AUTUMN :
                result.append("[中文：秋天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            case SUMMER :
                result.append("[中文：夏天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            case WINTER :
                result.append("[中文：冬天，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");
                break;
            default :
                result.append("地球没有的季节 " + season.name());
                break;
        }
        return result.toString();
    }
}
