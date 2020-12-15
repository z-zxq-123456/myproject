package leetcodeTest;

public class longestPalindrome {



    public String longestPalindrome(String s) {

        int len = s.length();
        int index=0,max=0,begin=0;

        while(index < len){
            int right=index,left=index;

            while(index <= len-2 && s.substring(index+1,index+2) .equals(s.substring(index,index+1))){
                index++;
                right++;
            }

            while(right < len && left >= 0 && s.substring(right,right+1).equals(s.substring(left,left+1)) ){
                right++;
                left--;
            }

            right--;
            left++;

            if  (right-left+1 > max){
                max = right-left+1;
                begin = left;
            }

            index++;
        }

        return s.substring(begin,begin+max);
    }



    public String longestPalindrome2(String s) {

        char [] chars = s.toCharArray();
        int len = chars.length;
        int index=0,max=0,begin=0;

        while(index < len){
            int right=index,left=index;

            while(index < len-1 && chars[index+1] == chars[index]){
                index++;
                right++;
            }

            while(right < len && left >= 0 && chars[right]==chars[left]){
                right++;
                left--;
            }

            right--;
            left++;

            if  (right-left+1 > max){
                max = right-left+1;
                begin = left;
            }

            index++;
        }

        return s.substring(begin,begin+max);
    }

    public static void main(String[] args) {

        String test="zcabbbcbbbacz";
        System.out.println(new longestPalindrome().longestPalindrome2(test));
    }
}
