import social.roo.utils.RooUtils;

/**
 * @author biezhi
 * @date 2017/8/1
 */
public class IdTest {
    public static void main(String[] args) {
        String tid = RooUtils.genTid();
        System.out.println(tid);
        System.out.println(RooUtils.decodeId(tid));
    }
}
