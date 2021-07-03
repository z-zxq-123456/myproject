package guava;

import com.google.common.base.*;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * The <code>guava.StringTest</code> class have to be described
 *
 * <p>
 * The <code>StringTest</code> class have to be detailed For example:
 * <p>
 *
 * @author Administrator
 * @date 2020/11/19 13:55
 * @see
 * @since 1.0
 */
public class StringTest {
    public static void main(String[] args) {

        Stopwatch stopwatch = Stopwatch.createStarted();

        List<String> strings = Lists.newArrayList("1","a","b");
        String val = Joiner.on("-").skipNulls().join(strings);
        System.out.println(val);
        System.out.println(stopwatch.toString());

        String ori = "a,b,c";
        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
        System.out.println(splitter.split(ori).toString());

        String matchStri = "this is a 13 tst";

        System.out.println(CharMatcher.any().countIn(matchStri));
        System.out.println(CharMatcher.digit().countIn(matchStri));
        System.out.println(CharMatcher.digit().or(CharMatcher.javaLetter()).countIn(matchStri));
    }
}
