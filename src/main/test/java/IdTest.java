import com.roo.utils.Hashids;

/**
 * @author biezhi
 * @date 2017/8/1
 */
public class IdTest {
    public static void main(String[] args) {
        Hashids hashids = new Hashids("blade-roo");
        String id = hashids.encode(1002, System.currentTimeMillis());
        System.out.println(id.toLowerCase());
    }
}
