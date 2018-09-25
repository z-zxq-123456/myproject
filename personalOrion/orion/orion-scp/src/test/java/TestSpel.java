import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lixiaobin on 2017/3/24.
 */
public class TestSpel {
    public static void main(String[] arg)
    {

       /* Map map = new HashMap<>();
        map.put("SYS_HEAD","hello");
        map.put("num1",1);
        map.put("num2",2);
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(map);

        System.out.println(parser.parseExpression("[SYS_HEAD]").getValue(context));
        System.out.println(parser.parseExpression("[num1] + [num2]").getValue(context));
        for (int i  = 0; i < 100; i++)
        {
            context.setVariable("index",i);
            System.out.println(parser.parseExpression("#index").getValue(context));
        }

        List list = new ArrayList<>();
        for (int i = 0 ; i < 100 ;i++)
        {
            list.add(""+i);
        }
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("collection",list);


        List newList = (List)parser.parseExpression("#collection.subList(1,5)").getValue(context);
        System.out.println(newList);
        if (newList != null)
        {
            for (Object o:newList)
            {
                System.out.println(o);
            }
        }
*/

        Map map = new ConcurrentHashMap();

        Object o = map.containsKey(null);


        System.out.println(o);



    }

}
