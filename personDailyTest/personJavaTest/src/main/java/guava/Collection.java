package guava;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 * The <code>guava.Collection</code> class have to be described
 *
 * <p>
 * The <code>Collection</code> class have to be detailed For example:
 * <p>
 *
 * @author Administrator
 * @date 2020/11/19 14:22
 * @see
 * @since 1.0
 */
public class Collection {

    public static void main(String[] args) {

        //create
        List list1  = Lists.newArrayList();
        Map map1 = Maps.newConcurrentMap();

        //transform
        List<String> stringList = Lists.newArrayList("1","3","2");
        List<Integer> integerList = Lists.transform(stringList, Integer::parseInt);

        System.out.println(integerList);

        //find
        String value = Iterables.find(stringList, key -> {
            assert key != null;
            return key.equals("3");
        });
        System.out.println(value);

        //filter
        List<String> filterList = Lists.newArrayList(Collections2.filter(stringList,key->key.equals("2")));
        System.out.println(filterList);

        //partition
        List<List<String>> partitionGroup = Lists.partition(stringList,2);
        System.out.println(partitionGroup);


        //group 分组
        List<Mode> modeList = Lists.newArrayList(new Mode("test1","1"),new Mode("test2","12"),new Mode("test3","13"));
        Map<String,Mode> stringModeMap = Maps.uniqueIndex(modeList, Mode::getId);
        System.out.println(stringModeMap);

    }


   static class Mode{

        public Mode(String id, String func) {
            this.id = id;
            this.func = func;
        }

        private String id;
        private String func;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

       @Override
       public String toString() {
           return "Mode{" +
                   "id='" + id + '\'' +
                   ", func='" + func + '\'' +
                   '}';
       }
   }
}
