package algorithm.slideWindow;

import java.util.ArrayDeque;
import static java.lang.System.*;

/**
 * The <code>algorithm.slideWindow.Dequeue</code> class have to be described
 *
 * <p>
 * The <code>Dequeue</code> class have to be detailed For example:
 * <p>
 * 查询滑动窗口最大值的方法
 * 双端队列实现思路：
 * 将滑动窗口的最大值始终放在队首位置，将小于最大值并在最大值左边的元素删除，
 * step1:移除最左边小于最大值的元素
 * step2:从队尾向前依次移除小于当前要加入到队列元素的值
 * step3:将新元素加入到队列末尾
 * step4:将最大值加入到最终结果的数组中
 * @author Administrator
 * @date 2020/11/10 9:17
 * @since 1.0
 */
public class Dequeue {

    public static int[] maxSlidingWindow(int[] nums,int k){

        if (nums == null || k == 0){
            return new int[0];
        }
        int[] result = new int[(nums.length - k) + 1];
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i=0; i<nums.length; i++){

            if (i >= k && (i - k)>= arrayDeque.peek()) {
                arrayDeque.removeFirst();
            }

            while (!arrayDeque.isEmpty() && nums[arrayDeque.peekLast()] < nums[i])
                arrayDeque.removeLast();

            arrayDeque.offer(i);
            int rindex = i - k +1;
            if (rindex >= 0){
                result[rindex] = nums[arrayDeque.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{0,1,3,9,4,5,1,2,6,3,7};
        int[] res = Dequeue.maxSlidingWindow(arrays,3);
        for (int e:res){
               out.print(e);
               out.print(",");
         }
        System.out.println();
        syst("q",null,"d");
    }

    private static void syst(String ...a){
        String[] res = a;
        for (String sy:res){
            out.println(sy);
        }
    }

}
