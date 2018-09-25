import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.SysHead;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.orion.api.Convert;
import com.dcits.orion.base.map.MapConvert;
import com.dcits.orion.scp.factory.SystemFactory;
import com.dcits.orion.scp.mapping.DataMapping;
import com.dcits.orion.scp.mapping.MappingBuilder;
import com.dcits.orion.scp.utils.ScpUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/16.
 */
public class TestDataMapping {


    public static void main(String[] args) throws Exception {
        MappingBuilder mappingBuilder = new MappingBuilder(null);
        File file = new File("E:/work/svn/trunk/Galaxy2.0/orion/orion-scp/src/test/resources/scpMapping/test-mapping.xml");
        FileInputStream inputStream = new FileInputStream(file);
        Map map = mappingBuilder.parser(inputStream);
        DataMapping dataMapping = new DataMapping();
        dataMapping.setMapping(map);
        //dataMapping.setConvert(true);



        Map request = requestMap();

        String flowID = ScpUtils.getExprString(request,"[SYS_HEAD][SERVICE_CODE]+'-'+[SYS_HEAD][MESSAGE_TYPE]+'-'+[SYS_HEAD][MESSAGE_CODE]");
        System.out.println(flowID);

        Map cacheMap = new HashMap();
        cacheMap.put("in",request);

        /*System.out.println(cacheMap);

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(cacheMap);
        context.setVariable("index",1);
        System.out.println(parser.parseExpression("[REQUEST][BODY][SERV_DETAIL1][#index][BASE_ACCT_NO]").getValue(context));*/

        Map mappingRequest = dataMapping.getMappingData(cacheMap,flowID,"dep_in");

       System.out.println(BeanUtils.getString(mappingRequest));
        //test();

        //testErrorRet();

    }
    public static Map requestMap()
    {
        String filePath = ClassLoaderUtils.getResource("financial-9999-0101.json").getFile();
        return JSON.parseObject(FileUtils.readFile(filePath));

    }

    public static void test()
    {
        Convert convert = new MapConvert();
        System.out.println( convert.pack(new SysHead(), new BeanResult("000000", "Galaxy server is starting!")));

    }

    public static void testErrorRet()
    {
        Map cacheMap = new HashMap();
        ScpUtils.addError("999999","系统错误",cacheMap);
        ScpUtils.addError("333333","执行超时",cacheMap);

        Map errorRet = ScpUtils.errorReturn(cacheMap);
        System.out.println(errorRet);
    }




  }
